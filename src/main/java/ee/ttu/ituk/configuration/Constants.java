package ee.ttu.ituk.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String HOUR_AVERAGE_MODE = "dswrfsfc_1_Hour_Average";
    public static final String COUNT = "120";
    public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String BASE_URL = "https://api.planetos.com/v1/datasets/noaa_gfs_pgrb2_global_forecast_recompute_0.25degree/point?origin=dataset-details";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_VALUE_APPLICATION_JSON = "application/json charset=utf-8";

}
