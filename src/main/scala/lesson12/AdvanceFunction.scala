package lesson12

/**
 * Created by lenovo on 2015/6/10.
 */
class AdvanceFunction {

}


object AdvanceFunction extends App{

  val s = value(x=>x*x,1 to 5)
  def value(fun:(Int) => Int,input:Seq[Int]) = {
    input map{(_,fun(_))}
  }

  val xarray:Array[Int] = Array(1,4,3,14,5,61,7)

  def fun(x:Int,y:Int):Int ={
    if(x > y) x
    else  y
  }
  val x = xarray.reduceLeft(fun(_,_))
  println(x)


  def largest(xfun:(Int)=>Int,inputs:Seq[Int]):Int ={
    inputs.map(xfun(_)).reduceLeft(fun(_,_))
  }
 val x2  =  largest(x=>x*10 - x*x,1 to 5)
  println("x2 = " +x2)
  val digest = List(2,3)
  9::digest


  def sum(lst:List[Int]):Int =
    if(lst == Nil) 0 else lst.head + sum(lst.tail)

  println(sum(List(1,2,3,4,5,6,7,8,9,10)))


  val names = List("name","vector","test")

  val upname = names.map{_.toUpperCase}

  upname.foreach(println)


  def numForm(n:BigInt):Stream[BigInt] = n #:: numForm(n+1)

  val su = numForm(10)

  val qures  = numForm(1).map(x=> x*x)
  println("su " + qures(3))

  def pow(a:Int,b:Int) = a * b

  val aview = (1 until 100).view.map(pow(10,_)).map(1/_).force
  val aview1 = (1 until 100).map(pow(10,_)).map(1/_)

  println(aview)
  println(aview1)

  val  scount =(1 until 100).par.count(_%2 == 0)
  println(scount)


  val price =  List(1,2,3,41)
  val nums = List(2,3,4,5)

  val result = price zip nums map{p => p._1 * p._2} sum

  println("result " + result)


  for(i <- (0 until 100).par) print(i + "t")


  def indexs(str:String): Unit ={
    for( n <- str ) println(n + " ")
  }

  indexs("Mississippi")

}