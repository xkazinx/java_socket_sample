package com.company;

import Visual.GUI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new ClientTask("74.91.121.41", 9091);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
