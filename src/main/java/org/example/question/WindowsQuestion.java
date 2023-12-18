package org.example.question;

public class WindowsQuestion extends AbstractQuestion {
    public WindowsQuestion() {
        super("Какая последняя версия Windows?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("11");
    }
}
