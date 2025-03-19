package ex01;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Клас для зберігання параметрів (кутів) і результатів обчислень.
 * Реалізує інтерфейс {@link Serializable}
 * @author antonovs105
 * @version 0.0.1
 */
public class Item2d implements Serializable { 
    /** Масив вхідних аргументів (кутів). */
    private double[] angles; 
    /** Кількість одиниць у двійковому поданні цілої частини середнього арифметичного. */
    private int unitCount;  

    private transient String transientMessage;

    /** Згенерована константа для серіалізації. */
    private static final long serialVersionUID = 2L; 

    /**
     * Конструктор за замовчуванням.
     */
    public Item2d() {
        this.angles = new double[4]; 
        this.unitCount = 0;
    }

    /**
     * Конструктор для ініціалізації об'єкта результатом обчислень.
     * @param angles масив кутів
     * @param unitCount кількість одиниць
     */
    public Item2d(double[] angles, int unitCount) {
        this.angles = angles;
        this.unitCount = unitCount;
    }

    /**
     * Встановлює масив кутів.
     * @param angles масив кутів
     */
    public void setAngles(double[] angles) {
        this.angles = angles;
    }

    /**
     * Повертає масив кутів.
     * @return масив кутів
     */
    public double[] getAngles() {
        return angles;
    }

    /**
     * Встановлює кількість одиниць.
     * @param unitCount кількість одиниць
     */
    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    /**
     * Повертає кількість одиниць.
     * @return кількість одиниць
     */
    public int getUnitCount() {
        return unitCount;
    }

    /**
     * Встановлює transient повідомлення.
     * @param transientMessage повідомлення
     */
    public void setTransientMessage(String transientMessage) {
        this.transientMessage = transientMessage;
    }

    /**
     * Повертає transient повідомлення.
     * @return transient повідомлення
     */
    public String getTransientMessage() {
        return transientMessage;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Item2d{" + 
               "angles=" + Arrays.toString(angles) +
               ", unitCount=" + unitCount +
               ", transientMessage='" + transientMessage + '\'' +
               '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item2d other = (Item2d) obj;
        return unitCount == other.unitCount && Arrays.equals(angles, other.angles); 
    }
}