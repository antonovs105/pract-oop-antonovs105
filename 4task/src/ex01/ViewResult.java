package ex01;
import java.io.IOException;
import java.util.List;

/** ConcreteProduct
* (Шаблон проектирования
* Factory Method)<br>
* Класс для отображения
* результатов вычислений,
* реализующий интерфейс View
* @author xone
* @version 2.0
* @see View
*/
public class ViewResult implements View {
    /** Коллекция аргументов и результатов вычислений */
    protected List<Item2d> items;

    /** Конструктор за замовчуванням. */
    public ViewResult() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewInit(Calc calc) {
        // Генеруємо тестові дані - 4 випадкових кути
        double[] angles = new double[4];
        for (int i = 0; i < 4; i++) {
            angles[i] = Math.random() * 360.0;
        }
        
        // Виконуємо обчислення, яке автоматично додає результат до колекції
        calc.calculateAverageUnits(angles);
        
        // Отримуємо колекцію результатів
        this.items = calc.getResults();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewSave(Calc calc) throws IOException {
        calc.save(); // Використовуємо метод save з Calc
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewRestore(Calc calc) throws Exception {
        calc.restore(); // Використовуємо метод restore з Calc
        this.items = calc.getResults(); // Отримуємо відновлену колекцію з Calc
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewHeader() {
        System.out.println("Results of Calculations:");
        System.out.println("-----------------------");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewBody() {
        if (items != null && !items.isEmpty()) {
            for (Item2d item : items) {
                System.out.println("Angles: " + java.util.Arrays.toString(item.getAngles()) + 
                                  ", Unit Count: " + item.getUnitCount());
            }
        } else {
            System.out.println("No results to display.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewFooter() {
        System.out.println("-----------------------");
        System.out.println("End of Results.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
}