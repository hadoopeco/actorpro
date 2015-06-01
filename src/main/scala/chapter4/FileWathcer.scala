package chapter4

import java.io.File
import java.net.URI

import akka.actor._

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/7
 * Time: 17:31
 */

object FileWatcherProtocol {
  case class NewFile(file: File, timeAdded: Long)
  case class SourceAbandoned(uri: String)
}

class FileWatcher(sourceUri:String, logProcSupervisor:ActorRef)
  extends Actor with FileWatchingAbilities{
  import FileWatcherProtocol._
  import LogProcessingProtocol._
  register(sourceUri)
  def receive = {
    case NewFile(file,_) =>
      logProcSupervisor !  LogFile(file)
    case SourceAbandoned(uri) =>
      self ! PoisonPill
  }

}

class FileWatcherSupervisor(sources:Vector[String],logProcSuperProps:Props) extends Actor{

  var fileWatchers : Vector[ActorRef]  = sources.map{source =>
    val logProcSupervisor = context.actorOf(logProcSuperProps)
    val fileWatcher  = context.actorOf(Props(new FileWatcher(source,logProcSupervisor)))
    context.watch(fileWatcher)
    fileWatcher
  }

  def receive = {
    case Terminated(fileWatcher) =>
      fileWatchers = fileWatchers.filterNot{w => w == fileWatcher}
      if(fileWatchers.isEmpty) self!PoisonPill
  }


}
