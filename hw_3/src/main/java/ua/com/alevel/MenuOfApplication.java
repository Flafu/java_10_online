package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuOfApplication {
    void test() throws IOException {
        System.out.println("Hello! Welcome to my application!");
        System.out.println("Please, press 1, 2, 3 or 0 to see what will happen :)" + "\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            start(position, reader);
            menu();
        }
    }

    public void menu() throws IOException {
        System.out.println("1. Perform regular reverse");
        System.out.println("2. Perform substring reverse");
        System.out.println("3. Perform reverse by given indexes");
        System.out.println("0. Press 0 to exit the app");
    }

    public void start(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> oneReverse();
            case "2" -> subReverse();
            case "3" -> reverseByIndex();
            case "0" ->  System.exit(0);
        }
    }

    public void oneReverse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Give me a word or a sentence to start with: ");
        String src = reader.readLine();
        System.out.println("Your given info was reversed: " + ReverseHomework.defaultReverse(src) + "\n");
    }

    public void subReverse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Give me a word or a sentence to start with: ");
        String src = reader.readLine();
        System.out.println("Your word or a sentence - " + src);
        System.out.println("Give me a part of this word or a sentence you want to reverse: ");
        String dest = reader.readLine();
        System.out.println("Your given info was reversed: " + ReverseHomework.subString(src, dest) + "\n");
    }

    public void reverseByIndex() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Give me a word or a sentence to start with: ");
        String src = reader.readLine();
        System.out.println("Enter first Index: ");
        String first = reader.readLine();
        System.out.println("Enter last Index: ");
        String last = reader.readLine();
        int firstIndex = Integer.parseInt(first);
        int lastIndex = Integer.parseInt(last);
        System.out.println("Your given info was reversed: " + ReverseHomework.indexReverse(src, firstIndex, lastIndex) + "\n");
    }
}
