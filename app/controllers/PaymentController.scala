package controllers

import javax.inject._

import models._
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc._

import scala.util.Success

import akka.actor.{ActorRef, ActorSystem}
import com.rabbitmq.client.{ConnectionFactory, DefaultConsumer}
import com.thenewmotion.akka.rabbitmq.{ChannelActor, ChannelMessage, CreateChannel, _}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Luke on 07/07/2016.
  */
@Singleton
class PaymentController @Inject()(val messagesApi: MessagesApi) extends Controller with Formatter with MongoDatabaseConnector with App {

  /**
    * Create a form of type Payment
    */
  private val cardForm: Form[Payment] = Form(
    mapping(
      "cardholder" -> nonEmptyText,
      "cardNumber" -> nonEmptyText,
      "cv" -> number,
      "expirationMonth" -> nonEmptyText,
      "expirationYear" -> nonEmptyText
    )
    (Payment.apply)
    (Payment.unapply)
  )

  private val checkoutForm = Form(
    single(
      "registerCard" -> nonEmptyText
    )
  )

  def checkout = Action {
    implicit request =>
      Ok(views.html.checkout(checkoutForm)(request.session))
  }

  def checkoutBasket = Action {
    implicit request =>
      if (request.session.get("connected").isEmpty) {
        Redirect(routes.LoginController.login())
      }
      else {
        val in = checkoutForm.bindFromRequest().data("registerCard")
        var payMthd: PaymentMethod.Value = null
        in match {
          case "payNow" => payMthd = PaymentMethod.PayNow
          case "payLater" => payMthd = PaymentMethod.PayLater
          case _ => payMthd = PaymentMethod.Other
        }
        val cust = findAccountByEmail(request.session.data("connected")).head.accountID
        val ol = OrderLine.basket
        val price = OrderLine.totalPrice(ol)
        val status = OrderStatus.Ordered.toString
        val td = todaysDate
        val time = timeNow
        val orderID = randomInt
        val ord = Order(orderID, cust, td, time, status, payMthd.toString, ol.toArray, price, 0)

        for (o <- ol) {
          println(s"Stock: "+ findProductByID(o.prodId).stock +"\nNew stock: " + (findProductByID(o.prodId).stock - o.quantity))
          Product.updateDatabaseStock(o.prodId, findProductByID(o.prodId).stock - o.quantity)

        }

        connectToDatabase(CollectionNames.ORDER_COLLECTION, DatabaseNames.ORDERS_DATABASE).onComplete {
          case Success(result) =>
            for (o <- ol) {
              sendOrderlinesToIMS(o)
            }
            Order.create(result, ord)
            OrderLine.basket.clear()
        }
        Ok(views.html.orderConfirm(ord)(request.session))

      }
  }

  def sendOrderlinesToIMS(orderline: OrderLine) = {
    implicit val system = ActorSystem()
    val HOST_ADDRESS: String = "192.168.1.15"
    val USER_NAME: String = "jesus"
    val USER_PASSWORD: String = "jesus"

    val factory = new ConnectionFactory()
    factory.setHost(HOST_ADDRESS)
    factory.setUsername(USER_NAME)
    factory.setPassword(USER_PASSWORD)
    val connection = system.actorOf(ConnectionActor.props(factory), "rabbitmq")
    val exchange = "amq.fanout"

    def toBytes(x: String) = x.getBytes("UTF-8")

    def setupPublisher(channel: Channel, self: ActorRef) {
      val queue = channel.queueDeclare().getQueue
      channel.queueBind(queue, exchange, "")
    }
    connection ! CreateChannel(ChannelActor.props(setupPublisher), Some("publisher"))

    Future {
      def loop(ol: OrderLine) {
        val publisher = system.actorSelection("/user/rabbitmq/publisher")

        val datastring: String = ol.prodId + "," + ol.quantity.toString + "," + ol.price.toString

        def publish(channel: Channel) {
          channel.basicPublish(exchange, "", null, toBytes(datastring))
        }
        publisher ! ChannelMessage(publish, dropIfNoChannel = false)

      }
      loop(orderline)
    }

  }


  /**
    * Load the registerCard page, passing in the card form
    *
    * @return
    */
  //  def registerCard = Action {
  //    implicit request =>
  //    Ok(views.html.registerCard(order)(cardForm)(request.session))  }

  //TODO
  /**
    * Find if card already exists, if not register, if so confirm registerCard
    *
    * @return
    */
  def registerPayment() = Action {
    implicit request => {

      Redirect(routes.HomeController.index())

    }
  }
}


