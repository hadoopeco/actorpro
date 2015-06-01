package stock

import akka.actor.{Actor, Props}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/27
 * Time: 23:24
 */
class MainObject {

}

object Main extends App{



}



class TestActor extends Actor{
  def receive = {
    case "test" =>
      print("test")
      if(context.child("test").isEmpty){
        context.actorOf(Props[Stock2Actor],"test")
      }
  }

}

class Stock2Actor extends Actor{
  def receive = {
    case "stock" =>
      print("test2")
  }
}
