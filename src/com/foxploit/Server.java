package com.foxploit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int BASE_PORT = 2000;   // The base port for the server

    private ServerSocket serverSocket = null;   // Server Socket for main server
    private StockDB stockDatabase;              // Stock database

    public Server(int socket, StockDB users) {

        this.stockDatabase = users;

        try {
            this.serverSocket = new ServerSocket(socket);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /* each server will provide the following functions to
     * the public. Note that these are non-static
     */

    public boolean isAuthorized(String symbol) {
        return this.stockDatabase.findSecuritySymbol(symbol);
    }


    public void changePrice(String key, String newValue) {
        // should these be synchronized?
        // TODO : Check future about synchronized
        stockDatabase.changeSecurityPrice(key, newValue);
    }


    /* server will define how the messages should be posted
     * this will be used by the connection servers
     */

    public void postMSG(String msg) {
        // all threads print to same screen
        // TODO : Handle display
        System.out.println(msg);
    }

    public String authorizedOnce(String a) {
        // need to implement this.
        // TODO
        return null;
    }

    public void server_loop() {
        try {
            while (true) {
                Socket socket = this.serverSocket.accept();
                Client client = new Client(this);
                client.connectToServer(socket);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }// end server_loop

}
