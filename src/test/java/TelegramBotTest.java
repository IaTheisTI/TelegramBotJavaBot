import org.example.UserData;
import org.junit.Assert;
import org.junit.Test;

public class TelegramBotTest {

    /**
     * Проверяет, что конструктор UserData устанавливает значения score и current равными 0.
     */
    @Test
    public void testConstructorSetsValues(){
        int expectedScore = 0;
        int expectedCurrent = 0;

        UserData data = new UserData();

        Assert.assertEquals(expectedScore, data.getScore());
        Assert.assertEquals(expectedCurrent, data.getCurrent());
    }

    /**
     * Проверяет, что метод setScore увеличивает значение score на заданную величину.
     */
    @Test
    public void testSetScoreIncreasesValue(){
        UserData data = new UserData();
        int newScore = 10;
        data.setScore(newScore);

        Assert.assertEquals(newScore, data.getScore());
    }

    /**
     * Проверяет, что метод setCurrent обновляет значение current на заданную величину.
     */
    @Test
    public void testSetCurrentUpdatesValue(){
        UserData data = new UserData();
        int newCurrent = 5;

        data.setCurrent(newCurrent);

        Assert.assertEquals(newCurrent, data.getCurrent());
    }

}