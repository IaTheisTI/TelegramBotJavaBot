package org.example.question;

public class ProgrammQuestion extends AbstractQuestion{
    private final int minimal = 2;
    private final String[] programm = {"JAVASCRIPT", "PYTHON", "JAVA", "TYPESCRIPT", "C#", "C++", "PHP", "SHELL", "C", "RUBY"};
    public ProgrammQuestion() {
        super("Напишите несколько языков программирования, " +
                "которые входят в топ 10 самых популярных в мире");
    }

    @Override
    public boolean checkAnswer(String answer) {
        int count = 0;
        for (String method : programm){
            if (answer.toUpperCase().contains(method)){
                count = count + 1;
            }

        }
        return count >= minimal;
    }
}
