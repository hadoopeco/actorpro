package chapter4

import java.io.File

import akka.actor._
import chapter4.LogProcessingProtocol.LogFile

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/7
 * Time: 16:21
 */

object LogProcessingProtocol{
  case class LogFile(file:File)

  case class Line(time:Long, message:String, messageType:String)
}

class LogProcSupervisor(kouKuanSupervisorProps:Props) extends Actor{

  val kkuanSupervisor = context.actorOf(kouKuanSupervisorProps)
  val logProce  = context.actorOf(Props(new LogProcessor(kkuanSupervisor)))

  def receive={
    case m => logProce  forward(m)
  }
}

class ManycallSupervisor(sources:Vector[String],props:Props) extends Actor{
   var calls:Vector[ActorRef] = sources.map{ source =>
       val kkuanSuper = context.actorOf(props)
       context.watch(kkuanSuper)
       kkuanSuper
   }

  def receive = {
    case "test" =>
      calls.foreach{ event =>
        event forward("test")
      }
  }
}

class CallProcess  extends Actor{
  import  FileWatcherProtocol._
  def receive = {
    case _ =>
      Thread.sleep(10000)
      println("calling proc")
  }
}

class LogProcessor(kouKuanSupervisor:ActorRef) extends Actor {
  import LogProcessingProtocol._

  def receive ={
    case LogFile(file) =>
      val lines = Vector("test","test2")
      lines.foreach(kouKuanSupervisor !_ )
  }

}
