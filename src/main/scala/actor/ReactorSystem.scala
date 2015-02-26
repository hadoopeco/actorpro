package actor

import akka.actor.Actor.Receive
import akka.actor._

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/2/19
 * Time: 13:12
 */
class ReactorSystem {

}

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

  def taskDestinator : Receive = {
    case _ =>
      implicit val ec:ExecutionContext = context.dispatcher
      val timeoutMessenger = context.system.scheduler.scheduleOnce(Duration.create(10,"seconds")){
        println("task destinator")
      }
  }


}

object ReactorSystem{
  def main(args: Array[String]) {
    val as = ActorSystem("EnterPriseActor")
    var task = as.actorOf(Props[Task],"task")

    task!"test"
    task!"test"

//    as.shutdown()
  }
}
