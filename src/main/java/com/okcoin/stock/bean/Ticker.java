package com.okcoin.stock.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/3/4
 * Time: 21:18
 */

public class Ticker {
    @JSONField(name="buy")
    private float buy;
    @JSONField(name="high")
    private float high;

    @JSONField(name="last")
    private float last;
    @JSONField(name="low")
    private float low;
    @JSONField(name="sell")
    private float sell;
    @JSONField(name="vol")
    private float vol;

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }
}
