import java.lang.Comparable

/**
  * Created by WEIBIN521 on 2016/1/12.
  */

final class Pairs[S,T] (val param1:S,val param2:T){

  def swap = new Pairs(param2,param1)
}

object Pairs extends App{
  val pais = new Pairs(1,2)
  val swapP = pais.swap
  print(swapP.param1)
}