package ua.com.alevel.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondTask {
    public static void secondTask() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a value:");
        String info = bufferedReader.readLine();

        char[] array = info.toCharArray();
        int[] compare = new int[65536];
        int num = 1;

        for (char a : array)
            if ((a >= 'A' && a <= 'Z') ||
                    (a >= 'a' && a <= 'z') ||
                    (a >= 'А' && a <= 'Я') ||
                    (a >= 'а' && a <= 'я'))
            {
                compare[a]++;
            }

        for (int i = 0; i < compare.length; i++) {
            if (compare[i] > 0) {
                System.out.println(num++ + ". " + (char)i + " - " + compare[i]);
            }
        }
    }
}