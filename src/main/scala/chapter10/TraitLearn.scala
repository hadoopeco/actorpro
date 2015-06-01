package chapter10

import java.awt.Point
import java.beans.PropertyChangeListener

/**
 * Created by lenovo on 2015/6/1.
 */

trait Logger{
  val maxlenght:Int

  def info(msg:String): Unit ={
    println("info :"+msg +"  length="+maxlenght)
  }
}

trait TestLogger{
  val filename:String
}

class Account{

}

class ShortAccount extends Account with Logger{
  val maxlenght = 20
  def run(): Unit ={
    info("ShortAccount run")
  }
}

object Apprun extends App with Logger{
  val maxlenght = 30
  val acc = new ShortAccount
  acc.run()
  info("App run")
}

class OrderPoint extends Point with Ordered[OrderPoint]{


  override def compare(that:OrderPoint): Int = {
    0
  }
}