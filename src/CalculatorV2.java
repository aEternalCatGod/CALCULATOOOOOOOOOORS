import java.util.Scanner;

public class CalculatorV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Type in a calculation...");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.exit(1);
            }

            enum Operation {
                ADD,
                SUBTRACT,
                MULTIPLY,
                DIVIDE

            }
            Operation selectedOperation = null;

            double number1 = 0;
            double number2 = 0;

            int operatorIndex = 0;

            if (input.contains("+")) {
                selectedOperation = Operation.ADD;
                operatorIndex = input.indexOf("+");

            } else if (input.contains("-")) {
                selectedOperation = Operation.SUBTRACT;
                operatorIndex = input.indexOf("-");

            } else if (input.contains("*")) {
                selectedOperation = Operation.MULTIPLY;
                operatorIndex = input.indexOf("*");

            } else if (input.contains("/")) {
                selectedOperation = Operation.DIVIDE;
                operatorIndex = input.indexOf("/");

            } else {
                System.out.println("ERROR: Not an Calculation");
                continue;
            }

            String calculationPart1 = input.substring(0, operatorIndex);
            String calculationPart2 = input.substring(operatorIndex + 1);

            try {
                number1 = Double.parseDouble(calculationPart1);
            } catch (Exception e) {
                System.out.println("ERROR: Text ist not a number: " + calculationPart1);
                continue;
            }

            try {
                number2 = Double.parseDouble(calculationPart2);
            } catch (Exception e) {
                System.out.println("ERROR: Text ist not a number: " + calculationPart2);
                continue;
            }


            if (selectedOperation == null) {
                continue;
            }

            String output = null;

            switch (selectedOperation) {
                case Operation.ADD -> output = String.valueOf(number1 + number2);
                case Operation.SUBTRACT -> output = String.valueOf(number1 - number2);
                case Operation.MULTIPLY -> output = String.valueOf(number1 * number2);
                case Operation.DIVIDE -> output = String.valueOf(number1 / number2);
            }
            System.out.println("=" + output);
        }
    }
}
