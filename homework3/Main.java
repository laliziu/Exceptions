package homework.homework3;


import java.io.*;
import java.util.*;

/*Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом (данные вводятся одной строкой без запятых):
        Фамилия Имя Отчество, дата рождения, номер телефона, пол

        Форматы данных:
        фамилия, имя, отчество - строки
        дата рождения - строка формата dd.mm.yyyy
        номер телефона - целое беззнаковое число без форматирования
        пол - символ латиницей f или m.

        Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
        вернуть код ошибки. Создать метод, который обработает его и покажет пользователю сообщение, что он ввел
        меньше или больше данных, чем требуется.

        Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы
        данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные
        типы java или создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение
        с информацией, что именно неверно.

        Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии. В него в одну строку
        должны записаться полученные данные, вида

        Фамилия Имя Отчество, дата рождения, номер телефона, пол

        Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

        Не забудьте закрыть соединение с файлом.

        При возникновении проблемы с  чтением-записью в файл, исключение должно быть корректно обработано, пользователь
        должен увидеть стектрейс ошибки.
*/
public class Main {
    public static void main(String[] args) {

        start();

    }

    private static void start() {
        while (true) {
            optionMenu();
            int option = 0;
            try {
                option = Integer.parseInt(new Scanner(System.in).nextLine());
                if (option < 1 || option > 2) {
                    throw new WrongOptionException("Неверно выбрано действие!");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (option) {
                case 1 -> manualAdd();
                case 2 -> System.exit(0);
            }
        }
    }

    private static void manualAdd() {
        String newItem = inputByUser();
        checkInputData(newItem);
        try {
            saveToFile(newItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void optionMenu() {
        System.out.println("Выберите действие:\n" +
                "1. Ввод данных о пользователе\n" +
                "2. Выход");
    }

    private static void saveToFile(String newItem) throws IOException {
        String lastname = newItem.split(" ")[0].trim();
        String fileName = lastname.concat(".txt");
        File itemFile = new File(fileName);
        boolean isItemExist = false;

        if (itemFile.createNewFile()) {
            System.out.println("Создание нового файла "+fileName);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             FileWriter fw = new FileWriter(fileName, true);) {
            String str = null;
            while ((str = br.readLine()) != null) {
                if (str.equalsIgnoreCase(newItem)) {
                    isItemExist = true;
                }
            }
            if (!isItemExist) {
                fw.write(newItem+"\n");
            }
        }
    }

    private static void checkInputData(String newItem) {
        checkFullname(newItem);
        checkDate(newItem);
        checkPhoneNumber(newItem);
        checkGender(newItem);
    }

    private static void checkGender(String newItem) {
        String gender = newItem.split(" ")[5].trim();
        if (!gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("m")) {
            throw new WrongDataException("Некорректно введен пол");
        }
    }

    private static void checkPhoneNumber(String newItem) {
        String phoneNumber = newItem.split(" ")[4].trim();

        if (!phoneNumber.matches("\\d{10}")) {
            throw new WrongDataException("Некорректно введен номер телефона");
        }

    }

    private static void checkDate(String newItem) {
        String date = newItem.split(" ")[3].trim();
        String day = date.split("\\.")[0];
        String month = date.split("\\.")[1];
        String year = date.split("\\.")[2];

        if (!day.matches("\\d{2}")) {
            throw new WrongDataException("Некорректно введена дата - день!");
        }

        if (!month.matches("\\d{2}")) {
            throw new WrongDataException("Некорректно введена дата - месяц!");
        }

        if (!year.matches("\\d{4}")) {
            throw new WrongDataException("Некорректно введена дата - год!");
        }

    }

    private static void checkFullname(String newItem) {
        String lastname = newItem.split(" ")[0].trim();
        String firstname = newItem.split(" ")[1].trim();
        String middlename = newItem.split(" ")[2].trim();

        if (lastname.matches("[0-9]+") || lastname.equals("")) {
            throw new WrongDataException("Фамилия введена неверно!");
        }

        if (firstname.matches("[0-9]+") || firstname.equals("")) {
            throw new WrongDataException("Имя введено неверно!");
        }

        if (middlename.matches("[0-9]+") || middlename.equals("")) {
            throw new WrongDataException("Отчество введено неверно!");
        }

    }

    private static String inputByUser() {
        System.out.println("Введите одной строкой через пробел следущие данные:");
        System.out.println("Фамилия Имя Отчество");
        System.out.println("дата рождения в формате dd.mm.yyyy");
        System.out.println("номер телефона из 10 символов");
        System.out.println("пол - символ латиницей f или m");
        return checkInputSize(new Scanner(System.in).nextLine());
    }

    private static String checkInputSize(String newItem) {
        String[] newItemArr = newItem.trim().split(" ");
        if (newItemArr.length != 6) {
            throw new InputSizeException("Введено не верное количество данных, " +
                    "ожидается 6, введено " + newItemArr.length);
        }
        return newItem;
    }
}
