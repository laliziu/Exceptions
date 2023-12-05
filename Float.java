package homework;

import java.util.Scanner;

public class Float {
    public static void main(String[] args) {
    float num = CheckFloat();
        System.out.println(" Введенное число " + num);
    }

    private static float CheckFloat() {
        Scanner scanner = new Scanner(System.in);
        float num;
        while (true) {
            System.out.println("Введите дробное число: ");
            if (scanner.hasNextFloat()) {
                num = scanner.nextFloat();
                break;
            } else {
                System.out.println("Вы ввели не дробное число");
                scanner.nextLine();
            }

            }
        return num;

        }


    }


