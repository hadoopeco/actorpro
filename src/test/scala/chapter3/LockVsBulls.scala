package chapter3

import akka.actor.{Actor, ActorRef}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/29
 * Time: 15:16
 */

object Kios01Protocol{
  case class Ticket(seat:Int)
  case class Game(name:String, tikects:Seq[Ticket])
}

class Kiosk01(nextKiosK: ActorRef) extends Actor{
  import Kios01Protocol._

  def receive ={
    case game @ Game(_,tickets) =>
      nextKiosK ! game.copy(name = "1", tickets.tail)
  }
}


