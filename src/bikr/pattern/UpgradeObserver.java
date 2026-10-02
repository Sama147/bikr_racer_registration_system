package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;

public interface UpgradeObserver {
    void update(Racer racer, CategoryLevel newCategory);
}