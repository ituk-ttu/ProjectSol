package ee.ttu.ituk;

import ee.ttu.ituk.data.ResponseData;
import ee.ttu.ituk.service.CalculationService;
import ee.ttu.ituk.service.PlanetOSRequestHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectSolApplicationTests {

	@Autowired
	CalculationService calculationService;

	@Autowired
	PlanetOSRequestHandler planetOSRequestHandler;

	@Test
	public void testPlanetOSRequest() {
		ResponseData responseData = planetOSRequestHandler.performRequest("45","45");
		assertTrue(responseData.getEntries().size() > 0);
	}

}
