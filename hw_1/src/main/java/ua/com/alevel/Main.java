package ua.com.alevel;

import ua.com.alevel.tasks.FirstTask;
import ua.com.alevel.tasks.SecondTask;
import ua.com.alevel.tasks.ThirdTask;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FirstTask.firstTask();
        SecondTask.secondTask();
        ThirdTask.thirdTask();
    }
}