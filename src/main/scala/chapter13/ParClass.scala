package chapter13

/**
 * Created by lenovo on 2015/7/7.
 */
class ParClass {

}

object ParClass extends App{
 var j = for(i <- (0 until 100).par) yield i
  print(j)

  for(i <- "sssss") print(i)
}
