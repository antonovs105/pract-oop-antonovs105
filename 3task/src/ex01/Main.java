package ex01;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Демонстрація обчислення кількості одиниць та збереження/відновлення стану.
 * Містить реалізацію статичного метода main.
 * @author antonovs105
 * @version 0.0.2
 * @see Main#main
 */
public class Main {
    /** Об'єкт класу {@link Calc} виконує обчислення та серіалізацію. */
    private Calc calc = new Calc(); 

    /** Відображає меню та обробляє команди користувача. */
    private void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double[] angles = new double[4];
        int unitCount = 0; 

        do {
            do {
                System.out.println("\nCommand:");
                System.out.print("'q' - quit, 'v' - view all, 'l' - view last, 'i' - input angles, " +
                                "'c' - calc, 's' - save, 'r' - restore:  ");
                try {
                    s = in.readLine();
                } catch(IOException e) {
                    System.out.println("Input error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit");
                    break;
                case 'v':
                    System.out.println("All Results:");
                    calc.show();
                    break;
                case 'l':
                    System.out.println("Last Calc:");
                    calc.showLast();
                    break;
                case 'i':
                    System.out.println("Enter four angles:");
                    try {
                        String[] angleStr = in.readLine().split(" ");
                        if (angleStr.length != 4) {
                            System.out.println("Need 4 args");
                            break;
                        }
                        for (int i = 0; i < 4; i++) {
                            angles[i] = Double.parseDouble(angleStr[i]);
                        }
                        System.out.println("saved: " + Arrays.toString(angles));
                    } catch (IOException e) {
                        System.out.println("input error: " + e);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format");
                    }
                    break;
                case 'c':
                    System.out.println("Calcullating");
                    try {
                        unitCount = calc.calculateAverageUnits(angles); 
                        System.out.println("count: " + unitCount);
                        System.out.println("Result added");
                        calc.showLast();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 's':
                    System.out.println("Saved");
                    try {
                        calc.save();
                        System.out.println("Collection saved in file");
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;
                case 'r':
                    System.out.println("Restoring");
                    try {
                        calc.restore();
                        System.out.println("restored from file");
                        System.out.println("size: " + calc.getResults().size());
                    } catch (Exception e) {
                        System.out.println("Deserialization error: " + e);
                    }
                    break;
                default:
                    System.out.print("Invalid command");
            }
        } while(s.charAt(0) != 'q');
    }

    /** Виконується при запуску програми.
     * Запускає меню для взаємодії з користувачем.
     * @param args - параметри запуску програми.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}