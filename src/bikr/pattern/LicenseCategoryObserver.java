package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;
import bikr.repository.LicenseRepository;

/*
 * Observer that updates the racer's license category in the repository
 * whenever the racer is promoted. Only prints a success message if a
 * license was actually found and updated.
 */
public class LicenseCategoryObserver implements UpgradeObserver {

    private final LicenseRepository licenseRepository;

    public LicenseCategoryObserver(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void update(Racer racer, CategoryLevel newCategory) {
        boolean updated = licenseRepository.updateCategory(racer.getUserId(), newCategory);

        if (updated) {
            System.out.println(">>> License updated for " + racer.getFullName()
                    + " to " + newCategory);
        } else {
            System.out.println(">>> (no license found for " + racer.getFullName()
                    + " to update)");
        }
    }
}