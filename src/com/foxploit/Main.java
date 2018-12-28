package com.foxploit;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        StockDB stockDatabase = new StockDB("stocks.csv", "Symbol", "Security Name", "Price");
        Server mainServer = new Server(Server.BASE_PORT, stockDatabase);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainServer.setVisible(true);
            }
        });
        mainServer.server_loop();
    }
}
