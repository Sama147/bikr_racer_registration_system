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

    public void updateCategory(int userId, CategoryLevel newCategory) {
        License l = findByUserId(userId);
        if (l != null) {
            l.setCategory(newCategory);
            l.setExpirationDate(LocalDate.now().plusYears(1));
        }
    }
}