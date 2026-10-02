package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;

public class RacerNotifyObserver implements UpgradeObserver {
    @Override
    public void update(Racer racer, CategoryLevel newCategory) {
        System.out.println(">>> Notification: Racer " + racer.getFullName()
                + " has been promoted to " + newCategory + "!");
    }
}