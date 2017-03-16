package ee.ttu.ituk.web;

import ee.ttu.ituk.data.ResponseData;
import ee.ttu.ituk.service.CalculationService;
import ee.ttu.ituk.service.PlanetOSRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

public class CalculationController {

    @Autowired
    CalculationService calculationService;

    @Autowired
    PlanetOSRequestHandler planetOSRequestHandler;

    @RequestMapping(value = "/calculate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Map<String, BigDecimal> calculate(@RequestParam(name = "inclination") String inclination,
                                                           @RequestParam(name = "angleOfCompass") String angleOfCompass,
                                                           @RequestParam(name = "longitude") String longitude,
                                                           @RequestParam(name = "latitude") String latitude) {
        ResponseData responseData = planetOSRequestHandler.performRequest(latitude, longitude);
        return calculationService.calculateRealSolarPower(responseData, new BigDecimal(inclination), new BigDecimal(angleOfCompass));
    }

}
