package actor

import akka.actor.{Props, ActorSystem, Actor, ActorLogging}
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
  var ticker10que = new mutable.Queue[Float]
  var count:Int = 30

  context.system.scheduler.schedule(10 seconds,20 seconds){
     val ticker = okcoin.retrieveMsg()
     if (ticker != null) {
//       if (count == 0) {
         if (tickerque.size >= 20) {
           tickerque.dequeue()
         }
         tickerque += ticker.getLast
        if (tickerque.size >= 20) {
          self ! Indicator(tickerque,ticker.getLast)
        } else {
          log.info("prices size less than 20  size = {}", tickerque.size)
        }

//       }
     }else{
       log.info("get null ticker")
     }
  }

  var buyflag:Boolean = false
  override def receive: Receive = {
    case Indicator(allprice,curprice)=>
      log.info("Indicator price {}", curprice)
      val prices =  new Array[Float](20)
      allprice.copyToArray(prices)
      var total:Float = 0
      for(i <-0 until 20){
        total += prices(i)*(i+1)
      }
      var part10:Float = 0
      for(j <- 13 until 20){
        part10 += prices(j)*(j-12)
      }
      val ema20 = total / 210
      val ema10 = part10 / 28
      log.info("Indicator price ema10 {} eam20 {}",ema10,ema20 )
      if ( ema10 >= ema20 && !buyflag) {
          log.info("buy the ticket price {}", curprice)
          self ! BuyOKTicker("buy", curprice)
          buyflag = true
      }else if(ema10 <=  ema20 && buyflag){
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


//  var parts:Array[String] = Array("1","2","3","4","5","6","7","8","9","10","11","12")


  def unapplySeq(input:String):Option[Seq[String]] =
    Some(input.split("\\s+"))
}




case class Indicator(prices:mutable.Queue[Float],curprice:Float)
case class BuyOKTicker(symble:String,curprice:Float)
