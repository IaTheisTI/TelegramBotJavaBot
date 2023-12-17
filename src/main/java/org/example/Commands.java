package org.example;

import java.io.IOException;

public class Commands {
    public static String start(String request, String chatID) throws IOException {
        switch (request) {
            case("/question"):
                return "QWERTY";
            default:
                return "ff";
        }
    }
}
