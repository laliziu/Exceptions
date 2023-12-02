package homework;

import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Введите строку: ");
            String str = scanner.nextLine();
            if(str.isEmpty()){
                throw new Exception("Вы ввели пустую строку");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            scanner.close();
        }
    }
}
