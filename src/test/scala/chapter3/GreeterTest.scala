package chapter3

import akka.testkit.{EventFilter, TestKit}
import org.scalatest.{MustMatchers, WordSpecLike, fixture}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/4/29
 * Time: 17:33
 */
class GreeterTest extends TestKit(testSystem)
with WordSpecLike
with MustMatchers
with StopSystemAfterAll
{
  "The Greeter" must {
  }
}
