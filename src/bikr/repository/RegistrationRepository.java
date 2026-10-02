package bikr.repository;

import bikr.model.Registration;
import bikr.model.enums.CategoryLevel;
import bikr.model.enums.RegistrationStatus;

public class RegistrationRepository {

    public int insert(Registration reg) {
        reg.setRegistrationId(DataStore.nextRegId());
        if (reg.getStatus() == null) reg.setStatus(RegistrationStatus.CONFIRMED);
        DataStore.registrations.add(reg);
        return reg.getRegistrationId();
    }

    public int countByRaceAndCategory(int raceId, CategoryLevel category) {
        int n = 0;
        for (Registration r : DataStore.registrations) {
            if (r.getRaceId() == raceId
                    && r.getCategory() == category
                    && r.getStatus() == RegistrationStatus.CONFIRMED) n++;
        }
        return n;
    }
}