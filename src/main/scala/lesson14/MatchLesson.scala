package lesson14

/**
 * Created by lenovo on 2015/6/19.
 */
class MatchLesson {


}

object MatchLesson extends App{
  var ch:Char = '3'
  var sign = ch match {
    case '_' => 1
    case '+' => 2
    case _ if Character.isDigit(ch) => Character.digit(ch,10)
    case _ => 0
  }
  println(sign)

  val pattern = "([0-9]+) ([a-z]+)".r

  "99 items" match {
    case pattern(num,item) => println(num,item)
    case _ => println("test")
  }

  val arra = Array(0,2)

  arra match {
    case Array(0) => println("array is 0")
    case Array(x,y) => println("array is x,y")
    case Array(0,_*) => println("array is 0 ,*")
    case _ => println("other")
  }




}
