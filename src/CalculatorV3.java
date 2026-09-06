import java.awt.*;
import java.math.BigInteger;
import java.util.Scanner;

public class CalculatorV3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "+ for add \n- for subtract \n* for multiply \n/ for divide \n^ for power");

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

            double power = Math.pow(number1, number2);

            switch (result.operation()) {
                case Operation.ADD -> output = String.valueOf(number1 + number2);
                case Operation.SUBTRACT -> output = String.valueOf(number1 - number2);
                case Operation.MULTIPLY -> output = String.valueOf(number1 * number2);
                case Operation.DIVIDE -> output = String.valueOf(number1 / number2);
                case Operation.POWER -> output = String.valueOf(power);
                case Operation.FACULTY -> output = String.valueOf(factorial((int) number1));
                default -> output = "ERROR";
            }
            System.out.println("=" + output);

        }
    }
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }


    private static ParseResult parseCalculation(String input) {
        Operation selectedOperation = null;

        double number1 = 0;
        double number2 = 0;

        double power = Math.pow(number1, number2);


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

        } else if (input.contains("^")) {
            selectedOperation = Operation.POWER;
            operatorIndex = input.indexOf("^");

        } else if (input.contains("!")) {
            selectedOperation = Operation.FACULTY;
            operatorIndex = input.indexOf("!");

        } else {
            System.out.println("ERROR: Not an Calculation");
            return new ParseResult(0.0, 0.0, 0, "ERROR: Not an Calculation", null);

        }

        String calculationPart1 = input.substring(0, operatorIndex);
        String calculationPart2 = input.substring(operatorIndex + 1);

        try {
            number1 = Double.parseDouble(calculationPart1);
        } catch (Exception e) {
            System.out.println("ERROR: Text ist not a number: " + calculationPart1);
            return new ParseResult(0.0, 0.0, 0, "ERROR: Text ist not a number: " + calculationPart1, null);
        }

        if (selectedOperation == Operation.FACULTY) {
            return new ParseResult(number1, 0.0, power, null, selectedOperation);
        }

        try {
            number2 = Double.parseDouble(calculationPart2);
        } catch (Exception e) {
            System.out.println("ERROR: Text ist not a number: " + calculationPart2);
            return new ParseResult(0.0, 0.0, power, "ERROR: Text ist not a number: " + calculationPart2, null);
        }

        return new ParseResult(number1, number2, power, null, selectedOperation);
    }

    enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        POWER,
        FACULTY
    }

    record ParseResult(double number1, double number2, double power, String error, Operation operation) {
    }
}
