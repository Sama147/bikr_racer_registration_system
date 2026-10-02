package bikr.repository;

import bikr.model.Race;

import java.util.List;

public class RaceRepository {

    public int insert(Race race) {
        race.setRaceId(DataStore.nextRaceId());
        DataStore.races.add(race);
        return race.getRaceId();
    }

    public List<Race> findAll() { return DataStore.races; }

    public Race findById(int raceId) {
        for (Race r : DataStore.races) {
            if (r.getRaceId() == raceId) return r;
        }
        return null;
    }
}