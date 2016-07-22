package com.company;

import Visual.GUI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new ClientTask("127.0.0.1", 9091);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
