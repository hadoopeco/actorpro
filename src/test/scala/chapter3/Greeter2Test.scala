package chapter3

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{WordSpecLike, MustMatchers, WordSpec}

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/5/3
 * Time: 16:08
 */
class Greeter2Test extends TestKit(ActorSystem("system"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll
{

}


