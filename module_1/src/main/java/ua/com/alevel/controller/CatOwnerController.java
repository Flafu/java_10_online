package ua.com.alevel.controller;

import ua.com.alevel.entity.Cat;
import ua.com.alevel.entity.CatOwner;
import ua.com.alevel.entity.Owner;
import ua.com.alevel.service.CatOwnerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CatOwnerController {
    private final CatOwnerService catOwnerService = new CatOwnerService();

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
        System.out.println("If you want to connect a cat to an owner please enter 1");
        System.out.println("If you want to see all owner's cats please enter 2");
        System.out.println("If you want to see all cat's owners please enter 3");
        System.out.println("If you want to see all connections please enter 4");
        System.out.println("If you want to delete a connection please enter 5");
        System.out.println("If you want to return to main menu please enter 0");
    }

    void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> attach(bufferedReader);
            case "2" -> findAllCatsByOwner(bufferedReader);
            case "3" -> findAllOwnerWithCats(bufferedReader);
            case "4" -> findAllCatOwner();
            case "5" -> delete(bufferedReader);
        }
    }

    void attach(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the cat that you want to connect to the owner: ");
        String catId = bufferedReader.readLine();
        System.out.println("Enter ID of the owner: ");
        String ownerId = bufferedReader.readLine();

        boolean validateCatId = catOwnerService.ValidateId(catId);
        boolean validateOwnerId = catOwnerService.ValidateId(ownerId);

        if (validateCatId && validateOwnerId) {
            CatOwner catOwner = new CatOwner();
            catOwner.setCatId(catId);
            catOwner.setOwnerId(ownerId);
            catOwnerService.attachCatToOwner(catId, ownerId);
            System.out.println("Cat was successfully connected to the owner!");
        } else {
            if (!validateCatId) {
                System.out.println("Sorry, but no cat with that ID was found!");
            }
            if (!validateOwnerId) {
                System.out.println("Sorry, but no owner with that ID was found!");
            }
        }
    }

    public Cat[] findAllCatsByOwner(BufferedReader bufferedReader) throws IOException {
        System.out.println("If you want to see all owner's cats please enter owner's ID: ");
        String ownerId = bufferedReader.readLine();
        if (!catOwnerService.ValidateId(ownerId)) {
            System.out.println("Sorry, but no owner with that ID was found!");
            return null;
        }

        Cat[] listCats = catOwnerService.findAllCatsByOwner(ownerId);

        if (listCats.length == 0) {
            System.out.println("This owner has no cats!");
        } else {
            System.out.println("This owner has following cats:");
            for (int i = 0; i < listCats.length; i++) {
                if (listCats[i] != null) {
                    System.out.println("Cat's ID: " + listCats[i].getId() + ", Name: " + listCats[i].getName() + ", Color: " + listCats[i].getColor() + ", Age: " + listCats[i].getAge());
                }
            }
        }
        return listCats;
    }

    public Owner[] findAllOwnerWithCats(BufferedReader bufferedReader) throws IOException {
        System.out.println("If you want to see all cat's owners please enter cat's ID:");
        String catId = bufferedReader.readLine();
        if (!catOwnerService.ValidateId(catId)) {
            System.out.println("Sorry, but no cat with that ID was found!");
            return null;
        }

        Owner[] listOwners = catOwnerService.findAllOwnerWithCats(catId);
        if (listOwners.length == 0) {
            System.out.println("This cat has no owners!");
        } else {
            System.out.println("This cat has following owners:");
            for (int i = 0; i < listOwners.length; i++) {
                if (listOwners[i] != null) {
                    System.out.println("Owner's ID: " + listOwners[i].getId() + ", Name: " + listOwners[i].getName() + ", Gender: " + listOwners[i].getGender());
                }
            }
        }
        return listOwners;
    }

    public void findAllCatOwner() {
        System.out.println("All connections between cats and owners: ");
        CatOwner[] catOwners = catOwnerService.findAll();

        if (catOwners.length == 0) {
            System.out.println("No connection between cats and owners!");
        } else {
            for (int i = 0; i < catOwners.length; i++) {
                if (catOwners[i] != null) {
                    System.out.println("Cat's ID: " + catOwners[i].getCatId() + " / Owner's ID: " + catOwners[i].getOwnerId());
                }
            }
        }
    }

    public void delete(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter ID of the owner: ");
        String ownerId = bufferedReader.readLine();
        System.out.println("Enter ID of the cat: ");
        String catId = bufferedReader.readLine();

        boolean validateCatId = catOwnerService.ValidateId(catId);
        boolean validateOwnerId = catOwnerService.ValidateId(ownerId);

        if (validateCatId && validateOwnerId) {
            catOwnerService.deleteCatOwners(catId, ownerId);
            System.out.println("Connection between them was deleted!");
        } else {
            if (!validateCatId) {
                System.out.println("Sorry, but no cat with that ID was found!");
            }
            if (!validateOwnerId) {
                System.out.println("Sorry, but no owner with that ID was found!");
            }
        }
    }
}
