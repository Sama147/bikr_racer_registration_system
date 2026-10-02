package bikr.controller;

import bikr.model.License;
import bikr.model.Race;
import bikr.model.Racer;
import bikr.model.Registration;
import bikr.model.enums.CategoryLevel;
import bikr.repository.LicenseRepository;
import bikr.repository.RaceRepository;
import bikr.repository.RegistrationRepository;
import bikr.view.RaceRegistrationView;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles race selection and registration eligibility.
 *
 * Check order:
 *   1. Registration deadline
 *   2. Official races require a valid license
 *   3. Category seat availability
 *
 * All I/O goes through RaceRegistrationView.
 */
public class RaceRegistrationController {

    private final RaceRepository         raceRepo;
    private final RegistrationRepository regRepo;
    private final LicenseRepository      licenseRepo;
    private final RaceRegistrationView   view;

    public RaceRegistrationController(RaceRepository raceRepo,
                                      RegistrationRepository regRepo,
                                      LicenseRepository licenseRepo,
                                      RaceRegistrationView view) {
        this.raceRepo    = raceRepo;
        this.regRepo     = regRepo;
        this.licenseRepo = licenseRepo;
        this.view        = view;
    }

    /** Prints the race menu and returns the user's choice. */
    public int showRaceMenu() {
        return view.showRaceMenu(raceRepo.findAll());
    }

    /** Maps a 1-based menu choice to a Race, or null if out of range. */
    public Race getRaceByMenuChoice(int choice) {
        List<Race> races = raceRepo.findAll();
        if (choice < 1 || choice > races.size()) return null;
        return races.get(choice - 1);
    }

    /** Runs eligibility checks, then registers. Returns true if saved. */
    public boolean registerForRace(Racer racer, Race race) {

        // --- 1. Deadline ---
        LocalDate today = LocalDate.now();
        if (today.isAfter(race.getRaceLastDayRegistrations())) {
            view.showRegistrationClosed();
            return false;
        }

        // --- 2. Official race -> license must exist and be valid ---
        if (race.isRaceOfficiality()) {
            License license = licenseRepo.findByUserId(racer.getUserId());

            if (license == null) {
                if (!view.promptPurchaseLicense()) {
                    view.showCantRegister();
                    return false;
                }
                licenseRepo.insert(new License(racer.getUserId(), today.plusYears(1), racer.getCategory()));
                racer.setCurrentPodiums(0);
                racer.setCategory(CategoryLevel.CAT_5);
                view.showLicensePurchased();
            }
            else if (license.getExpirationDate().isBefore(today)) {
                if (!view.promptRenewLicense()) {
                    view.showCantRegister();
                    return false;
                }
                licenseRepo.updateCategory(racer.getUserId(), racer.getCategory());
                view.showLicenseRenewed();
            }
        }

        // --- 3. Category seat availability ---
        int seatsUsed = regRepo.countByRaceAndCategory(race.getRaceId(), racer.getCategory());
        if (seatsUsed >= race.getRaceMaxRegistrations()) {
            view.showCategoryFull();
            return false;
        }

        // --- 4. Save ---
        Registration reg = new Registration(racer.getUserId(), race.getRaceId(), racer.getCategory());
        regRepo.insert(reg);
        view.showRegistrationSuccess();
        return true;
    }

    // -----------------------------------------------------------------
    // Menu-level messages (delegated to the view)
    // -----------------------------------------------------------------
    public void showInvalidChoice() { view.showInvalidChoice(); }
    public void showSignedOut()     { view.showSignedOut(); }
    public void showBye()           { view.showBye(); }
}