import java.util.Scanner;

public class CalculatorV3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Type in a calculation...");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.exit(1);
            }

            ParseResult result = parseCalculation(input);

            if (result.error != null) {
                continue;
            }

            String output = null;

            double number1 = result.number1;
            double number2 = result.number2;

            switch (result.operation()) {
                case Operation.ADD -> output = String.valueOf(number1 + number2);
                case Operation.SUBTRACT -> output = String.valueOf(number1 - number2);
                case Operation.MULTIPLY -> output = String.valueOf(number1 * number2);
                case Operation.DIVIDE -> output = String.valueOf(number1 / number2);
            }
            System.out.println("=" + output);


        }
    }

    private static ParseResult parseCalculation(String input) {
        Operation selectedOperation;


        double number1;
        double number2;

        int operatorIndex;

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
            return new ParseResult(0.0, 0.0, "ERROR: Not an Calculation", null);

        }

        String calculationPart1 = input.substring(0, operatorIndex);
        String calculationPart2 = input.substring(operatorIndex + 1);

        try {
            number1 = Double.parseDouble(calculationPart1);
        } catch (Exception e) {
            System.out.println("ERROR: Text ist not a number: " + calculationPart1);
            return new ParseResult(0.0, 0.0,  "ERROR: Text ist not a number: " + calculationPart1, null);
        }

        try {
            number2 = Double.parseDouble(calculationPart2);
        } catch (Exception e) {
            System.out.println("ERROR: Text ist not a number: " + calculationPart2);
            return new ParseResult(0.0, 0.0, "ERROR: Text ist not a number: " + calculationPart2, null);
        }

        return new ParseResult(number1, number2, null, selectedOperation);
    }

    enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE

    }
    record ParseResult (double number1, double number2, String error, Operation operation) {}
}
