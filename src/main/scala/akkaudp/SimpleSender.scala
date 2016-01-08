import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, Props}
import akka.io.{IO, Udp}

/**
 * Created by lenovo on 2015/8/14.
 */



class SimpleSender(remoter:InetSocketAddress) extends Actor{
  import context.system
  IO(Udp)!Udp.SimpleSender


  override def receive: Receive = {
    case Udp.SimpleSenderReady =>
      context.become(oreder())
  }

  def oreder():Receive ={
    case "test" =>{

    }
  }
}

object SimpleSender extends App{

  def props(remoter:InetSocketAddress) = Props(new SimpleSender(remoter))
}

class Listener extends Actor{
//  IO(Udp)!Bind(self,new InetSocketAddress("127.0.0.1",0))

  override def receive: Receive = {
    case Udp.Bound(local) =>
      println("tests")

  }

  def ready(sokect:ActorRef):Receive = {
    case Udp.Received(data,remote) =>
      sokect!Udp.Send(data,remote)
  }
}