/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/2/13
 * Time: 14:40
 */

abstract class ItemContainer

class ShoppingCart(var maximum:Int) extends ItemContainer{

    maximum = if (maximum < 10) 10 else maximum

  def test2(){
    println("test2")
  }
}

case class testCase(test:String){
  val tes = test
}

object ShoppingCart{

  def test{
    print("tests")
  }

  def main(args: Array[String]) : Unit = {
    var catalog = Vector.empty[Int]
    catalog = catalog :+ 11
    catalog :+= 12
    catalog :+= 13
    catalog :+= 14
    catalog :+= 15

    var evennum =
      for{
        number <- catalog
        if number %2 == 0
      }yield number

    print(evennum)

  }
}