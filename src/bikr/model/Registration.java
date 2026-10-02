package bikr.model;

import bikr.model.enums.CategoryLevel;
import bikr.model.enums.RegistrationStatus;

public class Registration {
    private int registrationId;
    private int racerId;
    private int raceId;
    private RegistrationStatus status;
    private CategoryLevel category;

    public Registration() { }

    public Registration(int racerId, int raceId, CategoryLevel category) {
        this.racerId = racerId;
        this.raceId = raceId;
        this.category = category;
        this.status = RegistrationStatus.CONFIRMED;
    }

    public int getRegistrationId() { return registrationId; }
    public void setRegistrationId(int registrationId) { this.registrationId = registrationId; }

    public int getRacerId() { return racerId; }
    public void setRacerId(int racerId) { this.racerId = racerId; }

    public int getRaceId() { return raceId; }
    public void setRaceId(int raceId) { this.raceId = raceId; }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }

    public CategoryLevel getCategory() { return category; }
    public void setCategory(CategoryLevel category) { this.category = category; }
}