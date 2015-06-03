package actor

import actor.Indicator
import akka.actor.{ActorLogging, Props, ActorSystem, Actor}
import com.okcoin.OkcoinAccess

import scala.collection.mutable
import scala.concurrent.duration._

/**
 * Created by lenovo on 2015/6/3.
 */
class OkCoinSystem extends Actor with ActorLogging{
  import context.dispatcher
  val okcoin = new OkcoinAccess
  var tickerque = new mutable.Queue[Float]
  var running  = false
  context.system.scheduler.schedule(20 seconds,5 seconds){
      val ticker = okcoin.retrieveMsg()
      if (tickerque.size >= 20) {
        tickerque.dequeue()
      }
      tickerque += ticker.getLast
      log.info("current price = {}", ticker.getLast)
      if (tickerque.size >= 20) {
        self ! Indicator(tickerque, ticker.getLast)
      } else {
        log.info("prices size less than 20  size = {}", tickerque.size)
      }
  }

  var buyflag:Boolean = false
  override def receive: Receive = {
    case Indicator(price,curprice)=>
      if (curprice > price.max && !buyflag) {
          log.info("buy the ticket price {}", curprice)
          self ! BuyOKTicker("buy", curprice)
          buyflag = true
      }else if(curprice < price.min && buyflag){
          log.info("sell the ticket price {}", curprice)
          self ! BuyOKTicker("sell", curprice)
          buyflag = false
      }
    case msg@BuyOKTicker("buy",curprice) =>
      okcoin.buyTicker(curprice)

    case msg@BuyOKTicker("sell",curprice) =>
      okcoin.sellTicker(curprice)
    case _ =>
      println("recieve message")
  }
}


object OkCoinSystem extends App{
  val  sac = ActorSystem.create("stockSystem")
  sac.actorOf(Props[OkCoinSystem])

}


case class Indicator(prices:mutable.Queue[Float],curprice:Float)
case class BuyOKTicker(symble:String,curprice:Float)
