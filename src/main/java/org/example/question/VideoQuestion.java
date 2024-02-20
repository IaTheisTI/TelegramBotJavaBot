package org.example.question;

public class VideoQuestion extends AbstractQuestion {
    public VideoQuestion() {
        super("I5 12400 cpu z оснащен ... вычислительными ядрами, " +
                "которые могут обрабатывать до 20 потоков параллельно");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("12");
    }
}
