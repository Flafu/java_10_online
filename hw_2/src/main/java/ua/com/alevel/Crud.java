package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Crud {
    Cats[] cats = new Cats[10];

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    public void menu() {
        System.out.println();
        System.out.println("If you want create new cat please enter 1");
        System.out.println("If you want show all cats please enter 2");
        System.out.println("If you want exit please enter 3");
    }

    public void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> create(bufferedReader);
            case "2" -> readAll();
            case "3" -> System.exit(0);
        }
    }

    public void create(BufferedReader bufferedReader) throws IOException {
        if (cats[cats.length - 1] == null) {
            createArray();
        }
        System.out.println("Please enter cat's name:");
        String name = bufferedReader.readLine();
        while(!Cats.validateName(name)) {
            System.out.println("Please enter cat's name correctly using only Latin/Cyrillic alphabet(up to 20 letters): ");
            name = bufferedReader.readLine();
        }

        System.out.println("Please enter cat's color:");
        String color = bufferedReader.readLine();
        while(!Cats.validateColor(color)) {
            System.out.println("Please enter cat's color correctly using only Latin/Cyrillic alphabet(up to 15 letters): ");
            color = bufferedReader.readLine();
        }

        System.out.println("Please enter cat's age:");
        String ageNotEmpty = bufferedReader.readLine();
        while (ageNotEmpty.isEmpty() || !ageNotEmpty.matches("\\d+") || !Cats.validateAge(Integer.parseInt(ageNotEmpty))) {
            System.out.println("Please enter cat's age correctly using only numbers(up to 30 years): ");
            ageNotEmpty = bufferedReader.readLine();
        }

        Cats cat = new Cats();
        cat.name = name;
        cat.color = color;
        cat.age = ageNotEmpty;
        for (int i = 0; i < cats.length; i++) {
            if(cats[i] == null) {
                cats[i] = cat;
                break;
            }
        }
    }

    public void createArray() {
        Cats[] newCats = new Cats[cats.length + 10];
        for (int i = 0; i < cats.length; i++) {
            newCats[i] = cats[i];
        }
        cats = newCats;
    }

    public void readAll() {
        for (int i = 0; i < cats.length; i++) {
            if(cats[i] != null) {
                System.out.println("Cat: " + i +
                        ", Name: " + cats[i].name +
                        ", Color: " + cats[i].color +
                        ", Age: " + cats[i].age);
            }
        }
    }
}
