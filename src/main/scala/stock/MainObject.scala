package stock

import akka.actor.{Terminated, Actor, ActorSystem, Props}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}


/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/27
 * Time: 23:24
 */
object ActorFactory{

}


object Test extends App{

  val as:ActorSystem = ActorSystem.create("test1")
  val as2:ActorSystem = ActorSystem.create("test2")
  val ac1 = as.actorOf(Props[TestActor],"ta1")

  val ac2 = as2.actorOf(Props(new TestActor2(as)),"ta2")

  ac2!UserInput("chat:message")
  ac2!UserInput("chat:test")
  as.shutdown()
  as2.shutdown()
}


class TestActor() extends Actor{
  override def receive: Receive = {
    case "test" =>
      print("test actor print")
    case "test2" =>
      print("test actor print2")

  }
}

case class UserInput(event:String)
class TestActor2(as:ActorSystem) extends Actor{
  val MatchCommand = """^(.+):(.+)""".r

  override def receive: Receive = {
    case "test" =>
      as.actorSelection("ta1")!"test"
      println("TestActor2 print")
    case msg@UserInput(MatchCommand(event,"message"))=>
      println(event);
    case "test2" =>
      print("TestActor2 print2")

  }
}


class Worker extends Actor{
  override def receive: Receive = {
    case "test" =>
      print("any test")
  }
}
class TestActors extends Actor{

  var router = {
    var routees = Vector.fill(5){
      var r =  context.actorOf(Props[Worker])
      context watch  r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(),routees)
  }


  override def receive: Receive ={
    case w:Worker =>
      router.route(w,sender())
    case Terminated(a) =>
      router.removeRoutee(a)
      var r =  context.actorOf(Props[Worker])
      context watch  r
      router  = router.addRoutee(r)
  }
}