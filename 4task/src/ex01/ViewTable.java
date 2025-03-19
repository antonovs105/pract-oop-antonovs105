package ex01;

import java.util.Formatter;

/** ConcreteProduct
* (шаблон проектирования
* Factory Method)<br>
* Вивід результатів у вигляді таблиці
* @author antonovs105
* @version 0.0.2
* @see ViewResult
*/
public class ViewTable extends ViewResult {
    /** Ширина таблиці */
    private static final int WIDTH = 80;
    
    /** Встановлює стандартну ширину таблиці.
     * Викликає конструктор суперкласу {@linkplain ViewResult#ViewResult() ViewResult()}
     */
    public ViewTable() {
        
    }
    
    /** Виводить вертикальний розділювач шириною {@linkplain ViewTable#WIDTH} символів */
    private void outLine() {
        for(int i = WIDTH; i > 0; i--) {
            System.out.print('-');
        }
    }
    
    /** Викликає {@linkplain ViewTable#outLine()}; завершує вивід розділювачем рядка */
    private void outLineLn() {
        outLine();
        System.out.println();
    }
    
    /** Виводить заголовок таблиці шириною {@linkplain ViewTable#WIDTH} символів */
    private void outHeader() {
        outLineLn();
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-20s |\n", "Angle 1", "Angle 2", "Angle 3", "Angle 4", "Binary Units Count");
        outLineLn();
    }
    
    /** Виводить тіло таблиці шириною {@linkplain ViewTable#WIDTH} символів */
    private void outBody() {
        if (items == null || items.isEmpty()) {
            System.out.println("| No data available " + " ".repeat(WIDTH - 20) + "|");
            return;
        }
        
        for(Item2d item : items) {
            double[] angles = item.getAngles();
            
            if (angles != null && angles.length == 4) {
                System.out.printf("| %-10.2f | %-10.2f | %-10.2f | %-10.2f | %-20d |\n", 
                    angles[0], angles[1], angles[2], angles[3], item.getUnitCount());
            } else {
                System.out.printf("| %-45s | %-20d |\n", "Invalid angles data", item.getUnitCount());
            }
        }
    }
    
    /** 
     * Перезаписує метод viewInit в батьківському класі ViewResult,
     * щоб не додавати випадкові дані при показі існуючих результатів
     * {@inheritDoc}
     */
    @Override
    public void viewInit(Calc calc) {

        this.items = calc.getResults();
    }
    
    /** Вивід елемента таблиці<br>{@inheritDoc} */
    @Override
    public void viewHeader() {
        outHeader();
    }
    
    /** Вивід елемента таблиці<br>{@inheritDoc} */
    @Override
    public void viewBody() {
        outBody();
    }
    
    /** Вивід елемента таблиці<br>{@inheritDoc} */
    @Override
    public void viewFooter() {
        outLineLn();
    }
} 