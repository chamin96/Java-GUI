package com.foxploit;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    public static final int BASE_PORT = 2000;   // The base port for the server

    private ServerSocket serverSocket = null;   // Server Socket for main server
    private StockDB stockDatabase;              // Stock database

    /*
    * UI components
    */
    public static final String TITLE = "Auction Server";


    public Server(int socket, StockDB users) {
        /*
        * UI stuff
        */
        createView();

        setTitle(TITLE);
        setSize(500,400);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //*********************
        this.stockDatabase = users;

        try {
            this.serverSocket = new ServerSocket(socket);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new BorderLayout());
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
                System.out.println("A client has connected." + client);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }// end server_loop

}