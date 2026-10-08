package bikr.pattern;

import bikr.model.Race;
import bikr.model.enums.RaceType;

import java.time.LocalDate;

public class RaceBuilder {
    private String raceName;
    private LocalDate raceDate;
    private RaceType raceType;
    private boolean raceOfficiality;
    private double raceMiles;
    private String raceRoute;
    private String raceLocation;
    private int raceMaxRegistrations;
    private LocalDate raceLastDayRegistrations;

    public RaceBuilder(String raceName, LocalDate raceDate) {
        this.raceName = raceName;
        this.raceDate = raceDate;
    }

    public RaceBuilder setType(RaceType t) { this.raceType = t; return this; }
    public RaceBuilder setOfficiality(boolean o) { this.raceOfficiality = o; return this; }
    public RaceBuilder setMiles(double m) { this.raceMiles = m; return this; }
    public RaceBuilder setRoute(String r) { this.raceRoute = r; return this; }
    public RaceBuilder setLocation(String l) { this.raceLocation = l; return this; }
    public RaceBuilder setMaxRegistrations(int m) { this.raceMaxRegistrations = m; return this; }
    public RaceBuilder setLastDayRegistrations(LocalDate d) { this.raceLastDayRegistrations = d; return this; }

    public Race build() {
        validate();
        return new Race(this);
    }

    private void validate() {
        if (raceName == null || raceName.isBlank())
            throw new IllegalStateException("Race name required");
        if (raceDate == null)
            throw new IllegalStateException("Race date required");
        if (raceType == null)
            throw new IllegalStateException("Race type required");
        if (raceRoute == null || raceRoute.isBlank())
            throw new IllegalStateException("Race route required");
        if (raceLocation == null || raceLocation.isBlank())
            throw new IllegalStateException("Race location required");
        if (raceMiles <= 0)
            throw new IllegalStateException("Miles must be positive");
        if (raceMaxRegistrations <= 0)
            throw new IllegalStateException("Max registrations must be positive");
        if (raceLastDayRegistrations == null)
            throw new IllegalStateException("Last registration date required");
        if (raceLastDayRegistrations.isAfter(raceDate))
            throw new IllegalStateException("Registration deadline cannot be after race date");
    }

    public String getRaceName() { return raceName; }
    public LocalDate getRaceDate() { return raceDate; }
    public RaceType getRaceType() { return raceType; }
    public boolean isRaceOfficiality() { return raceOfficiality; }
    public double getRaceMiles() { return raceMiles; }
    public String getRaceRoute() { return raceRoute; }
    public String getRaceLocation() { return raceLocation; }
    public int getRaceMaxRegistrations() { return raceMaxRegistrations; }
    public LocalDate getRaceLastDayRegistrations() { return raceLastDayRegistrations; }
}