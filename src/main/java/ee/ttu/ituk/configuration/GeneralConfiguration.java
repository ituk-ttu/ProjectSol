package ee.ttu.ituk.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralConfiguration {

    @Value("api-key")
    String apiKey;

    @Value("commonURL")
    String commonUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getCommonUrl() {
        return commonUrl;
    }
}
