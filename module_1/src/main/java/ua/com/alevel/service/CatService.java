package ua.com.alevel.service;

import ua.com.alevel.db.DbOwnerRelation;
import ua.com.alevel.entity.Cat;

public class CatService {

    private final DbOwnerRelation relation = DbOwnerRelation.getInstance();

    public void create(Cat cat) {
        relation.createCat(cat);
    }

    public void update(Cat cat) {
        relation.updateCat(cat);
    }

    public Cat[] findAll() {
        return relation.allCats();
    }

    public Cat findById(String catId) {
        return relation.findCatById(catId);
    }

    public void delete(String catId) {
        Cat current = relation.findCatById(catId);
        if (current != null) {
            relation.deleteCat(catId);
        }
    }

    public boolean validateName(String name) {
        if ((name != null) && (name.matches("[a-zA-Zа-яА-Я]+") && name.length() <= 20)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateColor(String color) {
        if ((color != null) && color.matches("[a-zA-Zа-яА-Я]+") && color.length() <= 15) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAge(Integer age) {
        if (age > 0 && age <= 30 && age.toString().matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }
}
