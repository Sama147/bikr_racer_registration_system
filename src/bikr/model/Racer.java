package bikr.model;

import bikr.model.enums.CategoryLevel;

public class Racer extends User {
    private int currentPodiums;
    private CategoryLevel category;

    public Racer() {
        this.category = CategoryLevel.CAT_5;
    }

    public Racer(String firstName, String lastName, String email, String ssn, String password) {
        super(firstName, lastName, email, ssn, password);
        this.currentPodiums = 0;
        this.category = CategoryLevel.CAT_5;
    }

    public int getCurrentPodiums() { return currentPodiums; }
    public void setCurrentPodiums(int currentPodiums) { this.currentPodiums = currentPodiums; }

    public CategoryLevel getCategory() { return category; }
    public void setCategory(CategoryLevel category) { this.category = category; }
}