package ex01;

/** ConcreteCreator
* (шаблон проектування Factory Method)<br>
* Об'являє метод,
* "фабрикуючий" об'єкти
* @author antonovs105
* @version 0.0.2
* @see ViewableResult
* @see ViewableTable#getView()
*/
public class ViewableTable extends ViewableResult {
    /** Створює відображаємий об'єкт {@linkplain ViewTable} */
    @Override
    public View getView() {
        return new ViewTable();
    }
} 