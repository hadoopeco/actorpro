package router


import akka.actor._
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}


/**
 * Created by lenovo on 2015/8/14.
 */

class Worker extends Actor{
  override def receive: Receive = {
    case "test" =>
      print("Worker Test ")

  }
}

class Master extends Actor{

  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props[Worker])
      println("prepare the router")
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(),routees)
  }

  override def receive: Receive = {

    case w@"test" =>
      println("Master "+w)
      router.route(w,sender)
    case Terminated(a) =>
      router.removeRoutee(a)
      println("remove from routees "+a)
      val r = context.actorOf(Props[Worker])
      context watch r
      router.addRoutee(r)
  }
}


object Master extends App{
  val as = ActorSystem.create("master")
  val m = as.actorOf(Props[Master])
  m!"test"
}
