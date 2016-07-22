package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class ClientTask {
    private Socket _client;

    public ClientTask(String serverAddress, int port) throws IOException {
        _client = new Socket(serverAddress, port);
    }

    public String GetDate() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(_client.getInputStream()));
        return input.readLine();
    }


    public void EndConnection() {
        try {
            _client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
