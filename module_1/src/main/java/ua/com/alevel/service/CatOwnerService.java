package ua.com.alevel.service;

import ua.com.alevel.db.DbOwnerRelation;
import ua.com.alevel.entity.Cat;
import ua.com.alevel.entity.CatOwner;
import ua.com.alevel.entity.Owner;

public class CatOwnerService {

    private final DbOwnerRelation relation = DbOwnerRelation.getInstance();

    public void attachCatToOwner(String catId, String ownerId) {
        Cat currentCat = relation.findCatById(catId);
        Owner currentOwner = relation.findOwnerById(ownerId);
        if (currentCat != null && currentOwner != null) {
            relation.attachCatToOwner(catId, ownerId);
        }
    }

    public Cat[] findAllCatsByOwner(String ownerId) {
        return relation.findAllCatsByOwner(ownerId);
    }

    public Owner[] findAllOwnerWithCats(String catId) {
        return relation.findAllOwnersWithCat(catId);
    }

    public CatOwner[] findAll() {
        return relation.allCatOwners();
    }

    public void deleteCatOwners(String catId, String ownerId) {
        relation.deleteCatOwnerById(catId, ownerId);
    }

    public boolean ValidateId(String id) {
        if ((id != null) && (!id.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }
}
