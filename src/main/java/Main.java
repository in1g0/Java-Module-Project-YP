import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SplitCalculator splitCalculator = new SplitCalculator();
        splitCalculator.start();
    }
}

class SplitCalculator {
    public int numberOfPeople;
    public ArrayList<Goods> goodsList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Вас приветствует Калькулятор счёта! Пожалуйста, введите число персон, на которых необходимо раздеть счёт:");
        while (true)
            try {
                numberOfPeople = scanner.nextInt();
                scanner.nextLine();
                if (numberOfPeople <= 1) {
                    System.out.println("Некорректное значение для подсчёта(значение должно быть больше 1), попробуйте еще раз.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение для подсчёта, пожалуйста, попробуйте еще раз.");
                scanner.nextLine();
            }

        System.out.println("Введите информацию о товарах.");
        while (true) {
            System.out.println("Введите название товара:");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("завершить")) {
                break;
            }

            double price = 0;
            boolean statusPrice = false;
            while (!statusPrice) {
                System.out.println("Введите цену товара: ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    scanner.nextLine();
                    if (price > 0) {
                        statusPrice = true;
                    } else {
                        System.out.println("Цена товара должна быть больше 0. Повторите ввод.");
                    }
                } else {
                    System.out.println("Неверная цена. Цена должна быть представлена числом, пожалуйста, повторите ввод.");
                    scanner.next();
                }
            }
            goodsList.add(new Goods(name, price));
            System.out.println("Товар успешно добавлен!");
            System.out.println("Хотите добавить ещё один товар? Если нет - введите завершить");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("завершить")) {
                break;
            }

        }
        System.out.println("\nДобавленные товары:");
        for (Goods goods : goodsList) {
            System.out.println(goods.getName() + ": " + goods.getPrice());
        }
        double totalCost = 0;
        for (Goods goods : goodsList) {
            totalCost += goods.getPrice();
        }
        double costForEach = totalCost / numberOfPeople;
        System.out.println("\nКаждый должен оплатить:");
        System.out.println(Formatter.format(costForEach));
    }
}
