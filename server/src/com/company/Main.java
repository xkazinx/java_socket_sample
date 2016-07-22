package com.company;

import java.io.IOException;

public class Main {
    public static SocketHandler _socket;

    public static void main(String[] args) throws IOException {
        _socket = new SocketHandler("127.0.0.1", 9091);
        _socket.Tick();
    }
}
