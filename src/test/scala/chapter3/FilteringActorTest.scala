package chapter3

import akka.actor.{ActorRef, Actor}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/29
 * Time: 16:18
 */

object FilteringActorProtocal{
  case class Event(id:Long)
}

class FilteringActor(nextActor:ActorRef,bufferSize:Int) extends Actor{
  import FilteringActorProtocal._

  var lastMessages = Vector[Event]()
  def receive = {
    case msg:Event =>
      if(!lastMessages.contains(msg)){
        lastMessages = lastMessages :+ msg
        nextActor ! msg

        if(lastMessages.size > bufferSize){
          lastMessages = lastMessages.tail
        }
      }
  }
}