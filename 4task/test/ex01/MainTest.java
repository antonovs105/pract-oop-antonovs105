package ex01;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Виконує тестування класів.
 * Перевіряє правильність обчислень, серіалізації/десеріалізації,
 * та роботу фабричного шаблону проектування.
 * @author antonovs105
 * @version 0.0.4
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
        assertEquals(6, result2); 

        double[] angles3 = {0, 90, 180, 270};
        int result3 = calc.calculateAverageUnits(angles3);
        assertEquals(0, result3); 

        double[] angles4 = {30, 60, 45, 50}; 
        int result4 = calc.calculateAverageUnits(angles4);
        assertTrue(result4 >= 0); 
    }

    /** Тест для перевірки серіалізації та десеріалізації колекції результатів. */
    @Test
    public void testSerializationDeserialization() {
        Calc calc = new Calc();
        
        double[][] testAngles = {
            {10, 20, 30, 40},
            {50, 60, 70, 80},
            {90, 100, 110, 120}
        };
        
        for (double[] angles : testAngles) {
            calc.calculateAverageUnits(angles);
        }
        
        assertEquals(3, calc.getResults().size());
        
        // Запам'ятовуємо дані для порівняння після відновлення
        List<Item2d> originalResults = calc.getResults();
        Item2d lastResult = calc.getLastResult();
        
        lastResult.setTransientMessage("message");

        try {
            calc.save();
        } catch (IOException e) {
            fail("Serialization error: " + e.getMessage());
        }

        Calc newCalc = new Calc();
        
        try {
            newCalc.restore();
        } catch (Exception e) {
            fail("Deserialization error: " + e.getMessage());
        }
        
        // Перевіряємо відновлені результати
        List<Item2d> restoredResults = newCalc.getResults();
        
        assertNotNull(restoredResults);
        assertEquals(originalResults.size(), restoredResults.size());
        
        Item2d restoredLastResult = newCalc.getLastResult();
        assertNotNull(restoredLastResult);
        
        for (int i = 0; i < originalResults.size(); i++) {
            Item2d original = originalResults.get(i);
            Item2d restored = restoredResults.get(i);
            
            assertEquals(original.getUnitCount(), restored.getUnitCount());
            assertTrue(Arrays.equals(original.getAngles(), restored.getAngles()));
        }
        
        assertNull(restoredLastResult.getTransientMessage());
    }

    
}