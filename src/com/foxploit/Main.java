/*  CO225 Project II Auction Server
*   J.K.C.N.Jayasooriya - E/15/154
*   A.H.G.D.Jayalath    - E/15/142
 */

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
