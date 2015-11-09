package spark

/**
 * Created by lenovo on 2015/10/3.
 */
private[spark] class DefaultAddress(val host:Option[scala.Predef.String], posrt:String) {


}

class test(address:DefaultAddress){
  address.host.getOrElse()
}
object DefaultAddress extends App{
 val jars = Seq("test", null,"test1")

  val str = jars.filter(_ != null).count(_.contains("test"))
  print(str)
//  val map:Map[String,String] = jars.map(key => (key,key)).toMap
//
//  def params = Map(
//    "test" -> "test1",
//    "test2" -> "tes5",
//    "start" -> "tes5"
//  )
//  for((key,value) <- params if key.startsWith("test")){
//    println(value)
//  }
//  print(params.get("test"))
}