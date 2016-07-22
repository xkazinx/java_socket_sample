package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SocketHandler {
    public SocketHandler(int port) throws IOException
    {
        ServerSocket _listener = new ServerSocket(port);
    }

    private ServerSocket _listener;

    public void Tick() throws IOException
    {
        try {
            while (true) {
                Socket socket = _listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            _listener.close();
        }
    }

}
