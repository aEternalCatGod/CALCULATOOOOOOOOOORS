import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        System.out.println("Type in a calculation...");
        Scanner scanner = new Scanner(System.in);

        int number1 = scanner.nextInt();

        System.out.println("Chose + or - or * or /");
        String operator = scanner.next();

        int number2 = scanner.nextInt();

        boolean add         = operator.contains("+");
        boolean subtract    = operator.contains("-");
        boolean multiply    = operator.contains("*");
        boolean divide      = operator.contains("/");

        if (add) {
            System.out.println("=" + (number1 + number2));

        } else if (subtract) {
            System.out.println("=" + (number1 - number2));

        } else if (multiply) {
            System.out.println("=" + (number1 * number2));

        } else  if (divide) {
            System.out.println("=" + (number1 / number2));

        }

    }
}
