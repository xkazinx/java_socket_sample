package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String serverAddress = "127.0.0.1";
            int port = 9091;
            ClientTask ct = new ClientTask(serverAddress,port);
            System.out.println(ct.GetDate());
            ct.EndConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
