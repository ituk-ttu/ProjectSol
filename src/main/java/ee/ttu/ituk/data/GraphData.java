package ee.ttu.ituk.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GraphData {
    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<BigDecimal> solarPowerList = new ArrayList<>();

    public void setTimeList(ArrayList<String> timeList) {
        this.timeList = timeList;
    }

    public void setSolarPowerList(ArrayList<BigDecimal> solarPowerList) {
        this.solarPowerList = solarPowerList;
    }

    public ArrayList<String> getTimeList() {
        return timeList;
    }

    public ArrayList<BigDecimal> getSolarPowerList() {
        return solarPowerList;
    }

    public void addTimeAndSolarPower(String time, BigDecimal power) {
        timeList.add(time);
        solarPowerList.add(power);
    }

}
