package ee.ttu.ituk.data;

import java.util.ArrayList;

public class ResponseData {
    ArrayList<DataEntry> entries;
    Stats stats;

    public ArrayList<DataEntry> getEntries() {
        return entries;
    }

    public Stats getStats() {
        return stats;
    }
}
