package org.example;

import org.example.question.*;

import java.io.IOException;


public class Commands {
    private static AbstractQuestion[] questions;
    private static int currentQuestionIndex;

    public Commands() {
        questions = new AbstractQuestion[4];
        questions[0] = new VideoQuestion();
        questions[1] = new WindowsQuestion();
        questions[2] = new ProgrammQuestion();
        questions[3] = new PingQuestion();

        currentQuestionIndex = -1;
    }

    public String processAnswer(String answer) {
        if (currentQuestionIndex == -1) {
            return "Игра не начата! Напишите \"/question\" для начала игры.";
        }
        AbstractQuestion currentQuestion = questions[currentQuestionIndex];
        if (currentQuestion.checkAnswer(answer)) {
            currentQuestionIndex++;
            if (currentQuestionIndex == questions.length) {
                currentQuestionIndex = -1;
                return "Поздравляем! Вы ответили на все вопросы правильно. Игра завершена.";
            } else {
                AbstractQuestion nextQuestion = questions[currentQuestionIndex];
                return nextQuestion.getQuestion();
            }
        } else {
            return "Ответ неверный. Попробуйте еще раз.";
        }
    }

    public static String start(String request, String chatID) throws IOException {
        switch (request) {
            case("/question"):
                currentQuestionIndex = 0;
                AbstractQuestion firstQuestion = questions[currentQuestionIndex];
                System.out.println(firstQuestion.getQuestion());
                return "Добро пожаловать в игру \"вопрос - ответ\"! " + firstQuestion.getQuestion();
                case("/stop"):
                    currentQuestionIndex = -1;
                    return "Завершение игры \"вопрос - ответ\"!";
            default:
                return "Я вас не понимаю";
        }
    }
}