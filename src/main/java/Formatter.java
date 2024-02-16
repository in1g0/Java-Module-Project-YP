class Formatter {
    public static String format(double price) {
        String formattedPrice = String.format("%.2f", price);
        int rubles = (int) price;
        if (rubles % 100 >= 11 && rubles % 100 <= 14) {
            return formattedPrice + " рублей";
        }
        int lastNumbers = rubles % 10;
        return switch (lastNumbers) {
            case 1 -> formattedPrice + " рубль";
            case 2, 3, 4 -> formattedPrice + " рубля";
            default -> formattedPrice + " рублей";
        };
    }
}