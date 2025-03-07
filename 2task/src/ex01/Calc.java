package ex01;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Містить реалізацію методів для обчислення кількості одиниць і серіалізації/десеріалізації.
 * Використовує агрегування.
 * @author antonovs105
 * @version 0.0.1
 */
public class Calc {

    private static final String FNAME = "Item2d.bin"; 
    /** Зберігає результат обчислень. Об'єкт класу {@link Item2d} */
    private Item2d result; 

    /** Ініціалізує {@link Calc#result} */
    public Calc() {
        result = new Item2d();
    }

    /** Встановленя значення {@link Calc#result}*/
    public void setResult(Item2d result) {
        this.result = result;
    }

    /** Отримати значення {@link Calc#result}
     * @return значення посилання на об'єкт {@link Item2d}
     */
    public Item2d getResult() {
        return result;
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
     * Використовує агрегацію обчислень всередині класу {@link Calc}
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

        result.setAngles(angles); 
        result.setUnitCount(unitCount); 
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

    /** Виводить результат обчислень. */
    public void show() {
        System.out.println(result);
    }

    /** Зберігає {@link Calc#result} в файлі {@link Calc#FNAME}
     * @throws IOException
     */
    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new
                FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    /** Відновлює {@link Calc#result} з файла {@link Calc#FNAME}
     * @throws Exception
     */
    public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d)is.readObject(); 
        is.close();
    }
}