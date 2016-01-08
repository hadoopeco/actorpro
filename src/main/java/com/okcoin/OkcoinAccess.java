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
    String api_key = "442d9f7a-f794-4da9-ab81-cf20ace6e0ac";  //OKCoin申请的apiKey
    String secret_key = "67C1D3265DB40AFEE8E67A065C476EFE";  //OKCoin 申请的secret_key
    String url_prex = "https://www.okcoin.cn";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn
    String trade_coin = "btc_cny";
    String trade_amount = "0.09";
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

    public void buyTicker(Float curprice){
        synchronized (this) {
            try {
                stockPost.trade(trade_coin, "buy", String.valueOf(curprice + 10), trade_amount);
            } catch (HttpException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sellTicker(Float curprice){
        synchronized (this) {
            try {
                stockPost.trade(trade_coin, "sell", String.valueOf(curprice - 10), trade_amount);
            } catch (HttpException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
