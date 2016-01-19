package chapter17



/**
  * Created by WEIBIN521 on 2016/1/13.
  */

class Pair[T](val firs:T,val second:T){
  def replaceFirst(newFirst : T) = new Pair(newFirst,second)
}

abstract class List[+T]{
  def isEmpty : Boolean

  def head : T

  def tail : List[T]

}

class Node[T] (val head : T, val tail: List[T]) extends List[T]{
  def  isEmpty = false
}

class Empty[T] extends List[T]{
  def isEmpty = true

  def head = throw new UnsupportedOperationException

  def tail = throw new UnsupportedOperationException

}

//对象不能泛型的处理 使用Noting
object Empty1 extends List[Nothing]{

  def isEmpty = true

  def head = throw new UnsupportedOperationException

  def tail = throw new UnsupportedOperationException
}

object objectGeneric extends App{

  val node = new Node(42,new Empty)


  def getMiddle[T](a:Array[T]) = println(a(a.length/2))

  //定义一个参数为函数，需要有一个下划线，否则报错
  val f = getMiddle[String] _
  f(Array("test1","test2","test3"))
 }