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

    public Race build() { return new Race(this); }

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