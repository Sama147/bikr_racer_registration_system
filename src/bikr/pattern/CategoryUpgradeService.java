package bikr.pattern;

import bikr.model.Racer;
import bikr.model.enums.CategoryLevel;

import java.util.ArrayList;
import java.util.List;

public class CategoryUpgradeService implements UpgradeSubject {
    private final List<UpgradeObserver> observers = new ArrayList<>();

    @Override
    public void attach(UpgradeObserver observer) { observers.add(observer); }

    @Override
    public void detach(UpgradeObserver observer) { observers.remove(observer); }

    @Override
    public void notifyObservers(Racer racer, CategoryLevel newCategory) {
        for (UpgradeObserver o : observers) o.update(racer, newCategory);
    }
}