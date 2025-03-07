import java.util.Scanner;

public class FirstTask {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");

        String name = scanner.nextLine();

        String greetingMessage = "Greetings, " + name + ". First task done.";

        System.out.println(greetingMessage);

        scanner.close();
    }
}