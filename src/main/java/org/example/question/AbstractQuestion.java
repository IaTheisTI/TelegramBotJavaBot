package org.example.question;

public abstract class AbstractQuestion {

    private final String question;

    public AbstractQuestion(String question) {

        this.question = question;
    }

    public String getQuestion(){
        return question;
    }

    public abstract boolean checkAnswer(String answer);
}
