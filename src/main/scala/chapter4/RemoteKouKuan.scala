package chapter4

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{OneForOneStrategy, Actor, Props}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/7
 * Time: 17:40
 */
class KouKuanSupervisor(koukuanProps:Props) extends Actor{
  override def supervisorStrategy = OneForOneStrategy(){
    case _:Exception => Restart
  }

  val koukuan = context.actorOf(koukuanProps)

  def receive ={
    // 调动扣款process
    case m => koukuan forward(m)

  }
}


class KouKuanProcess extends Actor{

  def receive ={
    case _ =>
      println("koukuan")
      Thread.sleep(10000)
  }
}
