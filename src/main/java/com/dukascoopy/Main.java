//package com.dukascoopy;
//
//import com.dukascopy.api.Instrument;
//import com.dukascopy.api.system.ClientFactory;
//import com.dukascopy.api.system.IClient;
//import com.dukascopy.api.system.ISystemListener;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by lenovo on 2015/9/12.
// */
//public class Main {
//    //url of the DEMO jnlp
//    private static String jnlpUrl = "https://www.dukascopy.com/client/demo/jclient/jforex.jnlp";
//    //user name
//    private static String userName = "DEMO2wKdRW";
//    //password
//    private static String password = "wKdRW";
//
//    public static void main(String[] args) throws Exception {
//        final IClient client = ClientFactory.getDefaultInstance();
//        client.setSystemListener(new ISystemListener() {
//            private int lightReconnects = 3;
//
//            public void onStart(long processId) {
//            }
//
//            public void onStop(long processId) {
//                if (client.getStartedStrategies().size() == 0) {
//                    System.exit(0);
//                }
//            }
//
//            public void onConnect() {
//                lightReconnects = 3;
//            }
//
//            public void onDisconnect() {
//                if (lightReconnects > 0) {
//                    client.reconnect();
//                    --lightReconnects;
//                } else {
//                    try {
//                        //sleep for 10 seconds before attempting to reconnect
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        //ignore
//                    }
//                    try {
//                        client.connect(jnlpUrl, userName, password);
//                    } catch (Exception e) {
//                    }
//                }
//            }
//        });
//
//        //connect to the server using jnlp, user name and password
//        client.connect(jnlpUrl, userName, password);
//
//        //wait for it to connect
//        int i = 10; //wait max ten seconds
//        while (i > 0 && !client.isConnected()) {
//            Thread.sleep(1000);
//            i--;
//        }
//        if (!client.isConnected()) {
////            LOGGER.error("Failed to connect Dukascopy servers");
//            System.exit(1);
//        }
//
//        //subscribe to the instruments
//        Set<Instrument> instruments = new HashSet<Instrument>();
//        instruments.add(Instrument.EURUSD);
//        client.setSubscribedInstruments(instruments);
//
//        //start the strategy
//        client.startStrategy(new MAStrategy());
//        //now it's running
//    }
//}
