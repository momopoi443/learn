package machine;

import java.util.Objects;

public class CoffeeMachine {

    private static int money$ = 550;
    private static int waterMl = 400;
    private static int milkMl = 540;
    private static int beansG = 120;
    private static int cups = 9;

    private static int espWater = 250;
    private static int espBeans = 16;
    private static int espMoney = 4;

    private static int lattWater = 350;
    private static int lattMilk = 75;
    private static int lattBeans = 20;
    private static int lattMoney = 7;

    private static int cappWater = 200;
    private static int cappMilk = 100;
    private static int cappBeans = 12;
    private static int cappMoney = 6;

    MachineStates currentState = MachineStates.CHOOSING_AN_ACTION;

    private enum MachineStates{
        CHOOSING_AN_ACTION, CHOOSING_A_VARIANT_OF_COFFEE, ADDING_INGREDIENTS
    }

    private boolean resourceCheck(String type, int amount) {
        switch (type) {
            case "water" :
                if (amount > waterMl) {
                    return true;
                }
                break;
            case "milk" :
                if (amount > milkMl) {
                    return true;
                }
                break;
            case "beans" :
                if (amount > beansG) {
                    return true;
                }
                break;
            case "cups" :
                if (amount > cups) {
                    return true;
                }
                break;
        }
        return false;
    }

    private void actionChooser(String action) {

        switch (action) {
            case "buy" :
                currentState = MachineStates.CHOOSING_A_VARIANT_OF_COFFEE;
                break;
            case "fill" :
                currentState = MachineStates.ADDING_INGREDIENTS;
                break;
            case "take" :
                moneyReturn();
                break;
            case "remaining" :
                machineState();
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void addingIngredients(int[] amounts) {
        waterMl += amounts[0];

        milkMl += amounts[1];

        beansG += amounts[2];

        cups += amounts[3];


    }

    private void moneyReturn() {
        int buffer = money$;

        money$ = 0;

        System.out.println("I gave you $" + buffer);
    }

    private void machineState() {
        System.out.println("The coffee machine has:\n" +
                waterMl + " ml of water\n" +
                milkMl + " ml of milk\n" +
                beansG + " g of coffee beans\n" +
                cups + " disposable cups\n" +
                "$" + money$ + " of money\n");
    }

    private void makeCoffee(String command) {
        char type = command.charAt(0);

        boolean waterCheck;
        boolean milkCheck;
        boolean beansCheck;
        boolean cupsCheck;

        switch (type) {
            case '1' :
                waterCheck = resourceCheck("water", espWater);
                beansCheck = resourceCheck("beans", espBeans);
                cupsCheck = resourceCheck("cups", 1);

                if (waterCheck) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (beansCheck) {
                    System.out.println("Sorry, not enough beans!\n");
                } else if (cupsCheck) {
                    System.out.println("Sorry, not enough cups!\n");
                } else {
                    waterMl -= espWater;
                    beansG -= espBeans;
                    cups -= 1;
                    money$ += espMoney;
                    System.out.println("I have enough resources, making you a coffee!\n");
                }
                break;
            case '2' :
                waterCheck = resourceCheck("water", lattWater);
                milkCheck = resourceCheck("milk", lattMilk);
                beansCheck = resourceCheck("beans", lattBeans);
                cupsCheck = resourceCheck("cups", 1);

                if (waterCheck) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkCheck) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (beansCheck) {
                    System.out.println("Sorry, not enough beans!\n");
                } else if (cupsCheck) {
                    System.out.println("Sorry, not enough cups!\n");
                } else {
                    waterMl -= lattWater;
                    milkMl -= lattMilk;
                    beansG -= lattBeans;
                    cups -= 1;
                    money$ += lattMoney;
                    System.out.println("I have enough resources, making you a coffee!\n");
                }
                break;
            case '3' :
                waterCheck = resourceCheck("water", cappWater);
                milkCheck = resourceCheck("milk", cappMilk);
                beansCheck = resourceCheck("beans", cappBeans);
                cupsCheck = resourceCheck("cups", 1);

                if (waterCheck) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkCheck) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (beansCheck) {
                    System.out.println("Sorry, not enough beans!\n");
                } else if (cupsCheck) {
                    System.out.println("Sorry, not enough cups!\n");
                } else {
                    waterMl -= cappWater;
                    milkMl -= cappMilk;
                    beansG -= cappBeans;
                    cups -= 1;
                    money$ += cappMoney;
                    System.out.println("I have enough resources, making you a coffee!\n");
                }
                break;
            default :
                System.out.println("Wrong type");
                break;
        }
    }

    public void inputListener(String input, int... varargs) {
        switch (currentState) {
            case CHOOSING_AN_ACTION :
                actionChooser(input);
                break;
            case CHOOSING_A_VARIANT_OF_COFFEE:
                if (Objects.equals(input, "back")) {
                    currentState = MachineStates.CHOOSING_AN_ACTION;
                    break;
                }
                makeCoffee(input);

                currentState = MachineStates.CHOOSING_AN_ACTION;
                break;
            case ADDING_INGREDIENTS:
                addingIngredients(varargs);

                currentState = MachineStates.CHOOSING_AN_ACTION;
                break;
        }
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        boolean exit = true;
//
//        while (exit) {
//
//            String action;
//
//            System.out.println("Write action (buy, fill, take, remaining, exit):");
//            action = scanner.nextLine();
//
//            switch (action) {
//                case "buy" :
//                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte," +
//                            " 3 - cappuccino, back - to main menu:");
//                    String coffType = scanner.nextLine();
//
//                    if (Objects.equals(coffType, "back")) {
//                        break;
//                    }
//
//                    makeCoffee(coffType);
//                    break;
//                case "fill" :
//                    System.out.println("Write how many ml of water you want to add:");
//                    int waterAdd = scanner.nextInt();
//                    waterMl += waterAdd;
//
//                    System.out.println("Write how many ml of milk you want to add:");
//                    int milkAdd = scanner.nextInt();
//                    milkMl += milkAdd;
//
//                    System.out.println("Write how many grams of coffee beans you want to add:");
//                    int beansAdd = scanner.nextInt();
//                    beansG += beansAdd;
//
//                    System.out.println("Write how many disposable cups of coffee you want to add:");
//                    int cupsAdd = scanner.nextInt();
//                    cups += cupsAdd;
//                    scanner.nextLine();
//                    break;
//                case "take" :
//                    int buffer = money$;
//
//                    money$ = 0;
//
//                    System.out.println("I gave you $" + buffer);
//                    break;
//                case "remaining" :
//                    machineState();
//                    break;
//                case "exit" :
//                    exit = false;
//                    break;
//                default :
//                    System.out.println("Wrong command");
//                    break;
//            }
//        }
//    }
}
