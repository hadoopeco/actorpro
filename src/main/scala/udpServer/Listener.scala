package udpServer

import java.net.InetSocketAddress

import akka.actor.Actor
import akka.io.Udp

/**
 * Created by lenovo on 2015/8/28.
 */
class SimpleSender(remote:InetSocketAddress) extends Actor{

//  IO(Udp)!Udp.SimpleSender

  override def receive: Receive = {
    case Udp.SimpleSenderReady =>
      context.become(ready(sender()))
  }

  def ready(send:AnyRef):Receive  = {
    case msg:String =>
//      sender!Udp.Send(ByteString(send),remote)
  }
}
