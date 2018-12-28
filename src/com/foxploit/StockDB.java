/*  CO225 Project II Auction Server
 *   J.K.C.N.Jayasooriya - E/15/154
 *   A.H.G.D.Jayalath    - E/15/142
 */
package com.foxploit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StockDB {

    private static Map<String, String[]> stockList;
    private String[] fields;

    public StockDB(String csvFile, String key, String val1, String val2) {

        FileReader fileRd;
        BufferedReader reader;

        try {
            fileRd = new FileReader(csvFile);
            reader = new BufferedReader(fileRd);

            /* read the CSV file's first line which has
             * the names of fields.
             */

            String header = reader.readLine();
            fields = header.split(",");// keep field names

            // find where the key and the value are
            int keyIndex = findIndexOf(key);
            int val1Index = findIndexOf(val1);
            int val2Index = findIndexOf(val2);
            if (keyIndex == -1 || val1Index == -1 || val2Index == -1) {
                throw new IOException("CVS file does not have data");
            }

            // Hash map for Stock List
            stockList = new HashMap<String, String[]>();

            /* read each line, getting it split by ,
             * use the indexes to get the key and value
             */

            String[] tokens;
            for (String line = reader.readLine();
                 line != null;
                 line = reader.readLine()) {
                tokens = line.split(",");
                String[] tempValues = new String[2];
                try {
                    tempValues[0] = tokens[val1Index];
                    tempValues[1] = tokens[val2Index];
                    stockList.put(tokens[keyIndex], tempValues);
                } catch (NullPointerException e) {
                    System.out.println("Data store error!");
                    System.exit(-1);
                }
            }

            fileRd.close();
            reader.close();

        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed CSV file");
            System.out.println(e);
        }
    }

    // Find the index from the csv file for one field
    private int findIndexOf(String searchKey) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(searchKey)) return i;
        }
        return -1;
    }

    // Find Security Symbol
    public boolean findSecuritySymbol(String key) {
        return stockList.containsKey(key);
    }

    // Find Security Name
    public String findSecurityName(String key) {
        return stockList.get(key)[0];
    }

    // Find Security Price
    public String findSecurityPrice(String key) {
        return stockList.get(key)[1];
    }

    // Change Security Price
    public void changeSecurityPrice(String key, String newValue) {
        stockList.get(key)[1] = newValue;
        System.out.println("changed one : "+stockList.get(key)[1]);
    }

    public void printMap(){
        for (String item : stockList.keySet()) {
            System.out.println("key: " + item);
        }
    }
}
