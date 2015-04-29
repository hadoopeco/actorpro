package inaction.ticker

import akka.actor.Actor.Receive
import akka.actor.{Props, PoisonPill, Actor}

import scala.collection.immutable.Queue

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/10
 * Time: 16:44
 */



class TickerSeller extends Actor{

  var ticker = Vector[Ticket]()

  def receive ={
    case GetEvent => sender! ticker.size

    case Tickers(newTickers) =>
      ticker = ticker ++ newTickers
    case BuyTicket =>
      if(ticker.isEmpty){
        sender!Soldout
        self!PoisonPill
      }

      ticker.headOption.foreach{
        ticket =>
        ticker = ticker.tail
        sender!ticker
      }
    case Event(name,nofTickers)=>
      if(context.child(name).isEmpty){
        val tickerSeller = context.actorOf(Props[TickerSeller],name)


        val tickets = Tickers((1 to nofTickers).map(
          nr=> Ticket(name, nr)).toList)
        tickerSeller ! tickets
      }
      sender! EventCreated
    case TicketRequest(name)=>
      context.child(name) match {
        case Some(tickseller)   => tickseller.forward(BuyTicket)
        case None => sender!Soldout
      }
  }

}

object Main{
  def main(args:Array[String]){
    println("sssss")
  }
}
case class Ticket(name:String, nr:Int)
case object GetEvent
case class Tickers(newTickers:List[Ticket])
case object Soldout
case class Event(event:String, nofTickers:Int)
case object EventCreated
case class TicketRequest(event:String)
case object BuyTicket
