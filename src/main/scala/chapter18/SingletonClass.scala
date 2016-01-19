package chapter18

/**
  * Created by WEIBIN521 on 2016/1/13.
  */
class Document{
  def setTitle(title:String) ={
    this
  }

  def setContent(content:String) ={
    this
  }
}

object Title
class Book extends Document{
  def addChapter(chapter:String):this.type = {
    this
  }

  def set(obj : Title.type ):this.type ={this}


}

object Article extends App{
  val article = new Document
  article.setTitle("test").setContent("test2")

  object duk{
    def append(str:String): Any ={
      print(str)
      ""+str
    }
  }
  def appendLine(target:{def append(str:String):Any},lines:Iterable[String]){
     for (line <- lines){
       target.append(line)
       target.append(" test \n")

     }
  }

  appendLine(duk,Array("test","test2"))

  duk.append("test")


}
