package org.example;

import java.io.IOException;

public class Commands {
    public static String start(String request, String chatID) throws IOException {
        switch (request) {
            case("/question"):
                return "Добро пожаловать в игру \"вопрос - ответ\"! ";
            default:
                return "Я вас не понимаю";
        }
    }
}
