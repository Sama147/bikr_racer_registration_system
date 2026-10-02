package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;

public interface UpgradeSubject {
    void attach(UpgradeObserver observer);
    void detach(UpgradeObserver observer);
    void notifyObservers(Racer racer, CategoryLevel newCategory);
}