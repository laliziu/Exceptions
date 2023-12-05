package homework;

import java.util.Random;

public class ex2 {
    public static void main(String[] args) {
        catchedRes1();
    }

    public static void catchedRes1() {
        int d = 0;
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length ; i++) {
            array[i] = random.nextInt(100);
        }
        try {
            double result = array[3] / d;
            System.out.println("result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }

}