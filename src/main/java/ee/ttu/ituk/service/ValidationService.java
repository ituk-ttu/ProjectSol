package ee.ttu.ituk.service;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ValidationService {

    public static boolean validateLongitudeAndLatitude(String longitude, String latitude) {
        BigDecimal longitudeDecimal = new BigDecimal(longitude);
        BigDecimal latitudeDecimal = new BigDecimal(latitude);
        if (longitudeDecimal.compareTo(BigDecimal.valueOf(-180)) == -1 || longitudeDecimal.compareTo(BigDecimal.valueOf(180)) == 1) {
            return false;
        }
        if (latitudeDecimal.compareTo(BigDecimal.valueOf(-90.0)) == -1 || latitudeDecimal.compareTo(BigDecimal.valueOf(90.0)) == 1) {
            return false;
        }

        return true;
    }
}
