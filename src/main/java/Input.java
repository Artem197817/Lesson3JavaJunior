

import java.util.Scanner;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public String input (String message){
        System.out.println(message);
        return scanner.nextLine();
    }
    public Integer inputInt(String message) {
        int result;
        try {
            result = Integer.parseInt(input(message));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа. Введите число еще раз");
            result = inputInt(message);
        }
        return result;
    }

    public Double inputDouble (String message) {
        double result;
        try {
            result = Double.parseDouble(input(message));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа. Введите число еще раз");
            result = inputDouble(message);
        }
        return result;
    }
}
