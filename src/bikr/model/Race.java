package bikr.model;

import bikr.model.enums.RaceType;
import bikr.pattern.RaceBuilder;

import java.time.LocalDate;

public class Race {
    private int raceId;
    private int organizerId;
    private String raceName;
    private boolean raceOfficiality;
    private LocalDate raceDate;
    private RaceType raceType;
    private double raceMiles;
    private String raceRoute;
    private String raceLocation;
    private int raceMaxRegistrations;
    private LocalDate raceLastDayRegistrations;

    public Race(RaceBuilder builder) {
        this.raceName = builder.getRaceName();
        this.raceDate = builder.getRaceDate();
        this.raceType = builder.getRaceType();
        this.raceOfficiality = builder.isRaceOfficiality();
        this.raceMiles = builder.getRaceMiles();
        this.raceRoute = builder.getRaceRoute();
        this.raceLocation = builder.getRaceLocation();
        this.raceMaxRegistrations = builder.getRaceMaxRegistrations();
        this.raceLastDayRegistrations = builder.getRaceLastDayRegistrations();
    }

    public int getRaceId() { return raceId; }
    public void setRaceId(int raceId) { this.raceId = raceId; }

    public int getOrganizerId() { return organizerId; }
    public void setOrganizerId(int organizerId) { this.organizerId = organizerId; }

    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }

    public boolean isRaceOfficiality() { return raceOfficiality; }
    public void setRaceOfficiality(boolean raceOfficiality) { this.raceOfficiality = raceOfficiality; }

    public LocalDate getRaceDate() { return raceDate; }
    public void setRaceDate(LocalDate raceDate) { this.raceDate = raceDate; }

    public RaceType getRaceType() { return raceType; }
    public void setRaceType(RaceType raceType) { this.raceType = raceType; }

    public double getRaceMiles() { return raceMiles; }
    public void setRaceMiles(double raceMiles) { this.raceMiles = raceMiles; }

    public String getRaceRoute() { return raceRoute; }
    public void setRaceRoute(String raceRoute) { this.raceRoute = raceRoute; }

    public String getRaceLocation() { return raceLocation; }
    public void setRaceLocation(String raceLocation) { this.raceLocation = raceLocation; }

    public int getRaceMaxRegistrations() { return raceMaxRegistrations; }
    public void setRaceMaxRegistrations(int raceMaxRegistrations) { this.raceMaxRegistrations = raceMaxRegistrations; }

    public LocalDate getRaceLastDayRegistrations() { return raceLastDayRegistrations; }
    public void setRaceLastDayRegistrations(LocalDate raceLastDayRegistrations) {
        this.raceLastDayRegistrations = raceLastDayRegistrations;
    }
}