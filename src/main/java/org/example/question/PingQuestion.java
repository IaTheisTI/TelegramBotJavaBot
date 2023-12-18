package org.example.question;

public class PingQuestion extends AbstractQuestion{
    public PingQuestion() {
        super("Как посмотреть ping для 77.88.8.8 через cmd?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.contains("ping");
    }
}
