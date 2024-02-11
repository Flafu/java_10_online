package ua.com.alevel.controller;

import ua.com.alevel.entity.Owner;
import ua.com.alevel.service.OwnerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OwnerController {

    private final OwnerService ownerService = new OwnerService();

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
        System.out.println("If you want to create a new owner please enter 1");
        System.out.println("If you want to update your owner please enter 2");
        System.out.println("If you want to see all owners please enter 3");
        System.out.println("If you want to find an owner by ID please enter 4");
        System.out.println("If you want to delete an owner please enter 5");
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
        System.out.println("Please enter owner's name:");
        String nameOwner = bufferedReader.readLine();
        if (!ownerService.validateName(nameOwner)) {
            System.out.println("Oops! Please enter owner's name correctly using only Latin/Cyrillic alphabet(up to 20 letters): ");
            nameOwner = bufferedReader.readLine();
        }

        System.out.println("Enter owner's gender (M/F format): ");
        String genderOwner = bufferedReader.readLine();
        if (!ownerService.validateGender(genderOwner)) {
            System.out.println("Oops! Please enter owner's gender correctly using only 'F' or 'M':");
            genderOwner = bufferedReader.readLine();
        }

        Owner owner = new Owner();
        owner.setName(nameOwner);
        owner.setGender(genderOwner);
        ownerService.create(owner);
        System.out.println("The owner was added successfully!");
    }

    void update(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter owner's ID:");
        String ownerId = bufferedReader.readLine();

        Owner owner = ownerService.findById(ownerId);

        if (owner != null) {
            System.out.println("Please enter a new name for an owner:");
            String nameOwner = bufferedReader.readLine();
            if (!ownerService.validateName(nameOwner)) {
                System.out.println("Oops! Please enter owner's name correctly using only Latin/Cyrillic alphabet(up to 20 letters): ");
                nameOwner = bufferedReader.readLine();
            }
            owner.setName(nameOwner);

            System.out.println("Please enter a new owner's gender (M/F format):");
            String genderOwner = bufferedReader.readLine();
            if (!ownerService.validateGender(genderOwner)) {
                System.out.println("Oops! Please enter owner's gender correctly using only 'F' or 'M': ");
                genderOwner = bufferedReader.readLine();
            }

            owner.setGender(genderOwner);
            ownerService.update(owner);
            System.out.println("Owner's information was updated!");
        } else {
            System.out.println("Sorry, but no owner with that ID was found!");
        }
    }

    void readAll() {
        System.out.println("There is a full list of owners: ");
        for (Owner owner : ownerService.findAll()) {
            if (owner != null) {
                System.out.println("Owner's ID: " + owner.getId() + ", Name: " + owner.getName() + ", Gender: " + owner.getGender());
            }
        }
    }

    void readById(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the owner you are looking for:");
        String IdOwner = bufferedReader.readLine();
        Owner owner = ownerService.findById(IdOwner);
        if (owner != null) {
            System.out.println("Owner's ID: " + owner.getId() + ", Name: " + owner.getName() + ", Gender: " + owner.getGender());
        } else {
            System.out.println("Sorry, but no owner with that ID was found!");
        }
    }

    void delete(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the owner you want to delete: ");
        String idOwner = bufferedReader.readLine();
        Owner owner = ownerService.findById(idOwner);
        if (owner != null) {
            ownerService.delete(idOwner);
            System.out.println("Owner was deleted successfully!");
        } else {
            System.out.println("Sorry, but no owner with that ID was found!");
        }
    }
}
