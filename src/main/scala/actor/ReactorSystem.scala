package actor

import akka.actor._
import com.okcoin.OkcoinAccess
import com.okcoin.stock.bean.Ticker
import com.okcoin.stock.strategy.TrixCalac

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/2/19
 * Time: 13:12
 */
class ReactorSystem {

}

case class GetStock(id:Long)

class Task extends Actor {


  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    context.become(houseKeeper)
  }

  override def receive: Receive = {
    case "test" =>
      context.become(taskDestinator)
    case _ =>
      println ("orther orther")
  }

  def houseKeeper : Receive ={
    case _ =>
      println("become housekepper")
      context.become(taskDestinator)
  }
  val okcoin = new OkcoinAccess
  val trixcalac = new TrixCalac
  val trader = context.actorOf(Props[TradeActor],"trader")

  def taskDestinator : Receive = {

    case GetStock(id) =>
      implicit val ec:ExecutionContext = context.dispatcher
      val timeoutMessenger = context.system.scheduler.scheduleOnce(Duration.create(1,"seconds")){
        var ticker:Ticker = okcoin.retrieveMsg()
        var trixres:String = trixcalac.calacTr(ticker.getLast)
        trader!trixres
      }
    case _ => None

  }
}

class TradeActor extends Actor{
  def receive = {
    case "buy" =>
      println("buy stock")
      sender()!GetStock(1)
    case "sell"=>
      println("sell stock")
      sender()!GetStock(1)
    case _ =>
      sender()!GetStock(1)

  }
}

object ReactorSystem{
  def main(args: Array[String]) {
    val as = ActorSystem("EnterPriseActor")
    var task = as.actorOf(Props[Task],"task")

    task!"test"
    task!GetStock(1)

//    as.shutdown()
  }
}
