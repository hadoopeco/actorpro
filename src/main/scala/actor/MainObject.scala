package actor

import akka.actor.{Props, Actor, ActorSystem}
import akka.io.IO
import com.typesafe.config.ConfigFactory
import org.apache.http.protocol.HTTP

import scala.concurrent.duration.FiniteDuration

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/12
 * Time: 15:47
 */
object Mian extends App {

  val config = ConfigFactory.load()

  implicit val system = ActorSystem("gotticks")
//  val actor = system.actorOf(Props(new TestActor()),"test")


//  IO(Http).ask(Http.Bind())
}
