import java.util.Scanner;

    public class Main {
    public static void main(String[] args) throws Exception {
    System.out.println("Калькулятор умеет выполнять операции сложения строк, \n" +
            "вычитания строки из строки, умножения строки на число \n" +
            "и деления строки на число: \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b.\n" +
            "Значения строк передаваемых в выражении выделяются двойными кавычками. \n" +
            "Калькулятор принимает на вход числа от 1 до 10 включительно (не более), и строки длинной не более 10 символов.");
    Scanner scanner = new Scanner(System.in); //запускаю сканер
    String input = scanner.nextLine(); // считываю строку
    String[] value = input.replaceAll("\\s", "").split("\""); //удаляю пробелы и делю введенную строку по кавычкам
    if (!value[0].equals("")) {
        throw new Exception("Первым аргументом выражения должна быть строка");
    }
    if ((value.length != 3) && (value.length != 4)) {
        throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
    } else {
        String number1 = value[1];
        if (number1.length() > 10) {
            throw new Exception("Вы ввели длину строки более 10 символов");
        }
        if (value.length == 3) {
            String number2 = value[2];
            String number3 = value[2].replaceAll("[+\\-*/]", "");
            String operation = Operations.getOperator(number2);
            switch (operation) {
                case "*":
                    int count = Integer.parseInt(number3); // вторую строку представляю как число
                    if ((count < 1) || (count > 10)) {
                        throw new Exception("Вы ввели число меньше 1 или больше 10 ");
                    }
                    String umn = Operations.multiplication(number1, count);
                    if (umn.length() > 40) {
                        System.out.println("\"" + umn.substring(0, 40) + "..." + "\"");
                    } else {
                        System.out.println("\"" + umn + "\"");
                    }
                    break;
                case "/":
                    int counte = Integer.parseInt(number3); // вторую строку представляю как число
                    if ((counte < 1) || (counte > 10)) {
                        throw new Exception("Вы ввели число меньше 1 или больше 10");
                    }
                    String del = Operations.division(number1, counte);
                    System.out.println("\"" + del + "\"");
            }
        } else {
            String operation = value[2];
            String number2 = value[3];
            if (number2.length() > 10) {
                throw new Exception("Вы ввели длину строки более 10 символов");
            }
            switch (operation) {
                case "+":
                    String sum = Operations.summa(number1, number2);
                    System.out.println("\"" + sum + "\"");
                    break;
                case "-":
                    if (number1.contains(number2)) {
                        String raz = Operations.difference(number1, number2);
                        System.out.println("\"" + raz + "\"");
                    } else {
                        System.out.println("\"" + number1 + "\"");
                    }
                    break;
                case "/":
                    throw new Exception("нельзя делить строку на строку ");
                default:
                    throw new Exception("Строки нельзя умножать и делить (либо Вы ввели неверный знак) ");
            }
        }
    }
    }
    public class Operations {

        public static String multiplication(String number1, int count) {
            return number1.repeat(count); // умножение
        }

        public static String division(String number1, int counte) {
            return number1.substring(0, number1.length() / counte); //деление
        }

        public static String getOperator(String number2) throws Exception {
            for (int i = 0; i < number2.length(); i++) {
                char simbol = number2.charAt(i);
                if (simbol == '+' || simbol == '-' || simbol == '*' || simbol == '/') {
                    return "" + simbol;
                }
            }
            throw new Exception("Вы ввели не допустимый знак операции ");
        }

        public static String summa(String number1, String number2) {
            return number1 + number2; // сложение
        }

        public static String difference(String number1, String number2) {
            return number1.replaceAll(number2, ""); //вычитание
        }
    }
}
