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
    var catalog = Vector.empty[String]
    catalog = catalog :+ "stst"

    println(catalog)
    var test = testCase("testCase1")
    println(test.tes)

  }
}