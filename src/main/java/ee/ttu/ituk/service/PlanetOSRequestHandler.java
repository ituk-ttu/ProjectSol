package ee.ttu.ituk.service;

import com.google.gson.Gson;
import ee.ttu.ituk.configuration.Constansts;
import ee.ttu.ituk.configuration.GeneralConfiguration;
import ee.ttu.ituk.data.ResponseData;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Service
public class PlanetOSRequestHandler {

    @Autowired
    GeneralConfiguration generalConfiguration;

    public synchronized String performRequest(String latitude, String longitude) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(createProperLink(latitude, longitude));
            request.addHeader(Constansts.HEADER_ACCEPT, Constansts.HEADER_VALUE_APPLICATION_JSON);
            HttpResponse response = httpClient.execute(request);
            response.getEntity();
            Gson gson = new Gson();
            String json = EntityUtils.toString(response.getEntity());
            ResponseData responseData = gson.fromJson(json, ResponseData.class);
            System.out.println(responseData.getEntries());
//            System.out.println(json);
        } catch (IOException e) {
            System.err.println("error in IO");
        }
        return null;
    }

    String createProperLink(String latitude, String longitude) {
        String str = "https://api.planetos.com/v1/datasets/noaa_gfs_pgrb2_global_forecast_recompute_0.25degree" +
                "/point?origin=dataset-details&lat=49.5&apikey=7721a974ff4e44808149e1e22812dc17&lon=-50.5" +
                "&var=dswrfsfc_1_Hour_Average&start=2017-03-10T14:30:00Z" +
                "&end=2017-03-15T15:30:00Z&count=120";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constansts.BASE_URL);
        stringBuilder.append("&lat=").append(latitude);
        stringBuilder.append("&apikey=").append(Constansts.API_KEY);
        stringBuilder.append("&lon=").append(longitude);
        stringBuilder.append("&var=").append(Constansts.HOUR_AVERAGE_MODE);
        String[] dates = getDates();
        stringBuilder.append("&start=").append(dates[0]);
        stringBuilder.append("&end=").append(dates[1]);
        stringBuilder.append("&count=").append(Constansts.COUNT);
        return stringBuilder.toString();
    }

    String[] getDates() {
        String[] dates = new String[2];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constansts.TIME_FORMAT);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constansts.TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        dates[0] = dtf.format(now);
        LocalDateTime fiveDaysLater = now.plusDays((long) 5);
        dates[1] = dtf.format(fiveDaysLater);
        return dates;
    }
}
