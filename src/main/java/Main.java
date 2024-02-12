import java.util.ArrayList;
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
    while(true) {
        numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        if (numberOfPeople <= 1){
        System.out.println("Некорректное значение для подсчёта, попробуйте еще раз.");
    } else {
            break;
        }
    }
    System.out.println("Введите информацию о товарах.");
    while (true){
        System.out.println("Введите название товара:");
        String name = scanner.nextLine();
        if(name.equalsIgnoreCase("завершить")) {
            break;
        }

        double price = 0;
        boolean statusPrice = false;
        while (!statusPrice){
            System.out.println("Введите цену товара: ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                if (price > 0) {
                    statusPrice = true;
                } else {
                    System.out.println("Цена товара должна быть больше 0. Повторите ввод.");
                }
            } else {System.out.println("Неверная цена. Цена должна быть представлена числом, пожалуйста, повторите ввод.");
            scanner.next();}
        }
        goodsList.add(new Goods(name, price));
        System.out.println("Товар успешно добавлен!");
        System.out.println("Хотите добавить ещё один товар?");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("завершить")) {
            break;
        }
        scanner.nextLine();
    }
    System.out.println("\nДобавленные товары:");
    for (Goods goods : goodsList){
        System.out.println(goods.getName()+ ": " + goods.getPrice());}
    double totalCost = 0;
    for (Goods goods : goodsList) {
        totalCost += goods.getPrice();}
    double costForEach = totalCost / numberOfPeople;
    System.out.println("\nКаждый должен оплатить:");
    System.out.println(Formatter.format(costForEach));
    }
    }



class Goods {
    public String name;
    public double price;
    public Goods(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
}
class Formatter {
    public static String format(double price) {
        String formattedPrice = String.format("%.2f", price);
        int rubles = (int) price;
        if (rubles % 100 >= 11 && rubles % 100 <= 14){
        return formattedPrice + " рублей";}
        int lastNumbers = rubles % 10;
        switch (lastNumbers) {
            case 1:
        return formattedPrice + " рубль";
            case 2:
            case 3:
            case 4:
                return formattedPrice + " рубля";
            default:
                return formattedPrice + " рублей";
        }
    }
    }