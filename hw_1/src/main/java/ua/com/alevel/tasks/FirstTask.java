package ua.com.alevel.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {
    public static void firstTask() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a value:");
        String value = bufferedReader.readLine();

        char[] array = value.toCharArray();
        int res = 0;

        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                res += Integer.parseInt(String.valueOf(array[i]));
            }
        }
        System.out.println("Your value = " + res);
    }
}