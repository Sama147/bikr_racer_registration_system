package bikr.controller;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;
import bikr.pattern.CategoryUpgradeService;
import bikr.repository.UserRepository;
import bikr.view.ResultView;

/**
 * Simulates race day after a successful registration.
 * Awards 3rd place, increments podiums, and fires the Observer pattern
 * when the racer reaches the upgrade threshold.
 *
 * All output goes through ResultView.
 */
public class ResultController {

    private static final int PODIUM_THRESHOLD = 5;

    private final UserRepository          userRepo;
    private final CategoryUpgradeService  upgradeService;
    private final ResultView              view;

    public ResultController(UserRepository userRepo,
                            CategoryUpgradeService upgradeService,
                            ResultView view) {
        this.userRepo       = userRepo;
        this.upgradeService = upgradeService;
        this.view           = view;
    }

    public void simulateRaceDay(Racer racer) {
        view.showRaceDayStarted();
        view.showThirdPlace();

        int newPodiums = racer.getCurrentPodiums() + 1;

        if (newPodiums >= PODIUM_THRESHOLD) {
            CategoryLevel next = nextCategory(racer.getCategory());

            if (next != null) {
                racer.setCurrentPodiums(0);
                racer.setCategory(next);
                userRepo.updateRacerCategory(racer.getUserId(), next, 0);

                view.showCategoryUpgraded();
                upgradeService.notifyObservers(racer, next);
            } else {
                racer.setCurrentPodiums(0);
                userRepo.updateRacerCategory(racer.getUserId(), racer.getCategory(), 0);
                view.showAlreadyAtTop();
            }
        } else {
            racer.setCurrentPodiums(newPodiums);
            userRepo.updateRacerCategory(racer.getUserId(), racer.getCategory(), newPodiums);
            view.showPodiumsIncreased(newPodiums);
        }
    }

    private CategoryLevel nextCategory(CategoryLevel current) {
        switch (current) {
            case CAT_5: return CategoryLevel.CAT_4;
            case CAT_4: return CategoryLevel.CAT_3;
            case CAT_3: return CategoryLevel.CAT_2;
            case CAT_2: return CategoryLevel.CAT_1;
            default:    return null;
        }
    }
}