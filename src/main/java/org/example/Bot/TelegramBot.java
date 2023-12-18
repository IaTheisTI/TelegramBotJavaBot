package org.example.Bot;

import org.example.UserData;
import org.example.question.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ConcurrentHashMap;


public class TelegramBot extends TelegramLongPollingBot {

    private final AbstractQuestion[] questions;
    private ConcurrentHashMap<Long, UserData> users;

    public TelegramBot() {
        questions = new AbstractQuestion[4];
        questions[0] = new VideoQuestion();
        questions[1] = new WindowsQuestion();
        questions[2] = new ProgrammQuestion();
        questions[3] = new PingQuestion();
        users = new ConcurrentHashMap<>();
    }

    @Override
    public String getBotUsername() {
        return "offtimingbot";
    }


    public void sendText(long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who)
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotToken() {
        return "6132329633:AAGwTqA0KEvSdAE17sMtou8vxly6uHlfTNE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var text = message.getText();
        var userId = message.getFrom().getId();
        if (text.equals("/start")) {
            UserData data = new UserData();
            users.put(userId, data);
            sendText(userId, "Привет! Я Telegram Bot написанный на Java");
        }
        else if (text.equals("/question")) {
            sendText(userId, "Добро пожаловать в игру \"вопрос - ответ\"!");
            sendText(userId, questions[0].getQuestion());
        }
        else if (text.equals("/stop")) {
            UserData data = users.get(userId);
            data.setCurrent(0);
            data.setScore(0);
            sendText(userId, "Вы вышли из команды /question");
        }
        else {
            UserData data = users.get(userId);
            int current = data.getCurrent();

            if (current == questions.length) {
                sendText(userId, "Вы завершили опрос!");
            }
            boolean result = questions[current].checkAnswer(text);
            if (result) {
                sendText(userId, "Правильно!");
                data.setScore(data.getScore() + 1);
                data.setCurrent(current + 1);
                if (current == questions.length - 1) {
                    sendText(userId, "Поздравляю, вы ответили на все вопросы!");
                } else {
                    sendText(userId, questions[data.getCurrent()].getQuestion());
                }
            } else {
                sendText(userId, "Неправильно! Попробуйте ещё раз.");
            }
        }
    }
}



