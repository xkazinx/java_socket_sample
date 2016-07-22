package com.company;

import java.io.IOException;

public class Main {
    public static SocketHandler _socket;

    public static void main(String[] args) throws IOException {
        _socket = new SocketHandler(9090);
        _socket.Tick();
    }
}
