package ee.ttu.ituk.controller;

import ee.ttu.ituk.data.GraphData;
import ee.ttu.ituk.data.RequestData;
import ee.ttu.ituk.data.ResponseData;
import ee.ttu.ituk.service.CalculationService;
import ee.ttu.ituk.service.PlanetOSRequestHandler;
import ee.ttu.ituk.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class CalculationController {

    @Autowired
    CalculationService calculationService;

    @Autowired
    PlanetOSRequestHandler planetOSRequestHandler;

    @RequestMapping(value = "calculate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<GraphData> calculate(@RequestBody RequestData requestData) {
        if (!ValidationService.validateLongitudeAndLatitude(requestData.getLongitude(), requestData.getLatitude())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseData responseData = planetOSRequestHandler.performRequest(requestData.getLatitude(),
                requestData.getLongitude());
        return new ResponseEntity<>(calculationService.calculateRealSolarPower(responseData,
                new BigDecimal(requestData.getInclination()), new BigDecimal(requestData.getAngleRelativeToMedians())),
                HttpStatus.OK);
    }

}
