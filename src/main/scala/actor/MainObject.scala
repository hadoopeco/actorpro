package actor

import java.net.URL
import java.util

import scala.xml.{XML, Attribute}


/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/12
 * Time: 15:47
 */


class WxMessageA(appid:String,tes:String,contebt:Array[Byte]){


}
object Mian extends App {

  import  scala.xml.Null
  var ls = <img src ="testtt"/>
  var ls2 = ls  % Attribute(null,"alt","test", Attribute(null,"tips","testsss",Null))
  println(ls2)

  var root = XML.load(new URL("http://www.sina.com.cn"))
  println(root)


//  var testa:util.ArrayList[String] =  new util.ArrayList[String]()
//  testa.add("test1")
//  testa.add("test2")
//  testa.add("test3")
//  testa.add("test4")
//  testa.add("test5")
//
//  var test2 = testa.clone()
//  testa.clear()
//
//  println(testa)
//  println(test2)
//  val str = "test:user|tes2:tes5"
//
//  var map:Map[String,String] = Map.empty[String,String]
////  val s = str.split('|') map { x => {
//  //    val y = x.split(':')
//  //    map += (y(0) -> y(1))
//  //  }}
//  str.split('|') map { x => x.split(':')} foreach { x =>
//    map += (x(0) -> x(1))
//  }
//  println(map.get("test").get)
}
