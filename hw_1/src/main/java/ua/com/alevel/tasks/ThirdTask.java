package ua.com.alevel.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdTask {
    public static void thirdTask() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter lesson number (from 1 to 10):");
        String info = bufferedReader.readLine();
        int lessonNumber = Integer.parseInt(info);

        int calculation = lessonNumber * 45 + (lessonNumber / 2) * 5 + ((lessonNumber + 1) / 2 - 1) * 15;
        System.out.println("End: " + (calculation / 60 + 9) + ":" + calculation % 60);
    }
}
