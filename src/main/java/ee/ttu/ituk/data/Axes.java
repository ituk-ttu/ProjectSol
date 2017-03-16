package ee.ttu.ituk.data;

import java.math.BigDecimal;

public class Axes {
    String latitude;
    String time;
    String longitude;
    String reftime;
    BigDecimal angle;
    BigDecimal azimuth;

    public String getLatitude() {
        return latitude;
    }


    public String getTime() {
        return time;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getReftime() {
        return reftime;
    }

    public BigDecimal getAngle() {
        return angle;
    }

    public void setAngle(BigDecimal angle) {
        this.angle = angle;
    }

    public BigDecimal getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(BigDecimal azimuth) {
        this.azimuth = azimuth;
    }
}
