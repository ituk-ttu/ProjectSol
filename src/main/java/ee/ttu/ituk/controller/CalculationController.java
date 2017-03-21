package ee.ttu.ituk.controller;

import ee.ttu.ituk.data.GraphData;
import ee.ttu.ituk.data.RequestData;
import ee.ttu.ituk.data.ResponseData;
import ee.ttu.ituk.service.CalculationService;
import ee.ttu.ituk.service.PlanetOSRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class CalculationController {

    @Autowired
    CalculationService calculationService;

    @Autowired
    PlanetOSRequestHandler planetOSRequestHandler;

    @RequestMapping(value = "calculate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody GraphData calculate(@RequestBody RequestData requestData) {
        ResponseData responseData = planetOSRequestHandler.performRequest(requestData.getLatitude(), requestData.getLongitude());
        return calculationService.calculateRealSolarPower(responseData, new BigDecimal(requestData.getInclination()), new BigDecimal(requestData.getAngleRelativeToMedians()));
    }

}
