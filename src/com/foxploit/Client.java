/*  CO225 Project II Auction Server
 *   J.K.C.N.Jayasooriya - E/15/154
 *   A.H.G.D.Jayalath    - E/15/142
 */
package com.foxploit;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Client implements Runnable {

    // Constant values
    public static final int WAIT_AUTH = 0;
    public static final int HALF_WAY = 1;
    public static final int AUTH_DONE = 2;

    public static final String WAIT_AUTH_MSG = "Welcome to the Auction!\nEnter Username: ";
    public static final String INPUT_SYMBOL_MSG = "Enter symbol that you're bidding : ";
    public static final String INVALID_SYMBOL_MSG = "Invalid symbol!\nEnter a valid symbol : ";
    public static final String WAIT_BID_MSG = "Enter your bid: ";
    public static final String AUTH_DONE_MSG = "You are authorised to bid\n";
    public static final String BID_POSTED = "Your bid is posted\n";

    // Client props
    private String username = null;
    private String symbolName = null;
    private String bid = null;
    private Socket mySocket; // connection socket per thread
    private int currentState;
    private Server server;
    // TODO : use following if needed.
    //private String securityName = null;
    //private String securityPrice = null;

    // TODO : use given username for each thread id

    public Client(Server mainServer) {
        this.mySocket = null; // we will set this later
        this.currentState = WAIT_AUTH;
        //this.clientName = null;
        this.server = mainServer;
    }

    public void connectToServer(Socket socket) {
        this.mySocket = socket;
        Thread socketThread = new Thread(this);
        socketThread.start();
    }

    @Override
    public void run() {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));

            String line, outline;
            out.write(WAIT_AUTH_MSG);
            out.flush();
            for (line = in.readLine(); line != null && !line.equalsIgnoreCase("quit"); line = in.readLine()) {

                switch (currentState) {
                    case WAIT_AUTH:
                        this.username = line;
                        outline = INPUT_SYMBOL_MSG;
                        currentState = HALF_WAY;
                        break;
                    case HALF_WAY:
                        if (server.isAuthorized(line)) {
                            currentState = AUTH_DONE;
                            this.symbolName = line;
                            out.write(AUTH_DONE_MSG);
                            out.flush();
                            // TODO : Security name, etc
//                            this.securityName =
                            outline = WAIT_BID_MSG;
                        } else {
                            currentState = HALF_WAY;
                            outline = INVALID_SYMBOL_MSG;
                        }
                        break;
                    case AUTH_DONE:
                        try {
                            Double.parseDouble(line);
                        } catch (NumberFormatException e) {
                            outline = "Please enter a double value : ";
                            break;
                        }
                        this.bid = line;
                        server.changePrice(this.symbolName, this.bid);
                        server.addAndDisplayHistory(this.username + " Bids: " + line + " on " + new Date().toString());
                        out.write(BID_POSTED);
                        out.flush();
                        outline = WAIT_BID_MSG;
                        break;

                    default:
                        out.write("Undefined state");
                        out.flush();
                        return;
                }
                out.print(outline); // Send the said message
                out.flush(); // flush to network

            } // for

            // close everything
            out.close();
            in.close();
            this.mySocket.close();

        } // try
        catch (IOException e) {
            System.out.println(e);
        } // catch
    }
}
