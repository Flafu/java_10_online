package ua.com.alevel;

public class Cats {
    String name;
    String color;
    String age;

    public static boolean validateName(String name) {
        if ((name.length() > 0) && name.matches("[a-zA-Zа-яА-Я]+") && name.length() <= 20) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateColor(String color) {
        if ((color.length() > 0) && color.matches("[a-zA-Zа-яА-Я]+") && color.length() <= 15) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateAge(int age) {
        if (age > 0 && age <= 30) {
            return true;
        } else {
            return false;
        }
    }
}
