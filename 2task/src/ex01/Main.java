package ex01;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Демонстрація обчислення кількості одиниць та збереження/відновлення стану.
 * Містить реалізацію статичного метода main.
 * @author antonovs105
 * @version 0.0.1
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
                System.out.print("'q' - quit, 'v' - view, 'i' - input arguments, 'c' - calculate, 's' - save, 'r' - restore:  ");
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
                    System.out.println("Result:");
                    calc.show();
                    break;
                case 'i':
                    System.out.println("Enter four arguments (angles):");
                    try {
                        String[] angleStr = in.readLine().split(" ");
                        if (angleStr.length != 4) {
                            System.out.println("You need to enter 4 arguments");
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
                        System.out.println("Result saved");
                        calc.show();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 's':
                    System.out.println("Saved");
                    try {
                        calc.save();
                        System.out.println("State saved in file");
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    calc.show();
                    break;
                case 'r':
                    System.out.println("Restoring");
                    try {
                        calc.restore();
                        System.out.println("restored from file.");
                    } catch (Exception e) {
                        System.out.println("Deserialization error: " + e);
                    }
                    calc.show();
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