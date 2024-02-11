package ua.com.alevel.db;

import ua.com.alevel.entity.Cat;
import ua.com.alevel.entity.CatOwner;
import ua.com.alevel.entity.Owner;

import java.util.UUID;

public final class DbOwnerRelation {

    private static DbOwnerRelation instance;
    private Cat[] cats = new Cat[10];
    private Owner[] owners = new Owner[10];
    private CatOwner[] catOwners = new CatOwner[10];

    private DbOwnerRelation() { }

    public static DbOwnerRelation getInstance() {
        if (instance == null) {
            instance = new DbOwnerRelation();
        }
        return instance;
    }

    public void createCat(Cat cat) {
        if (cats[cats.length - 1] == null) {
            createArrayForCats();
        }
        cat.setId(generateIdForCat());
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] == null) {
                cats[i] = cat;
                break;
            }
        }
    }

    public void createOwner(Owner owner) {
        if (owners[owners.length - 1] == null) {
            createArrayForOwners();
        }
        owner.setId(generateIdForOwner());
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] == null) {
                owners[i] = owner;
                break;
            }
        }
    }

    public String generateIdForCat() {
        String id = UUID.randomUUID().toString();
        for (Cat cat : cats) {
            if (cat != null && id.equals(cat.getId())) {
                return generateIdForCat();
            }
        }
        return id;
    }

    public String generateIdForOwner() {
        String id = UUID.randomUUID().toString();
        for (Owner owner : owners) {
            if (owner != null && id.equals(owner.getId())) {
                return generateIdForCat();
            }
        }
        return id;
    }

    public void updateCat(Cat cat) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] != null && cats[i].getId().equals(cat.getId())) {
                cats[i] = cat;
                break;
            }
        }
    }

    public void updateOwner(Owner owner) {
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] != null && owners[i].getId().equals(owner.getId())) {
                owners[i] = owner;
                break;
            }
        }
    }

    public void attachCatToOwner(String catId, String ownerId) {
        if (catOwners[catOwners.length - 1] == null) {
            createArrayForCatOwners();
        }
        for (CatOwner duplicateCatOwner : catOwners) {
            if (duplicateCatOwner != null && duplicateCatOwner.getCatId().equals(catId) && duplicateCatOwner.getOwnerId().equals(ownerId)) {
                return;
            }
        }
        CatOwner catOwner = new CatOwner();
        catOwner.setCatId(catId);
        catOwner.setOwnerId(ownerId);
        for (int i = 0; i < catOwners.length; i++) {
            if (catOwners[i] == null) {
                catOwners[i] = catOwner;
                break;
            }
        }
    }

    public Cat[] allCats() {
        return cats;
    }

    public Owner[] allOwners() {
        return owners;
    }

    public CatOwner[] allCatOwners() {
        return catOwners;
    }

    public Cat findCatById(String catId) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] != null && cats[i].getId().equals(catId)) {
                return cats[i];
            }
        }
        return null;
    }

    public Owner findOwnerById(String ownerId) {
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] != null && owners[i].getId().equals(ownerId)) {
                return owners[i];
            }
        }
        return null;
    }

    public Cat[] findAllCatsByOwner(String ownerId) {
        String[] listCatId = new String[catOwners.length];
        int count = 0;
        for (int i = 0; i < catOwners.length; i++) {
            if (catOwners[i] != null && catOwners[i].getOwnerId().equals(ownerId)) {
                listCatId[count++] = catOwners[i].getCatId();
            }
        }

        Cat[] list = new Cat[count];
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] != null) {
                for (int j = 0; j < count; j++) {
                    if (cats[i].getId().equals(listCatId[j])) {
                        list[j] = cats[i];
                        break;
                    }
                }
            }
        }
        return list;
    }

    public Owner[] findAllOwnersWithCat(String catId) {
        String[] listOwnerId = new String[catOwners.length];
        int count = 0;
        for (int i = 0; i < catOwners.length; i++) {
            if (catOwners[i] != null && catOwners[i].getCatId().equals(catId)) {
                listOwnerId[count++] = catOwners[i].getOwnerId();
            }
        }

        Owner[] list = new Owner[count];
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] != null) {
                for (int j = 0; j < count; j++) {
                    if (owners[i].getId().equals(listOwnerId[j])) {
                        list[j] = owners[i];
                        break;
                    }
                }
            }
        }
        return list;
    }

    public void deleteCat(String catId) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] != null && cats[i].getId().equals(catId)) {
                cats[i] = null;
            }
            for (int j = 0; j < catOwners.length; j++) {
                if (catOwners[j] != null && catOwners[j].getCatId().equals(catId)) {
                    catOwners[j] = null;
                }
            }
        }
    }

    public void deleteOwner(String ownerId) {
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] != null && owners[i].getId().equals(ownerId)) {
                owners[i] = null;
            }
            for (int j = 0; j < catOwners.length; j++) {
                if (catOwners[j] != null && catOwners[j].getOwnerId().equals(ownerId)) {
                    catOwners[j] = null;
                }
            }
        }
    }

    public void deleteCatOwnerById(String catId, String ownerId) {
        int newArray = 0;
        for (int i = 0; i < catOwners.length; i++) {
            if (catOwners[i] != null && catOwners[i].getCatId().equals(catId) && catOwners[i].getOwnerId().equals(ownerId)) {
                catOwners[i] = null;
            } else if (catOwners[i] != null) {
                if (newArray != i) {
                    catOwners[newArray] = catOwners[i];
                    catOwners[i] = null;
                }
                newArray++;
            }
        }
    }

    private void createArrayForCats() {
        Cat[] newCats = new Cat[cats.length + 10];
        for (int i = 0; i < cats.length; i++) {
            newCats[i] = cats[i];
        }
        cats = newCats;
    }

    private void createArrayForOwners() {
        Owner[] newOwners = new Owner[owners.length + 10];
        for (int i = 0; i < owners.length; i++) {
            newOwners[i] = owners[i];
        }
        owners = newOwners;
    }

    private void createArrayForCatOwners() {
        CatOwner[] newCatOwners = new CatOwner[catOwners.length + 10];
        for (int i = 0; i < catOwners.length; i++) {
            newCatOwners[i] = catOwners[i];
        }
        catOwners = newCatOwners;
    }
}
