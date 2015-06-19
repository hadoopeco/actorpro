package com.okcoin;

import com.alibaba.fastjson.JSON;
import com.okcoin.stock.IStockRestApi;
import com.okcoin.stock.bean.Ticker;
import com.okcoin.stock.impl.StockRestApi;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;

import java.io.IOException;

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/3/11
 * Time: 21:05
 */
public class OkcoinAccess {
    String api_key = "6cf8b5b2-0d48-4da4-b0ea-118b4f6998dd";  //OKCoin申请的apiKey
    String secret_key = "BFA9F2CE90106DE5B8AC1AB8C7138C38";  //OKCoin 申请的secret_key
    String url_prex = "https://www.okcoin.cn";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn
    String trade_coin = "btc_cny";
    IStockRestApi stockPost = new StockRestApi(url_prex, api_key, secret_key);

    public Ticker retrieveMsg() {
        Ticker ticker = null;
        synchronized (this) {
            try {
                stockPost = new StockRestApi(url_prex, api_key, secret_key);
                String result = stockPost.ticker(trade_coin);
                if (StringUtils.isNotEmpty(result)) {
                    String tickerStr = StringUtils.substring(result, result.indexOf("\"ticker\":") + 9, result.length() - 1);
                    ticker = JSON.parseObject(tickerStr, Ticker.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ticker;
    }

    public void buyTicker(Float buyprice){

        try {
            stockPost.trade(trade_coin, "buy", String.valueOf(buyprice+1), "0.15");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sellTicker(Float buyprice){
        try {
            stockPost.trade(trade_coin, "sell",  String.valueOf(buyprice-1),"0.15");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
