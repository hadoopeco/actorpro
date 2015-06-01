package chapter10

import java.io.{InputStream, BufferedInputStream}

/**
 * Created by lenovo on 2015/6/1.
 */

trait Component{

}

trait JComponent extends Component{

}

trait Container extends Component{

}

trait JContainer extends Component with JComponent{

}

trait BufferStreamLike extends BufferedInputStream{

  override def read():Int={ 0 }

}

trait IterateInputStream extends InputStream with Iterator[Byte]{

}