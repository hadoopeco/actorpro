package com.okcoin.stock;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;
import java.util.Queue;

import com.alibaba.fastjson.JSON;
import com.okcoin.stock.impl.StockRestApi;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;



/**
 * 现货 REST API 客户端请求
 * @author zhangchi
 *
 */
public class StockClient {

	public static void main(String[] args) throws HttpException, IOException, InterruptedException {
		
	    String api_key = "6cf8b5b2-0d48-4da4-b0ea-118b4f6998dd";  //OKCoin申请的apiKey
	    String secret_key = "BFA9F2CE90106DE5B8AC1AB8C7138C38";  //OKCoin 申请的secret_key
 	    String url_prex = "https://www.okcoin.cn";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn
//	    String trade_coin = "ltc_usd";
	    String trade_coin = "btc_cny";
        Integer clen1 = 5;
        Integer clen2 = 30;
	    /**
	     * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
	     * 
	    */
	    IStockRestApi stockGet = new StockRestApi(url_prex);
		
	    /**
	     * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
	     * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入，
	     * 发送post请求之前，程序会做自动加密，生成签名。
	     * 
	    */
	    IStockRestApi stockPost = new StockRestApi(url_prex, api_key, secret_key);
        ArrayDeque<Float> queue = new ArrayDeque<Float>();
        ArrayDeque<Integer> director = new ArrayDeque<Integer>();

        String tst = stockPost.order_history("btc_cny", "1", "1", "10");
        System.out.println("StockClient.main ="+ tst);
//        float pre7 = 0;
//        float pre30 =0;
//        float buyprice =0;
//        float gain = 0;
//        float lastprice = 0;
//
//	    //现货行情
//        boolean buy = false;
//
//        while(true) {
//            String result = stockGet.ticker(trade_coin);
//            String tickerStr = StringUtils.substring(result,result.indexOf("\"ticker\":")+9,result.length()-1);
////            System.out.println("resu="+tickerStr);
//            Ticker ticker = JSON.parseObject(tickerStr, Ticker.class);
//            if(queue.size()>=clen2){
//                queue.poll();
//                queue.offer(ticker.getLast());
//
//                Object[] its =  queue.toArray();
//                float count =0;
//                float sum = 0;
//                float cur = 0;
//
//                /*
//                for(int i = 7;i>0;i--){
//                    cur = it.next();
//                    sum += cur*i;
//                    count +=i;
//
//                }
//
//
//
//                if(lastprice <= ticker.getLast()){
//                    director.push(1);
//                }else {
//                    director.push(-1);
//                }
//                int drict = 0;
//                if(director.size() >5){
//                    director.pollLast();
//                    for(Iterator<Integer> dit = director.iterator();dit.hasNext();){
//                        drict += dit.next();
//                    }
//                }
//
//
//
//
//                Iterator<Float> it2 =  queue.iterator();
//                float count2 =0;
//                float sum2 = 0;
//                for(int i = 30;i>0;i--){
//                    sum2 += it2.next()*i;
//                    count2 +=i;
//                }
//                float cur7 = sum/count;
//                float cur30 = sum2/count2;
//                if(pre7 < pre30 && cur7 > cur30 && !buy){
////                    if(drict >= 3) {
//                        buy = true;
//                        buyprice = ticker.getLast();
//
//                        System.out.println("buy =" + buyprice);
////                    }
////                    stockPost.trade(trade_coin, "buy", "50", "0.1");
//                }else if(pre7 > pre30 && cur7 < cur30 && buy){
//                    buy = false;
//                    gain += ticker.getLast() -buyprice;
//                    System.out.println("gain = " + gain);
//                }else{
//                    pre7 = cur7;
//                    pre30 = cur30;
//                }
//                */
//
//                for(int i = 1;i<8;i++){
//                    cur = (Float)its[30-i];
//                    sum += cur;
//                    count +=i;
//                }
//
//
//
////                if(lastprice <= ticker.getLast()){
////                    director.push(1);
////                }else {
////                    director.push(-1);
////                }
////                int drict = 0;
////                if(director.size() >9){
////                    director.pollLast();
////                    for(Iterator<Integer> dit = director.iterator();dit.hasNext();){
////                        drict += dit.next();
////                    }
////                }
//
//
//
//
//                float count2 =0;
//                float sum2 = 0;
//                for(int i = clen2-1;i>=0;i--){
//                    sum2 += (Float)its[i];
//                    count2 +=i;
//                }
//                float cur7 = sum/clen1;
//                float cur30 = sum2/clen2;
////                System.out.println("cur7 ="+cur7 +"  cur20="+cur30);
//                if(pre7 < pre30 && cur7 > cur30 && !buy){
////                    if(drict >= 3) {
//                    buy = true;
//                    buyprice = ticker.getLast();
//
//                    System.out.println("buy =" + buyprice +"time ="+new Date());
////                    }
//
//                    String buyresult = stockPost.trade(trade_coin, "buy", String.valueOf(buyprice+1), "0.1");
//                    if(buyresult.contains("error_code")) {
//                        System.out.println(" result = " + buyresult);
//
//                    }
//                }else if(pre7 > pre30 && cur7 < cur30 && buy){
//                    buy = false;
//                    float curprice = ticker.getLast();
//
////                    gain += ticker.getLast() -buyprice;
//                    stockPost.trade(trade_coin, "sell",  String.valueOf(curprice-1),"0.1");
//                    System.out.println("sell = " + curprice);
//                }else{
//                    pre7 = cur7;
//                    pre30 = cur30;
//                }
//            }else{
//                queue.offer(ticker.getLast());
//            }
//            lastprice = ticker.getLast();
//            Thread.sleep(1000);
//
//        }

            //现货市场深度
	/*
            //现货OKCoin历史交易信息
        String s =   stockGet.trades("btc_usd", "20");
        System.out.println(s);
        //现货用户信息
	    stockPost.userinfo();
		
	    //现货下单交易
//	    System.out.println(tradeResult);
//	    JSONObject tradeJSV1 = JSONObject.parseObject(tradeResult);
//	    String tradeOrderV1 = tradeJSV1.getString("order_id");

	    //现货获取用户订单信息
//            stockPost.order_info("btc_usd", tradeOrderV1);
//
//	    //现货撤销订单
//	    stockPost.cancel_order("btc_usd", tradeOrderV1);
		
	    //现货批量下单
	    stockPost.batch_trade("btc_usd", "buy", "[{price:50, amount:0.02}, {price:50, amount:0.03}]");

	    //批量获取用户订单
	    stockPost.orders_info("0", "btc_usd", "125420341, 125420342");
		
	    //获取用户历史订单信息，只返回最近七天的信息
	    stockPost.order_history("btc_usd", "0", "1", "20");
		
	*/
		
		
	}

}
