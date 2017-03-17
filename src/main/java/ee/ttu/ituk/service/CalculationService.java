package ee.ttu.ituk.service;

import ee.ttu.ituk.configuration.Constansts;
import ee.ttu.ituk.data.Axes;
import ee.ttu.ituk.data.DataEntry;
import ee.ttu.ituk.data.ResponseData;
import net.e175.klaus.solarpositioning.AzimuthZenithAngle;
import net.e175.klaus.solarpositioning.DeltaT;
import net.e175.klaus.solarpositioning.Grena3;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    private static BigDecimal sum = BigDecimal.ZERO;

    private void setAngles(Axes axes) {

        DateFormat format = new SimpleDateFormat(Constansts.TIME_FORMAT, Locale.ENGLISH);
        Date date = new Date();
        try {
            date = format.parse(axes.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar dateTime = new GregorianCalendar();

        dateTime.setTime(date);
        AzimuthZenithAngle position = Grena3.calculateSolarPosition(
                dateTime,
                Double.parseDouble(axes.getLatitude()), // latitude (degrees)
                Double.parseDouble(axes.getLongitude()), // longitude (degrees)
                DeltaT.estimate(dateTime));

        axes.setAngle(BigDecimal.valueOf(position.getZenithAngle()));
        axes.setAzimuth(BigDecimal.valueOf(position.getAzimuth()));
    }

    public Map<String, BigDecimal> calculateRealSolarPower(ResponseData responseData, BigDecimal inclination, BigDecimal angleOfCompass) {
        return responseData.getEntries().stream().collect(Collectors.toMap(dataEntry -> dataEntry.getAxes().getTime(),
                dataEntry -> performCalculation(dataEntry, inclination, angleOfCompass)));

    }

    private BigDecimal performCalculation(DataEntry dataEntry, BigDecimal inclination, BigDecimal angleOfCompass) {
        Axes axes = dataEntry.getAxes();
        BigDecimal solarPower = dataEntry.getData().getDswrfsfc_1_Hour_Average();
        if (!solarPower.equals(BigDecimal.ZERO)) {
            setAngles(axes);
            double altitude = Math.toRadians(axes.getAngle().subtract(BigDecimal.valueOf(90.0)).doubleValue());
            double solarSurfaceAngle = Math.toRadians(angleOfCompass.add(axes.getAzimuth()).doubleValue());
            BigDecimal power = BigDecimal.valueOf(Math.abs(solarPower.doubleValue()
                    * ((Math.sin(altitude) * Math.sin(Math.toRadians(inclination.doubleValue())))
                        + Math.cos(altitude) * Math.cos(Math.toRadians(inclination.doubleValue())) * Math.cos(solarSurfaceAngle))));
            sum = sum.add(power);
            System.out.println(axes.getTime() + " | " + axes.getAngle() + " | " + axes.getAzimuth());
            System.out.println(sum.toString());
            return power;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private double angleToRadian(double angle) {
        return Math.toRadians(angle);
    }
}
