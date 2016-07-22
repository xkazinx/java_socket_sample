package com.company;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by Kaazin on 22/07/2016.
 */
public class PeerHandler extends Thread
{
    public PeerHandler(Socket socket) {
        _peer = socket;
        System.out.println("Client connected");
    }

    private String _name;
    private Socket _peer;
    private BufferedReader _in;
    private PrintWriter _out;

    private void InitializeStreams() throws IOException
    {
        _in = new BufferedReader(new InputStreamReader(_peer.getInputStream()));
        _out = new PrintWriter(_peer.getOutputStream(), true);
    }

    public void run()
    {
        try {
            InitializeStreams();

            while (true) {
                _out.println("SUBMITNAME");
                _name = _in.readLine();
                if (_name == null)
                {
                    return;
                }

                synchronized (Main.Names) {
                    if (!Main.Names.contains(_name)) {
                        Main.Names.add(_name);
                        break;
                    }
                }
            }

            _out.println("NAMEACCEPTED");
            Main.Writers.add(_out);

            System.out.println("New Client accepted: " + _name);
            ProcessInput();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (_name != null)
            {
                Main.Names.remove(_name);
            }

            if (_out != null)
            {
                Main.Writers.remove(_out);
            }

            try {
                _peer.close();
            }
            catch (IOException e)
            {

            }
        }
    }

    private void ProcessInput() throws IOException
    {
        while (true)
        {
            String input = _in.readLine();
            if (input == null)
            {
                return;
            }

            System.out.println("New packet received");

            for (PrintWriter writer : Main.Writers)
            {
                writer.println("MESSAGE " + _name + ": " + input);
            }
        }
    }
}
