package ex01;

/** Creator
 * (шаблон проєктування Factory Method)<br>
 * Оголошує інтерфейс фабрикуючого методу
 * @author antonovs105
 * @version 0.0.1
 * @see Viewable#getView()
 */
public interface Viewable {
    /** Создаёт объект, реализующий {@linkplain View} */
    View getView();
}
