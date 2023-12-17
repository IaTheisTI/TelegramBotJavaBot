package org.example.question;

public class VideoQuestion extends AbstractQuestion {
    public VideoQuestion() {
        super("Сколько нужно видеопамяти (мб) для минимальных настроек " +
                "Quake III Arena (процессор Pentium 233 Мгц)?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("8");
    }
}
