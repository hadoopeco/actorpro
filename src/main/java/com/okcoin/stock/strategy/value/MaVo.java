package com.okcoin.stock.strategy.value;

/**
 * Author: weibin(weibin521@pingan.com.cn)
 * Date: 2015/3/14
 * Time: 21:44
 */
public class MaVo {
    private float ma1;

    private float ma2;

    private float m3;

    public MaVo(float ma1, float ma2, float m3) {
        this.ma1 = ma1;
        this.ma2 = ma2;
        this.m3 = m3;
    }

    public float getMa1() {
        return ma1;
    }

    public void setMa1(float ma1) {
        this.ma1 = ma1;
    }

    public float getMa2() {
        return ma2;
    }

    public void setMa2(float ma2) {
        this.ma2 = ma2;
    }

    public float getM3() {
        return m3;
    }

    public void setM3(float m3) {
        this.m3 = m3;
    }

    @Override
    public String toString() {
        return "MaVo{" +
                "ma1=" + ma1 +
                ", ma2=" + ma2 +
                ", m3=" + m3 +
                '}';
    }
}
