import akka.actor.Actor;
import akka.actor.ActorCell;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.collection.immutable.List;
import stock.ActorFactory;


/**
 * Created by lenovo on 2015/7/9.
 */
public class OkMain {
    public static Object obj=new Object();
    public static int i=0;
    public static void main(String[] args) {
        Thread t1=new Thread(){
            public void run(){
                while(true){
                    add();
                    System.out.println("增加第"+i+"颗子弹");
                    synchronized(obj){
//                        ______【1】_______
                        obj.notify();
                    }
                    synchronized(obj){
                        try {
//                            ______【2】_______
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();}}}}};
        Thread t2=new Thread(){
            public void  run(){
                while(true){
                    synchronized(obj){
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("发射第"+i+"枚子弹");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//子弹一共13发，打完结束
//                    ________【4】_________
                    subtract();

                    synchronized(obj){
                        obj.notify();
                    }
                }
            }
        };
//        _________【5】___________

        t1.start();
        t2.start();
    }
    public static void add(){i++;}
    public static void subtract(){i--;}
}


