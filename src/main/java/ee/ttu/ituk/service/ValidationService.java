package ee.ttu.ituk.service;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ValidationService {

    private static final BigDecimal RIGHT_ANGLE = BigDecimal.valueOf(90.0);
    private static final BigDecimal STRAIGHT_ANGLE = BigDecimal.valueOf(180.0);

    public static boolean validateLongitudeAndLatitude(String longitude, String latitude) {
        BigDecimal longitudeDecimal = new BigDecimal(longitude);
        BigDecimal latitudeDecimal = new BigDecimal(latitude);
        if (longitudeDecimal.compareTo(STRAIGHT_ANGLE.negate()) == -1 ||
                longitudeDecimal.compareTo(STRAIGHT_ANGLE) == 1) {
            return false;
        }
        if (latitudeDecimal.compareTo(RIGHT_ANGLE.negate()) == -1 ||
                latitudeDecimal.compareTo(RIGHT_ANGLE) == 1) {
            return false;
        }

        return true;
    }
}
