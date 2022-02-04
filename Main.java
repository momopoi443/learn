package machine;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        boolean exit = true;

        while (exit) {

            String input;

            System.out.println("Write action (buy, fill, take, remaining, exit):");
            input = scanner.nextLine();

            switch (input) {
                    case "buy" :
                        coffeeMachine.inputListener(input);

                        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte," +
                                " 3 - cappuccino, back - to main menu:");
                        String coffType = scanner.nextLine();

                        coffeeMachine.inputListener(coffType);
                        break;
                    case "fill" :
                        coffeeMachine.inputListener(input);

                        System.out.println("Write how many ml of water you want to add:");
                        int waterAdd = scanner.nextInt();

                        System.out.println("Write how many ml of milk you want to add:");
                        int milkAdd = scanner.nextInt();

                        System.out.println("Write how many grams of coffee beans you want to add:");
                        int beansAdd = scanner.nextInt();

                        System.out.println("Write how many disposable cups of coffee you want to add:");
                        int cupsAdd = scanner.nextInt();
                        scanner.nextLine();

                        coffeeMachine.inputListener(input, waterAdd, milkAdd, beansAdd, cupsAdd);
                        break;
                    case "take" :
                        coffeeMachine.inputListener(input);
                        break;
                    case "remaining" :
                        coffeeMachine.inputListener(input);
                        break;
                    case "exit" :
                        exit = false;
                        break;
                    default :
                        System.out.println("Wrong command");
                        break;
            }
        }
    }
}
