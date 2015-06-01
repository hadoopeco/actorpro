package chapter4

import akka.actor.{Props, ActorSystem, ActorLogging, Actor}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/6
 * Time: 17:41
 */
class LifeCycleHook extends Actor with ActorLogging{
  println("Constractor")

  override def preStart(){
    println("pre Start")
  }

  @scala.throws[Exception](classOf[Exception])
  override def postStop(){
    println("post stop")
  }

  @scala.throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]){
    println("pre Restart")
    super.preRestart(reason,message)
  }

  @scala.throws[Exception](classOf[Exception])
  override def postRestart(reason: Throwable){
    println("post  Restart")
    super.postRestart(reason)
  }

  def receive ={
    case "restart" =>
      println("restart  actor")
      throw new IllegalStateException("force restart")
    case msg:AnyRef =>
      println("Reveive")
  }
}

object ObjectMain extends App{

  override def main(args: Array[String]): Unit = {
//    val as:ActorSystem  = ActorSystem.create("sys")
//    val testArf = as.actorOf(Props[LifeCycleHook],"LifeCycleHooks")
//    testArf!"restart"
//    testArf.tell("msg",testArf)
//    as.stop(testArf)

  }
}
