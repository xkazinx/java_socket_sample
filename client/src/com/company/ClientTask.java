package com.company;

import Visual.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class ClientTask implements Runnable {

    private BufferedReader _in;
    private PrintWriter _out;

    private Socket _client;

    private GUI _windows;

    public ClientTask(String serverAddress, int port) throws IOException {
        _client = new Socket(serverAddress, port);
        _in = new BufferedReader(new InputStreamReader(_client.getInputStream()));
        _out = new PrintWriter(_client.getOutputStream(), true);
        _windows = new GUI();
        new Thread(this).start();
    }

    public void EndConnection() {
        try {
            _client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        try {
            String line = _in.readLine();
            while (line.startsWith("SUBMITNAME")) {
                String name = _windows.GetName();
                _out.println(name);
                line = _in.readLine();
            }
            if (line.startsWith("NAMEACCEPTED"))
                _windows.UserValidated();
            _out.println("Bienvenido al chat");
            while (true) {
                line = _in.readLine();
                if (line.startsWith("MESSAGE")) {
                    _windows.AddNewMsg(line.substring(8) + "\n");
                }
                String aux = _windows.GetNewMsg();
                if (aux != null) {
                    System.out.println(aux);
                    _out.println(aux);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
