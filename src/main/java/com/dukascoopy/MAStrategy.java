//package com.dukascoopy;
//
//import com.dukascopy.api.*;
//
///**
// * Created by lenovo on 2015/9/12.
// */
//public class MAStrategy implements IStrategy{
//    private IEngine engine = null;
//    private IIndicators indicators = null;
//    private int tagCounter = 0;
//    private double[] ma1 = new double[Instrument.values().length];
//    private IConsole console;
//
//    public void onStart(IContext context) throws JFException {
//        engine = context.getEngine();
//        indicators = context.getIndicators();
//        this.console = context.getConsole();
//        console.getOut().println("Started");
//    }
//
//    public void onStop() throws JFException {
//        for (IOrder order : engine.getOrders()) {
//            order.close();
//        }
//        console.getOut().println("Stopped");
//    }
//
//    public void onTick(Instrument instrument, ITick tick) throws JFException {
//        if (ma1[instrument.ordinal()] == -1) {
//            ma1[instrument.ordinal()] = indicators.ema(instrument, Period.TEN_SECS, OfferSide.BID, IIndicators.AppliedPrice.MEDIAN_PRICE, 14, 1);
//        }
//        double ma0 = indicators.ema(instrument, Period.TEN_SECS, OfferSide.BID, IIndicators.AppliedPrice.MEDIAN_PRICE, 14, 0);
//        if (ma0 == 0 || ma1[instrument.ordinal()] == 0) {
//            ma1[instrument.ordinal()] = ma0;
//            return;
//        }
//
//        double diff = (ma1[instrument.ordinal()] - ma0) / (instrument.getPipValue());
//
//        if (positionsTotal(instrument) == 0) {
//            if (diff > 1) {
//                engine.submitOrder(getLabel(instrument), instrument, IEngine.OrderCommand.SELL, 0.001, 0, 0, tick.getAsk()
//                        + instrument.getPipValue() * 10, tick.getAsk() - instrument.getPipValue() * 15);
//            }
//            if (diff < -1) {
//                engine.submitOrder(getLabel(instrument), instrument, IEngine.OrderCommand.BUY, 0.001, 0, 0, tick.getBid()
//                        - instrument.getPipValue() * 10, tick.getBid() + instrument.getPipValue() * 15);
//            }
//        }
//        System.out.println("MAStrategy.onTick "+ma0);
//        ma1[instrument.ordinal()] = ma0;
//    }
//
//    public void onBar(Instrument instrument, Period period, IBar askBar, IBar bidBar) {
//    }
//
//    //count open positions
//    protected int positionsTotal(Instrument instrument) throws JFException {
//        int counter = 0;
//        for (IOrder order : engine.getOrders(instrument)) {
//            if (order.getState() == IOrder.State.FILLED) {
//                counter++;
//            }
//        }
//        return counter;
//    }
//
//    protected String getLabel(Instrument instrument) {
//        String label = instrument.name();
//        label = label.substring(0, 2) + label.substring(3, 5);
//        label = label + (tagCounter++);
//        label = label.toLowerCase();
//        return label;
//    }
//
//    public void onMessage(IMessage message) throws JFException {
//    }
//
//    public void onAccount(IAccount account) throws JFException {
//    }
//}
