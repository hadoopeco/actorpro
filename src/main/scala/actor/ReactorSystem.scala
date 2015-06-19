package actor

import akka.actor._
import com.okcoin.OkcoinAccess
import com.okcoin.stock.bean.Ticker
import com.okcoin.stock.strategy.TrixCalac

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

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
  import scala.math._
  def main(args: Array[String]) {
    val fun = ceil _
    val a = Array(1.2,3.2,3.3,4.5) filter (p => p>2)
//    print(ceil(4.1223))
    println(test(ceil, 2.1))
    val pa = mapBy(10)
    println(pa(20))

    (1 to 20).map{"*" * _}.foreach{println _}

    "mark test wark".split(" ").sortWith(_.length > _.length )

  }


  def runTest(block: =>Unit){
    block
  }

  def test(f:(Double) => Double,x:Double) = f(x)

  def mapBy(factory:Double) = {
    (x: Double) => {
      print(factory)
      factory * x
    }
  }


}
