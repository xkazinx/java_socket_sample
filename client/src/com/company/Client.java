package com.company;

import Visual.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class Client implements Runnable {

    private BufferedReader _in;
    private PrintWriter _out;

    private Socket _client;

    private Control _control;

    private String _name;

    public Client(String serverAddress, int port, Control control) throws IOException {
        _client = new Socket(serverAddress, port);
        _in = new BufferedReader(new InputStreamReader(_client.getInputStream()));
        _out = new PrintWriter(_client.getOutputStream(), true);
        _control = control;
        new Thread(this).start();
    }

    public void SendMsg(String msg) {
        _out.println(msg);
    }

    public String get_name() {
        return _name;
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
                _name = _control.GetName();
                _out.println(_name);
                line = _in.readLine();
            }
            if (line.startsWith("NAMEACCEPTED"))
                _control.UserValidated();
            _out.println("Bienvenido al chat");
            while (true) {
                line = _in.readLine();
                if (line.startsWith("MESSAGE")) {
                    _control.AddMsg(line.substring(8) + "\n");
                }
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.exit(0);
        }
    }
}
