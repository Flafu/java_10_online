package ua.com.alevel.service;

import ua.com.alevel.db.DbOwnerRelation;
import ua.com.alevel.entity.Owner;

public class OwnerService {

    private final DbOwnerRelation relation = DbOwnerRelation.getInstance();

    public void create(Owner owner) {
        relation.createOwner(owner);
    }

    public void update(Owner owner) {
        relation.updateOwner(owner);
    }

    public Owner[] findAll() {
        return relation.allOwners();
    }

    public Owner findById(String ownerId) {
        return relation.findOwnerById(ownerId);
    }

    public void delete(String ownerId) {
        Owner current = relation.findOwnerById(ownerId);
        if (current != null) {
            relation.deleteOwner(ownerId);
        }
    }

    public boolean validateName(String name) {
        if ((name != null) && (name.matches("[a-zA-Zа-яА-Я]+") && name.length() <= 20)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateGender(String gender) {
        if ((gender != null) && (gender.length() == 1) && ((gender.equalsIgnoreCase("M")) || (gender.equalsIgnoreCase("F")))) {
            return true;
        } else {
            return false;
        }
    }
}
