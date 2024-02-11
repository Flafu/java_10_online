package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {

    public static final CatController catController = new CatController();
    public static final OwnerController ownerController = new OwnerController();
    public static final CatOwnerController catOwnerController = new CatOwnerController();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! Welcome to my application!");
        String position = "";
        while (!position.equals("0")) {
            menu();
            position = bufferedReader.readLine();
            crud(position, bufferedReader);
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want to interact with cats please enter 1");
        System.out.println("If you want to interact with owners please enter 2");
        System.out.println("If you want to interact with cats and owners both please enter 3");
        System.out.println("If you want to exit application please enter 0");
    }

    void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> catController.start();
            case "2" -> ownerController.start();
            case "3" -> catOwnerController.start();
            case "0" -> System.exit(0);
        }
    }
}
