package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;
import bikr.repository.LicenseRepository;

public class LicenseCategoryObserver implements UpgradeObserver {
    private final LicenseRepository licenseRepository;

    public LicenseCategoryObserver(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void update(Racer racer, CategoryLevel newCategory) {
        licenseRepository.updateCategory(racer.getUserId(), newCategory);
        System.out.println(">>> License updated for " + racer.getFullName()
                + " to " + newCategory);
    }
}