import org.example.Bot.FunctionQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FunctionQuestionTest {

    private FunctionQuestion bot;

    @BeforeEach
    void setUp() {
        bot = Mockito.spy(new FunctionQuestion());
    }

    @Test
    void testStartCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/start");
        bot.onUpdateReceived(update);

        verify(bot, times(2)).sendText(anyLong(), anyString());
    }

    @Test
    void testHelpCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/help");
        bot.onUpdateReceived(update);

        verify(bot, times(3)).sendText(anyLong(), anyString());
    }

    @Test
    void testQuestionCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/question");
        bot.onUpdateReceived(update);

        verify(bot, times(3)).sendText(anyLong(), anyString());
    }

    @Test
    void testStopCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/stop");
        bot.onUpdateReceived(update);

        verify(bot, times(1)).handleStopRequest(anyLong());
    }

    @Test
    void testArithmeticalCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/arithmetical");
        bot.onUpdateReceived(update);

        verify(bot, times(3)).sendText(anyLong(), anyString());
    }

    @Test
    void testAnswerCommand() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("/answer 5 + 5");
        bot.onUpdateReceived(update);

        verify(bot, times(2)).sendText(anyLong(), anyString());
        verify(bot, times(1)).handleArithmeticQuestion(anyLong(), anyString());
    }

    @Test
    void testQuizRequest() throws TelegramApiException {
        Update update = new Update();
        update.getMessage().setText("User's quiz response");
        bot.onUpdateReceived(update);

        verify(bot, times(1)).handleQuizRequest(anyLong(), anyString());
    }
}

