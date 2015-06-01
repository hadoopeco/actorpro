package chapter3

import akka.actor.{Actor, Props, ActorSystem}
import akka.testkit.{CallingThreadDispatcher, EventFilter, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/29
 * Time: 17:33
 */

object GreeterProtocol{
  case class Greeting(name:String)
}

class Greeter extends Actor{
  import GreeterProtocol._
  def receive = {
    case Greeting("name")=>
      print("test")
  }
}
class Greeter1Test extends TestKit(ActorSystem("testsystem"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll
{
  import GreeterProtocol._
  "The Greeter" must {
    "say hello Word! when a Greeting () is send to it " in{
//        EventFilter.info(message = "World!")
      val disId = CallingThreadDispatcher.Id
      val props = Props[Greeter].withDispatcher(disId)
      val greeter = system.actorOf(props)
        greeter!Greeting("name")
//      EventFilter.info(message = "name",
//      occurrences = 1).intercept {
//      }

    }
  }
}

object  Greeter1Test{
  val testSystem  ={
    val config =  ConfigFactory.parseString(
      """akka.event-handlers = ["akka.testkit.TestEventListener"]"""
    )
    ActorSystem("testSystem",config)

  }
}
