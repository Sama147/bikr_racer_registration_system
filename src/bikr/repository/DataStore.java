package bikr.repository;

import bikr.model.*;
import bikr.model.enums.*;
import bikr.pattern.RaceBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory replacement for the SQLite database.
 * Holds every entity as a Java List and seeds the demo data on first load.
 *
 * Repositories read and write these lists. The rest of the app is unaware
 * that there's no actual database.
 */
public class DataStore {

    public static final List<User>         users         = new ArrayList<>();
    public static final List<Race>         races         = new ArrayList<>();
    public static final List<Registration> registrations = new ArrayList<>();
    public static final List<License>      licenses      = new ArrayList<>();

    private static int nextUserId = 1;
    private static int nextRaceId = 1;
    private static int nextRegId  = 1;
    private static int nextLicId  = 1;

    public static int nextUserId() { return nextUserId++; }
    public static int nextRaceId() { return nextRaceId++; }
    public static int nextRegId()  { return nextRegId++;  }
    public static int nextLicId()  { return nextLicId++;  }

    static {
        seed();
    }

    private static void seed() {
        LocalDate today = LocalDate.now();

        // --- Racer 1: Nathaniel Lee — no license ---
        Racer nathaniel = new Racer("Nathaniel", "Lee", "nl@gmail.com", "000000001", "nl123");
        nathaniel.setUserId(nextUserId());
        nathaniel.setCategory(CategoryLevel.CAT_5);
        nathaniel.setCurrentPodiums(0);
        users.add(nathaniel);

        // --- Racer 2: Illysia Lewis — expired license, CAT_2 ---
        Racer illysia = new Racer("Illysia", "Lewis", "il@gmail.com", "000000002", "il123");
        illysia.setUserId(nextUserId());
        illysia.setCategory(CategoryLevel.CAT_2);
        illysia.setCurrentPodiums(0);
        users.add(illysia);
        License expired = new License(illysia.getUserId(), today.minusDays(30), CategoryLevel.CAT_2);
        expired.setLicenseId(nextLicId());
        licenses.add(expired);

        // --- Racer 3: Vivian Lawrence — valid license, CAT_2, 4 podiums ---
        Racer vivian = new Racer("Vivian", "Lawrence", "vl@gmail.com", "000000003", "vl123");
        vivian.setUserId(nextUserId());
        vivian.setCategory(CategoryLevel.CAT_2);
        vivian.setCurrentPodiums(4);
        users.add(vivian);
        License valid = new License(vivian.getUserId(), today.plusYears(1), CategoryLevel.CAT_2);
        valid.setLicenseId(nextLicId());
        licenses.add(valid);

        // --- Organizer ---
        Organizer amira = new Organizer("Amira", "Kareem", "amira@bikr.com", "100000001", "amira");
        amira.setUserId(nextUserId());
        users.add(amira);

        // --- Races ---

        // 1. Grand Canyonic — official, registration closed
        Race r1 = new RaceBuilder("Grand Canyonic heated wheels", today.plusDays(20))
                .setType(RaceType.CRITERIUM)
                .setOfficiality(true)
                .setMiles(70)
                .setRoute("Around and through the Grand Canyon")
                .setLocation("Grand Canyon, Grand Canyon National Park, AZ")
                .setMaxRegistrations(10)
                .setLastDayRegistrations(today.minusDays(1))
                .build();
        r1.setRaceId(nextRaceId());
        r1.setOrganizerId(amira.getUserId());
        races.add(r1);

        // 2. Seattle Crystal Twilight — official, 1 seat per category, all full
        Race r2 = new RaceBuilder("Seattle Crystal Twilight", today.plusDays(30))
                .setType(RaceType.GRAVEL)
                .setOfficiality(true)
                .setMiles(68)
                .setRoute("Around and through the financial district")
                .setLocation("Downtown Financial District, Seattle, WA")
                .setMaxRegistrations(1)
                .setLastDayRegistrations(today.plusDays(29))
                .build();
        r2.setRaceId(nextRaceId());
        r2.setOrganizerId(amira.getUserId());
        races.add(r2);

        // 3. The Summit Classic — official, open
        Race r3 = new RaceBuilder("The Summit Classic", today.plusDays(45))
                .setType(RaceType.MOUNTAIN)
                .setOfficiality(true)
                .setMiles(50)
                .setRoute("Around and through Pikes Peak")
                .setLocation("Pikes Peak, Colorado")
                .setMaxRegistrations(15)
                .setLastDayRegistrations(today.plusDays(44))
                .build();
        r3.setRaceId(nextRaceId());
        r3.setOrganizerId(amira.getUserId());
        races.add(r3);

        // 4. Maunakai volcanic boom — unofficial, open
        Race r4 = new RaceBuilder("Maunakai volcanic boom", today.plusDays(60))
                .setType(RaceType.TIME_TRIAL)
                .setOfficiality(false)
                .setMiles(45)
                .setRoute("Maunakai")
                .setLocation("Maunakai, Hawaii")
                .setMaxRegistrations(15)
                .setLastDayRegistrations(today.plusDays(59))
                .build();
        r4.setRaceId(nextRaceId());
        r4.setOrganizerId(amira.getUserId());
        races.add(r4);

        // --- Fill Seattle's single seat in EVERY category so no racer can register ---
        addReg(9001, r2.getRaceId(), CategoryLevel.CAT_5);
        addReg(9002, r2.getRaceId(), CategoryLevel.CAT_4);
        addReg(9003, r2.getRaceId(), CategoryLevel.CAT_3);
        addReg(9004, r2.getRaceId(), CategoryLevel.CAT_2);
        addReg(9005, r2.getRaceId(), CategoryLevel.CAT_1);
    }

    private static void addReg(int racerId, int raceId, CategoryLevel cat) {
        Registration reg = new Registration(racerId, raceId, cat);
        reg.setRegistrationId(nextRegId());
        registrations.add(reg);
    }

    private DataStore() { }
}