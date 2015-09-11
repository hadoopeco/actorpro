package future

import akka.actor.{Props, ActorSystem, Actor}

import scala.concurrent.{Future, ExecutionContext}


/**
 * Created by lenovo on 2015/8/28.
 */
class FutureLearn extends Actor{
  implicit val ec:ExecutionContext = context.dispatcher

  override def receive: Receive = {
    case "test" =>
      var f1 = Future{
        "Hellow"+"Future"
      }

      var f2 = Future.successful(3)
      var f3 = f1.map{x => {
        f2.map{
          y =>
            x.length * y
        }
      }}

      f3 foreach  println
  }
}


object FutureLearn extends App{
  val as = ActorSystem.create("test")
  var actor = as.actorOf(Props[FutureLearn])
  actor!"test"


}