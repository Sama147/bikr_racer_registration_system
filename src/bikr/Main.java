package bikr;

import bikr.controller.AuthController;
import bikr.controller.RaceRegistrationController;
import bikr.controller.ResultController;
import bikr.model.Race;
import bikr.model.Racer;
import bikr.pattern.CategoryUpgradeService;
import bikr.pattern.LicenseCategoryObserver;
import bikr.pattern.RacerNotifyObserver;
import bikr.repository.LicenseRepository;
import bikr.repository.RaceRepository;
import bikr.repository.RegistrationRepository;
import bikr.repository.UserRepository;
import bikr.view.AuthView;
import bikr.view.RaceRegistrationView;
import bikr.view.ResultView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Repositories (data layer)
        UserRepository         userRepo    = new UserRepository();
        RaceRepository         raceRepo    = new RaceRepository();
        RegistrationRepository regRepo     = new RegistrationRepository();
        LicenseRepository      licenseRepo = new LicenseRepository();

        //Observer pattern wiring
        CategoryUpgradeService upgradeService = new CategoryUpgradeService();
        upgradeService.attach(new RacerNotifyObserver());
        upgradeService.attach(new LicenseCategoryObserver(licenseRepo));

        // Views
        AuthView             authView   = new AuthView(sc);
        RaceRegistrationView regView    = new RaceRegistrationView(sc);
        ResultView           resultView = new ResultView();

        // Controllers
        AuthController             authCtrl   = new AuthController(userRepo, authView);
        RaceRegistrationController regCtrl    = new RaceRegistrationController(
                raceRepo, regRepo, licenseRepo, regView);
        ResultController           resultCtrl = new ResultController(
                userRepo, upgradeService, resultView);

        printBanner();

        // outer loop:  login, register races, sign out, repeat
        while (true) {

            Racer racer = authCtrl.login();
            if (racer == null) continue;

            // --- Inner loop — race menu until sign out or exit ---
            while (true) {
                int choice = regCtrl.showRaceMenu();

                if (choice == 0) {
                    regCtrl.showBye();
                    return;
                }
                if (choice == 5) {
                    regCtrl.showSignedOut();
                    break;
                }

                Race race = regCtrl.getRaceByMenuChoice(choice);
                if (race == null) {
                    regCtrl.showInvalidChoice();
                    continue;
                }

                if (regCtrl.registerForRace(racer, race)) {
                    resultCtrl.simulateRaceDay(racer, race);
                }
            }
        }
    }

    private static void printBanner() {
        System.out.println("RACERS TO TEST:");
        System.out.println("  Nathaniel Lee   | nl@gmail.com | nl123 | no license (CAT_5)");
        System.out.println("  Illysia Lewis   | il@gmail.com | il123 | expired license (CAT_2)");
        System.out.println("  Vivian Lawrence | vl@gmail.com | vl123 | valid license, 4 podiums (CAT_2)");
        System.out.println("  Ellie Ryerson   | er@gmail.com | er123 | no license (CAT_2)");
        System.out.println();
        System.out.println("RACES TO TEST:");
        System.out.println("  1. Grand Canyonic heated wheels - official, closed registration");
        System.out.println("  2. Seattle Crystal Twilight     - official, every category full");
        System.out.println("  3. The Summit Classic           - official, open for registration");
        System.out.println("  4. Maunakai volcanic boom       - Unofficial, 2 seats per category");
        System.out.println();
        System.out.println("SUGGESTED DEMO FLOWS:");
        System.out.println("  - Nathaniel picks 3  -> license purchase flow");
        System.out.println("  - Illysia   picks 3  -> license renewal flow");
        System.out.println("  - Vivian    picks 3  -> podium upgrade + observers run");
        System.out.println("  - Anyone    picks 1  -> registration closed");
        System.out.println("  - Anyone    picks 2  -> category seats full");
        System.out.println("  - Nathaniel picks 4  -> unofficial race, no podium counted");
        System.out.println("  - SEAT LIMIT TEST on race 4 (Maunakai, 2 CAT_2 seats):");
        System.out.println("      register Vivian  -> 1/2 seats taken");
        System.out.println("      register Illysia -> 2/2 seats taken");
        System.out.println("      register Ellie   -> blocked (\"no available seats\")");
        System.out.println();
        System.out.println("============================================================");
        System.out.println();
    }
}