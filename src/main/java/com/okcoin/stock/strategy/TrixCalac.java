package com.okcoin.stock.strategy;

import com.okcoin.stock.strategy.value.MaVo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/3/14
 * Time: 21:43
 */
public class TrixCalac {
    private static MaVo mashort = null;
    private static float trix = 1;
    private static float mtrix = 1;
    private static Queue<Float> queue = new ArrayDeque<Float>(20);

    public String calacTr(float price){
        String flag = "";
        if(mashort == null){
            mashort = new MaVo(price,price,price);
//            trix = price;
//            mtrix = price;
        }else{
            float ma1 = (2l*price + 11l*mashort.getMa1())/13l;
            float ma2 = (2l*ma1 + 11l*mashort.getMa2())/13l;
            float ma3 = (2l*ma2 + 11l*mashort.getM3())/13l;


            float curtrix = ((ma3 - mashort.getM3()) / mashort.getM3())* 100l;
            if(queue.size()>=20){
                queue.poll();
            }
            mashort = new MaVo(ma1,ma2,ma3);

            queue.add(curtrix);
            float cmtrix =0;
            if(queue.size()==20){
                Object[] tr= queue.toArray();
                float sum =0;
                for(int i=0;i<tr.length;i++){
                    sum += (Float)tr[i];
                }
                cmtrix = sum / 20l;
            }

            System.out.println("queue ="+queue.size()+" ctrix="+curtrix+" cmtrix="+cmtrix+" trix="+trix+" mtrix="+mtrix);
            if(trix < mtrix && curtrix > cmtrix && cmtrix > mtrix){
                flag = "buy";
            }else if(trix > mtrix && curtrix < cmtrix && cmtrix < mtrix){
                flag ="sell";
            }
            trix = curtrix;
            mtrix = cmtrix;
        }
        return flag;
    }

}
