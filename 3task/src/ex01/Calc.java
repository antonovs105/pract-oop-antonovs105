package ex01;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Містить реалізацію методів для обчислення кількості одиниць і серіалізації/десеріалізації.
 * Використовує колекцію для зберігання результатів.
 * @author antonovs105
 * @version 0.0.2
 */
public class Calc {

    private static final String FNAME = "Item2dCollection.bin"; 
    /** Зберігає результати обчислень. Колекція об'єктів класу {@link Item2d} */
    private List<Item2d> results; 
    /** Останній результат обчислення */
    private Item2d lastResult;

    /** Ініціалізує {@link Calc#results} */
    public Calc() {
        results = new ArrayList<>();
        lastResult = null;
    }

    /** Отримати список всіх результатів
     * @return список результатів обчислень
     */
    public List<Item2d> getResults() {
        return results;
    }
    
    /** Отримати останній результат обчислення
     * @return останній результат обчислення
     */
    public Item2d getLastResult() {
        return lastResult;
    }
    
    /** Додати результат до колекції
     * @param result результат для додавання
     */
    public void addResult(Item2d result) {
        results.add(result);
        lastResult = result;
    }

    /** Обчислює значення функції sin(x).
     * @param x - аргумент вичисляємої функції в градусах.
     * @return результат обчислення функції sin(x).
     */
    public double calcSin(double x) {
        return Math.sin(x * Math.PI / 180);
    }

    /**
     * Обчислює кількість одиниць у двійковому вигляді.
     * Зберігає результат в колекції.
     * @param angles масив з чотирьох довільних аргументів (кутів у градусах).
     * @return кількість одиниць у двійковому поданні середнього арифметичного
     */
    public int calculateAverageUnits(double[] angles) {
        if (angles == null || angles.length != 4) {
            throw new IllegalArgumentException("err");
        }

        double[] functionValues = new double[4];
        for (int i = 0; i < 4; i++) {
            functionValues[i] = 1000 * calcSin(angles[i]); 
        }

        double averageValue = 0;
        for (double val : functionValues) {
            averageValue += val;
        }
        averageValue /= 4.0;

        int integerPart = (int) averageValue;
        int unitCount = countSetBits(integerPart);

        lastResult = new Item2d(angles.clone(), unitCount);
        results.add(lastResult);
        
        return unitCount;
    }


    /**
     * Підраховує кількість встановлених бітів (одиниць) у двійковому поданні цілого числа.
     * Допоміжний метод для {@link #calculateAverageUnits(double[])}
     * @param n ціле число
     * @return кількість одиниць у двійковому поданні числа
     */
    private int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    /** Виводить результати обчислень. */
    public void show() {
        if (results.isEmpty()) {
            System.out.println("No saved results");
            return;
        }
        
        System.out.println("All results (" + results.size() + "):");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ": " + results.get(i));
        }
    }
    
    /** Виводить останній результат обчислення. */
    public void showLast() {
        if (lastResult == null) {
            System.out.println("No last result");
            return;
        }
        System.out.println("Last result: " + lastResult);
    }

    /** Зберігає колекцію {@link Calc#results} в файлі {@link Calc#FNAME}
     * @throws IOException
     */
    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new
                FileOutputStream(FNAME));
        os.writeObject(results);
        os.flush();
        os.close();
    }

    /** Відновлює колекцію {@link Calc#results} з файла {@link Calc#FNAME}
     * @throws Exception
     */
    public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        results = (List<Item2d>)is.readObject();
        if (!results.isEmpty()) {
            lastResult = results.get(results.size() - 1);
        }
        is.close();
    }
}