package ua.com.alevel.controller;

import ua.com.alevel.entity.Cat;
import ua.com.alevel.service.CatService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CatController {
    private final CatService catService = new CatService();

    void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String position = "";
        while (!position.equals("0")) {
            menu();
            position = bufferedReader.readLine();
            crud(position, bufferedReader);
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want to create a new cat please enter 1");
        System.out.println("If you want to update your cat please enter 2");
        System.out.println("If you want to see all cats please enter 3");
        System.out.println("If you want to find a cat by ID please enter 4");
        System.out.println("If you want to delete a cat please enter 5");
        System.out.println("If you want to return to main menu please enter 0");
    }

    void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> readAll();
            case "4" -> readById(bufferedReader);
            case "5" -> delete(bufferedReader);
        }
    }

    void create(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter cat's name:");
        String nameCat = bufferedReader.readLine();
        if (!catService.validateName(nameCat)) {
            System.out.println("Oops! Please enter cat's name correctly using only Latin/Cyrillic alphabet(up to 20 letters): ");
            nameCat = bufferedReader.readLine();
        }

        System.out.println("Please enter cat's color:");
        String colorCat = bufferedReader.readLine();
        if (!catService.validateColor(colorCat)) {
            System.out.println("Oops! Please enter cat's color correctly using only Latin/Cyrillic alphabet(up to 15 letters): ");
            colorCat = bufferedReader.readLine();
        }

        Integer ageCat = null;
        while (true) {
            System.out.println("Please enter cat's age: ");
            String tempAgeCat = bufferedReader.readLine();
            if (tempAgeCat.matches("\\d+")) {
                ageCat = Integer.valueOf(tempAgeCat);
                if (catService.validateAge(ageCat)) {
                    break;
                } else {
                    System.out.println("Please enter age from 1 to 30:");
                }
            } else {
                System.out.println("Oops! Please enter cat's age correctly using only numbers: ");
            }
        }

        Cat cat = new Cat();
        cat.setName(nameCat);
        cat.setColor(colorCat);
        cat.setAge(ageCat);
        catService.create(cat);
        System.out.println("The cat was added successfully!");
    }

    void update(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter cat's ID:");
        String catId = bufferedReader.readLine();

        Cat cat = catService.findById(catId);

        if (cat != null) {
            System.out.println("Please enter a new name for a cat: ");
            String nameCat = bufferedReader.readLine();
            if (!catService.validateName(nameCat)) { // while
                System.out.println("Oops! Please enter cat's name correctly using only Latin/Cyrillic alphabet(up to 20 letters): ");
                nameCat = bufferedReader.readLine();
            }
            cat.setName(nameCat);

            System.out.println("Please enter a new color for a cat: ");
            String colorCat = bufferedReader.readLine();
            if (!catService.validateColor(colorCat)) {
                System.out.println("Oops! Please enter cat's color correctly using only Latin/Cyrillic alphabet(up to 15 letters): ");
                colorCat = bufferedReader.readLine();
            }
            cat.setColor(colorCat);

            Integer ageCat = null;
            while (true) {
                System.out.println("Please enter a new age for a cat: ");
                String tempAgeCat = bufferedReader.readLine();
                if (tempAgeCat.matches("\\d+")) {
                    ageCat = Integer.valueOf(tempAgeCat);
                    if (catService.validateAge(ageCat)) {
                        break;
                    } else {
                        System.out.println("Please enter age from 1 to 30: ");
                    }
                } else {
                    System.out.println("Oops! Please enter cat's age correctly using only numbers: ");
                }
            }
            cat.setAge(ageCat);
            catService.update(cat);
            System.out.println("Cat's information was updated!");
        } else {
            System.out.println("Sorry, but no cat with that ID was found!");
        }
    }

    void readAll() {
        System.out.println("There is a full list of cats: ");
        for (Cat cat : catService.findAll()) {
            if (cat != null) {
                System.out.println("Cat's ID: " + cat.getId() + ", Name: " + cat.getName() + ", Color: " + cat.getColor() + ", Age: " + cat.getAge());
            }
        }
    }

    void readById(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the cat you are looking for:");
        String IdCat = bufferedReader.readLine();
        Cat cat = catService.findById(IdCat);
        if (cat != null) {
            System.out.println("Cat's ID: " + cat.getId() + ", Name: " + cat.getName() + ", Color: " + cat.getColor() + ", Age: " + cat.getAge());
        } else {
            System.out.println("Sorry, but no cat with that ID was found!");
        }
    }

    void delete(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the cat you want to delete: ");
        String idCat = bufferedReader.readLine();
        Cat cat = catService.findById(idCat);
        if (cat != null) {
            catService.delete(idCat);
            System.out.println("Cat was deleted successfully!");
        } else {
            System.out.println("Sorry, but no cat with that ID was found!");
        }
    }
}
