package bikr.repository;

import bikr.model.License;
import bikr.model.enums.CategoryLevel;

import java.time.LocalDate;

public class LicenseRepository {

    public int insert(License license) {
        license.setLicenseId(DataStore.nextLicId());
        DataStore.licenses.add(license);
        return license.getLicenseId();
    }
    public License findByUserId(int userId) {
        License latest = null;
        for (License l : DataStore.licenses) {
            if (l.getUserId() == userId) latest = l;
        }
        return latest;
    }

     // Updates the license category for the given user without changing expiration date
     // Returns true if a license was found and updated, false otherwise.
    public boolean updateCategory(int userId, CategoryLevel newCategory) {
        License l = findByUserId(userId);
        if (l == null) return false;
        l.setCategory(newCategory);
        return true;
    }

     //Renews the license by extending the expiration date by one year.
     //Returns true if a license was found and renewed, false otherwise.
    public boolean renewLicense(int userId) {
        License l = findByUserId(userId);
        if (l == null) return false;
        l.setExpirationDate(LocalDate.now().plusYears(1));
        return true;
    }
}