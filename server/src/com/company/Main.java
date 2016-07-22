package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class Main {
    public static HashSet<String> Names = new HashSet<>();
    public static HashSet<PrintWriter> Writers = new HashSet<>();

    public static SocketHandler _socket;

    public static void main(String[] args) throws IOException {
        _socket = new SocketHandler("127.0.0.1", 9091);
        _socket.Tick();
    }
}
