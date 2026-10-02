package bikr.repository;

import bikr.model.*;
import bikr.model.enums.CategoryLevel;

public class UserRepository {

    public int insertRacer(Racer racer) {
        racer.setUserId(DataStore.nextUserId());
        if (racer.getCategory() == null) racer.setCategory(CategoryLevel.CAT_5);
        DataStore.users.add(racer);
        return racer.getUserId();
    }

    public User findByEmail(String email) {
        for (User u : DataStore.users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public User findBySsn(String ssn) {
        for (User u : DataStore.users) {
            if (u.getSsn().equals(ssn)) return u;
        }
        return null;
    }

    public Racer findRacerById(int userId) {
        for (User u : DataStore.users) {
            if (u instanceof Racer && u.getUserId() == userId) return (Racer) u;
        }
        return null;
    }

    public void updateRacerCategory(int userId, CategoryLevel newCategory, int newPodiums) {
        Racer r = findRacerById(userId);
        if (r != null) {
            r.setCategory(newCategory);
            r.setCurrentPodiums(newPodiums);
        }
    }
}