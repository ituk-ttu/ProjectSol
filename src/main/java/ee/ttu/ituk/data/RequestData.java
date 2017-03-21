package ee.ttu.ituk.data;

import org.springframework.web.bind.annotation.RequestParam;

public class RequestData {
    String inclination;
    String angleRelativeToMedians;
    String longitude;
    String latitude;

    public String getInclination() {
        return inclination;
    }

    public String getAngleRelativeToMedians() {
        return angleRelativeToMedians;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setInclination(String inclination) {
        this.inclination = inclination;
    }

    public void setAngleRelativeToMedians(String angleRelativeToMedians) {
        this.angleRelativeToMedians = angleRelativeToMedians;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
