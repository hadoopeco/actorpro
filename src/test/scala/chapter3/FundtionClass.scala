package chapter3

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/3
 * Time: 23:01
 */
class FundtionClass {

}

object test extends App{
//    for (i <- 1 to 3 ; from = 4 - i; j <- from to 5  ) printf("test %s * %s ", i,j)




}


class Time(hour:Int,var min:Int){


}

object Time{
  private var count = 0

  def apply(min:Int) = new Time(newNum(),min)


  def newNum() = {
    count += 1
    count
  }
}