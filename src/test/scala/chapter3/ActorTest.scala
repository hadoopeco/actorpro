package chapter3

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest._



trait StopSystemAfterAll extends BeforeAndAfterAll{

  this: TestKit with Suite =>
     override  protected def afterAll(){
      super.afterAll()
      system.shutdown()
  }
}

object SilentActorProtocal{
  case class SilentMessage(data:String)
  case class GetState(receiver:ActorRef)
}

class SilentActor extends Actor{
  import chapter3.SilentActorProtocal._

  var internalState = Vector[String]()

  def receive = {
    case SilentMessage(data) =>
      internalState = internalState :+ data

    case GetState(receiver) =>
      receiver ! internalState

  }

  def state = internalState
}


class SlientAcotrTest extends TestKit(ActorSystem("testsystem"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll{
  "A Silent Actor" must {
      "change state when it receives a message , single thread" in{
        import chapter3.SilentActorProtocal._
        val silentActor = TestActorRef[SilentActor]
        silentActor ! SilentMessage("whisper")

        silentActor.underlyingActor.state must contain("whisper")
      }

    "change state with it recieve a message, multipule thread" in {
      import chapter3.SilentActorProtocal._
      val silentActor = system.actorOf(Props[SilentActor],"S3")
      silentActor ! SilentMessage("whisper1")
      silentActor ! SilentMessage("whisper2")
      // testActor 是TestKit 提供的测试类
      silentActor ! GetState(testActor)

      expectMsg(Vector("whisper1","whisper2"))

    }

    "send a message to an actor when it has finished" in {
      import Kios01Protocol._

      val props =  Props(new Kiosk01(testActor))
      val sendingActor = system.actorOf(props,"kiosk01")
      val tickets = Vector(Ticket(1),  Ticket(2), Ticket(3))

      val game = Game("Lakers vs Bulls", tickets)
      sendingActor ! game

      expectMsgPF(){
        case Game(_,tickets) =>
          tickets.size must be (game.tikects.size  - 1 )
      }

    }


    "filter out particular message" in {
      import FilteringActorProtocal._

      val props = Props(new FilteringActor(testActor,5))
      val filter = system.actorOf(props)

      filter ! Event(1)
      filter ! Event(2)
      filter ! Event(1)
      filter ! Event(2)
      filter ! Event(3)
      filter ! Event(3)
      filter ! Event(4)
      filter ! Event(5)
      filter ! Event(5)
      filter ! Event(6)
      filter ! Event(7)

      val eventIds = receiveWhile(){
        case Event(id) if id <= 5 => id
      }

      eventIds must be (List(1,2,3,4,5))
      expectMsg(Event(6))
    }

   "filter out particular messages using expectNoMsg" in{
     import FilteringActorProtocal._

     val props = Props(new FilteringActor(testActor,5))
     val filterActor = system.actorOf(props,"filter-2")

     filterActor! Event(1)
     filterActor! Event(2)
     expectMsg(Event(1))
     expectMsg(Event(2))
     filterActor! Event(1)
     expectNoMsg
     filterActor! Event(3)
     expectMsg(Event(3))
     filterActor! Event(4)
     expectMsg(Event(4))
     filterActor! Event(5)
     expectMsg(Event(5))
   }
  }
}




