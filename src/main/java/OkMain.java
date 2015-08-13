import akka.actor.ActorCell;
import scala.collection.immutable.List;


/**
 * Created by lenovo on 2015/7/9.
 */
public class OkMain {

    public static void main(String[] args) {
//        OkCoinSystem.main(args);
        List lis  = ActorCell.contextStack().get();
        if(lis.isEmpty()){
            System.out.println("null actorStack");
        }else{
            lis.head();
        }

    }
}
