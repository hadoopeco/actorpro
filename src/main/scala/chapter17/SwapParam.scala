package chapter17

/**
  * Created by WEIBIN521 on 2016/1/13.
  */
class SwapParam[T](val s:Map[T,T]) {

  def swap(para : Map[T,T]) ={


  }

}

object SwapParam extends App{
  val map:Map[String,String] = Map("S" -> "Sq", "T"-> "St")
  map.foreach(e => {
    val (k,v) = e
    println(k)
  } )


  def middle[Iterable[T]](param:String) = param.charAt(param.length/2)



}