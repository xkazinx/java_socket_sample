package com.company;

import Visual.GUI;

import java.io.IOException;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class Control {
    private static Control _instance;
    private Client _client;
    private GUI _windows;

    private Control() throws IOException {
        _windows = new GUI(this);
        _client = new Client("74.91.121.41", 9091, this);
    }

    public static Control GetInstance() throws IOException {
        if(_instance == null)
            _instance = new Control();
        return _instance;
    }

    public void SendMsg(String msg) {
        _client.SendMsg(msg);
    }

    public void EndSession() {
        _client.SendMsg(_client.get_name() + " ha abandonado el chat");
        _client.EndConnection();
        System.exit(0);
    }

    public String GetName() {
        return _windows.GetName();
    }

    public void UserValidated() {
        _windows.UserValidated();
    }

    public void AddMsg(String msg) {
        _windows.AddNewMsg(msg);
    }


}
