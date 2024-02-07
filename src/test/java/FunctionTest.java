import org.example.Bot.Function;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FunctionTest {

    @Test
    void testStartCommand() throws TelegramApiException {
        Function bot = spy(new Function());
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.getMessage()).thenReturn(message);
        when(message.getText()).thenReturn("/start");
        when(message.getFrom().getId()).thenReturn(123L);
        bot.onUpdateReceived(update);
        verify(bot).sendText(eq(123L), startsWith("Привет!"));
        verify(bot).sendText(eq(123L), contains("/help"));

    }
}