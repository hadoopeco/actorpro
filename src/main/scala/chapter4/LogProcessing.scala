package chapter4

import java.io.File

import akka.actor.{Props, ActorSystem}


/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/7
 * Time: 14:49
 */


object LogProcessingApp extends App{
    var sources  = Vector("tt","ttt")
    for(i <- 1 to 100000){
      sources = sources :+("test"+i)
    }

    val system = ActorSystem("logProcessSys")


    val callproc = Props(new CallProcess)
    val callsupervisor = Props(new ManycallSupervisor(sources,callproc))
//    val kKuanSuperisor = Props(new KouKuanSupervisor(kKuanProc))
////    val logprSuper = Props(new LogProcSupervisor(kkuanProp))
//    val fileWaSupervisor = Props(new FileWatcherSupervisor(sources,logprSuper))

    val kkuan = system.actorOf(callsupervisor)

    kkuan!"test"


}



trait FileWatchingAbilities {
  def register(uri: String) {

  }
}


trait LogParsing {
  import LogProcessingProtocol._
  // Parses log files. creates line objects from the lines in the log file.
  // If the file is corrupt a CorruptedFileException is thrown
  def parse(file: File): Seq[Line] = {
    Nil
  }
}