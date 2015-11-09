package actor

import com.WxMessage
import com.alibaba.fastjson.JSON

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/12
 * Time: 15:47
 */


class WxMessageA(appid:String,tes:String,contebt:Array[Byte]){


}
object Mian extends App {

  val str = "test:user|tes2:tes5"

  var map:Map[String,String] = Map.empty[String,String]
//  val s = str.split('|') map { x => {
  //    val y = x.split(':')
  //    map += (y(0) -> y(1))
  //  }}
  str.split('|') map { x => x.split(':')} foreach { x =>
    map += (x(0) -> x(1))
  }
  println(map.get("test").get)
}
