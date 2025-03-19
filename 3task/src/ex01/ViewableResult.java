package ex01;

/** ConcreteCreator
* (шаблон проектування Factory Method)<br>
* конкретна реалізація фабрикующого метода<br>
* Створює об'єкти для відображення результату
* @author antonovs105
* @version 0.0.1
* @see Viewable
* @see ViewableResult#getView()
*/
public class ViewableResult implements Viewable {
    /** Створює відображуємий об'єкт {@linkplain ViewResult} */
    @Override
    public View getView() {
        return new ViewResult();
    }
}
