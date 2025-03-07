package ex01;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Виконує тестування класів.
 * Перевіряє правильність обчислень та серіалізації/десеріалізації.
 * @author antonovs105
 * @version 0.0.1
 */
public class MainTest {

    /** Тест для перевірки обчислення кількості одиниць. */
    @Test
    public void testCalculateAverageUnits() {
        Calc calc = new Calc();
        double[] angles1 = {0, 0, 0, 0};
        int result1 = calc.calculateAverageUnits(angles1);
        assertEquals(0, result1);

        double[] angles2 = {90, 90, 90, 90};
        int result2 = calc.calculateAverageUnits(angles2);
        assertEquals(6, result2); // 1000 -> 1111101000 (6 одиниць)

        double[] angles3 = {0, 90, 180, 270};
        int result3 = calc.calculateAverageUnits(angles3);
        assertEquals(0, result3); // (0 + 1000 + 0 + (-1000)) / 4 = 0

        double[] angles4 = {30, 60, 45, 50}; 
        int result4 = calc.calculateAverageUnits(angles4);
        assertTrue(result4 >= 0); 
    }

    /** Тест для перевірки серіалізації та десеріалізації Item2d. */
    @Test
    public void testSerializationDeserialization() {
        Calc calc = new Calc();
        double[] angles = {10, 20, 30, 40};
        calc.calculateAverageUnits(angles); 
        Item2d originalResult = calc.getResult();
        originalResult.setTransientMessage("Message before serialization");

        try {
            calc.save();
        } catch (IOException e) {
            fail("Serialization error: " + e.getMessage());
        }

        Item2d restoredResult = null;
        try {
            calc.restore();
            restoredResult = calc.getResult();
        } catch (Exception e) {
            fail("Deserialization error: " + e.getMessage());
        }

        assertNotNull(restoredResult);
        assertEquals(originalResult.getUnitCount(), restoredResult.getUnitCount());
        assertTrue(Arrays.equals(originalResult.getAngles(), restoredResult.getAngles()));
        assertNull(restoredResult.getTransientMessage()); 
    }
}