package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Control.GetInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
