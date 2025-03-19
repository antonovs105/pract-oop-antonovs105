package ex01;

import java.io.IOException;

/** Product
 * (шаблон проектування Factory Method)<br>
 * інтерфейс фабрикуємих об'єктів<br>
 * Оголошує методи відображення об'єктів
 * @author antonovs105
 * @version 0.0.1
 */

public interface View {
    /** Відображує заголовок */
    void viewHeader();
    /** Відображує основну частину  */
    void viewBody();
    /** Відображує закінчення  */
    void viewFooter();
    /** Відображує цілий об'єкт  */
    void viewShow();
    /** Ініціалізує дані для відображення  */
    void viewInit(Calc calc);
    /** Збережує дані  */
    void viewSave(Calc calc) throws IOException;
    /** Відновлює збережені дані  */
    void viewRestore(Calc calc)throws Exception;
 }