package org.example.Bot;

import org.example.UserData;
import org.example.question.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ConcurrentHashMap;

public class FunctionQuestion extends TelegramLongPollingBot {

    private final AbstractQuestion[] questions;
    private final ConcurrentHashMap<Long, UserData> users;

    public FunctionQuestion() {
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

    public void sendText(long who, String what) {
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
            sendText(userId, "Можете посмотреть мой функционал при помощи команды /help");
        } else if (text.equals("/help")) {
            sendText(userId, "Я Telegram Bot, который поможет тебе решить простые арифметические задачи, а также могу предложить интересную игру!");
            sendText(userId, "Для вызова помощника для простых задач напиши /arithmetical");
            sendText(userId, "Для вызова игры воспользуйся командой /question");
        } else if (text.equals("/question")) {
            sendText(userId, "Добро пожаловать в игру \"вопрос - ответ\"!");
            sendText(userId, questions[0].getQuestion());
        } else if (text.equals("/stop")) {
            handleStopRequest(userId);
        } else if (text.equals("/arithmetical")) {
            sendText(userId, "Привет! Я Telegram Bot для решения арифметических задач с двумя числами.");
            sendText(userId, "Введите два числа и операцию через пробел, например: 5 10 +");
            sendText(userId, "Для подсчёта ответа напишите /answer, а далее пример, который требуется решить");
        } else if (text.startsWith("/answer")) {
            handleArithmeticQuestion(userId, text);
        } else if (text.startsWith("/")) {
            sendText(userId, "Неправильная команда. Попробуйте ещё раз или воспользуйтесь /help.");
        } else {
            handleQuizRequest(userId, text);
        }
    }

    public void handleQuizRequest(Long userId, String userResponse) {
        UserData data = users.get(userId);
        int current = data.getCurrent();

        if (current == questions.length) {
            sendText(userId, "Вы завершили опрос!");
        } else {
            boolean result = questions[current].checkAnswer(userResponse);
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


    public void handleStopRequest(long userId) {
        UserData data = users.get(userId);
        data.setCurrent(0);
        data.setScore(0);
        sendText(userId, "Вы вышли из команды /question");
    }

    public void handleArithmeticQuestion(long userId, String text) {
        UserData data = users.get(userId);

        // Extracting the numbers and operator from the user input
        String[] parts = text.split(" ");
        if (parts.length == 4) {
            try {
                double num1 = Double.parseDouble(parts[1]);
                double num2 = Double.parseDouble(parts[2]);
                String operator = parts[3];

                double result = performOperation(num1, operator, num2, userId);
                sendText(userId, "Результат: " + result);
            } catch (NumberFormatException e) {
                sendText(userId, "Ошибка ввода. Пожалуйста, введите числа и операцию в верном формате.");
            }
        } else {
            sendText(userId, "Некорректный ввод. Пожалуйста, введите два числа и операцию через пробел.");
        }
    }

    private double performOperation(double num1, String operator, double num2, long userId) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    sendText(userId, "Деление на ноль невозможно");
                    return Double.POSITIVE_INFINITY; // Handle division by zero
                }
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }
    }
}