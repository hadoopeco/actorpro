package chapter16


import scala.xml.dtd.{PublicID, DocType}
import scala.xml.transform.{RuleTransformer, RewriteRule}
import scala.xml._

/**
 * Created by lenovo on 2016/1/6.
 */
class XmlElement {

}

object XmlElement extends App{

  var image = <img href="http://www.163.com" alt="test"/>
  val href = image.attributes("href")
  val alt = image.attributes.get("alt1").getOrElse(Text("test"))

  for(attr <- image.attributes){
    println(attr.key)
  }

  println(image.attributes.asAttrMap)

  val testtext = for(attr <- image.attributes) yield  <li> {attr.key} </li>

  println(testtext)

  val lmt = <dl><dd>java</dd><dd>Gosling</dd><dd>Scala</dd><dd>Odersky</dd></dl>
  val lau = lmt \ "dd"
  println("xpath = " + lau)

  val nodecopy = lmt.copy(label = "ol")
  println(nodecopy)

//  val changeattr = for(n <- lmt \ "dd") { n % Attribute(null, "src","test",null ) }


  var rule = new RewriteRule{
    override def transform(n: Node) = n match {
      case e @ <dl>{_*}</dl> => e.asInstanceOf[Elem].copy(label = "ol")
      case _ => n
    }
  }
  val test = new RuleTransformer(rule).transform(lmt)

  println("transform = "+test)

  XML.save("my.html",lmt,enc = "UTF-8",xmlDecl = false,
    doctype = DocType("html",
      PublicID("-//W3C//DTD XHTML 1.0 Strict//EN","http://www.w3.org/TR/xhtml/DTD/xhtml-strict.dtd"),
      Nil
    )
  )

  val node = <img src="tests"/>
  node match {
    case n@ <img/> => println(n)
    case _ => println("node match")
  }

  val nestNode = <li><span> test </span></li>
  nestNode match {
    case <li>{child}</li> => println(child)
    case _ => println("nest node")
  }

  val minTagMin = <img src="test"></img>
  val src = xml.Utility.serialize(minTagMin,minimizeTags = MinimizeMode.Always)
  println(src)

  val field = <filed/>(0)(0)
  println("feild = "+field)

  val ques1 = <ul>
                <li>Opening bracket: [</li>
                <li>Closing bracket: ]</li>
                <li>Opening brace: {{</li>
                <li>Closing brace: }}</li>
              </ul>

  println(ques1)

//  ques2
  val q1 = <li>fred</li>
  val q2 = <li>{"fred"}</li>

  q1 match {
    case <li>{Text(t)}</li> => println(t)
  }
  q2 match {
    case <li>{Text(t)}</li> => println(t)
  }
}
