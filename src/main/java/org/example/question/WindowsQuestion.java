package org.example.question;

public class WindowsQuestion extends AbstractQuestion {
    public WindowsQuestion() {
        super("Какая версия Windows самая популярная в мире на момент 2024 года?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("10");
    }
}
