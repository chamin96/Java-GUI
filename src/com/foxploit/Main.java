package com.foxploit;

public class Main {

    public static void main(String[] args) {
        StockDB stockDatabase = new StockDB("stocks.csv", "Symbol", "Security Name", "Price");
        Server mainServer = new Server(Server.BASE_PORT, stockDatabase);
        mainServer.server_loop();
    }
}
