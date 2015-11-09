package stock

import akka.actor.{Terminated, Actor, ActorSystem, Props}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}
import com.alibaba.fastjson.JSON


/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/27
 * Time: 23:24
 */
object ActorFactory{

}

class msg(s:String,t:String) {

}
object Test extends App{
    def f:PartialFunction[Char,Int] = {case '-' =>  -1 ; case '+' => +1 ; case _ => 0 }

    def  swap(arr:(Int,Int)):(Int,Int) = {
      arr match {
        case (x,y) => (y,x)
      }
    }
    val  s = swap((1,2))
   print(s)
  val openId = "test"
  val msg = "msg"
//  val con =  s"{\"touser\":\"$openId\",\"msgtype\":\"text\",\"text\":{\"content\":\"$msg\"}}"


  def swapArray(arr:Array[Int]):Array[Int] ={
    arr match {
      case Array(first,second,_*) => Array(second,first)
    }
  }
  val arr = Array(1,2,3,4,5,6,7)
  val arrs = swapArray(arr)
  print(arrs)

  val a:List[Any] = List(List(3,8),2,List(5))
  

//  val as:ActorSystem = ActorSystem.create("test1")
//  val as2:ActorSystem = ActorSystem.create("test2")
//  val ac1 = as.actorOf(Props[TestActor],"ta1")
//
//  val ac2 = as2.actorOf(Props(new TestActor2(as)),"ta2")
//
//  ac2!UserInput("chat:message")
//  ac2!UserInput("chat:test")
//  as.shutdown()
//  as2.shutdown()
}


class TestActor() extends Actor{
  override def receive: Receive = {
    case "test" =>
      print("test actor print")
    case "test2" =>
      print("test actor print2")

  }
}

case class UserInput(event:String)
class TestActor2(as:ActorSystem) extends Actor{
  val MatchCommand = """^(.+):(.+)""".r

  override def receive: Receive = {
    case "test" =>
      as.actorSelection("ta1")!"test"
      println("TestActor2 print")
    case msg@UserInput(MatchCommand(event,"message"))=>
      println(event);
    case "test2" =>
      print("TestActor2 print2")

  }
}


class Worker extends Actor{
  override def receive: Receive = {
    case "test" =>
      print("any test")
  }
}
class TestActors extends Actor{

  var router = {
    var routees = Vector.fill(5){
      var r =  context.actorOf(Props[Worker])
      context watch  r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(),routees)
  }


  override def receive: Receive ={
    case w:Worker =>
      router.route(w,sender())
    case Terminated(a) =>
      router.removeRoutee(a)
      var r =  context.actorOf(Props[Worker])
      context watch  r
      router  = router.addRoutee(r)
  }
}