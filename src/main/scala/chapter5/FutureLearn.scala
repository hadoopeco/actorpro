package chapter5

import java.io.File

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/8
 * Time: 17:11
 */

object FutureObj extends App{
//   val lines = Source.fromURL("http://www.163.com").getLines().toArray
//
      def subdir(dir:File):Iterator[File] ={
          val child = dir.listFiles().filter(_.isDirectory)
          val files = dir.listFiles().filter(_.isFile)
          for(f <- files) {
            println(f.getName)
          }
          child.toIterator ++ child.toIterator.flatMap(subdir _)
      }

     for( d <- subdir(new File("D:\\bak\\book\\paff"))){
       println(d.getAbsolutePath+d.getName)
     }
}

