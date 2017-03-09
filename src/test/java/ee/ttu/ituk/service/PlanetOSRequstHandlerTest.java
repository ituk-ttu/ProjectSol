package ee.ttu.ituk.service;

import ee.ttu.ituk.ProjectSolApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanetOSRequstHandlerTest {

    @Autowired
    PlanetOSRequestHandler handler;

    @Before
    public void setUp() {
//        handler = new PlanetOSRequestHandler();
    }

    @Test
    public void dateTest() {
        String[] dates = handler.getDates();
        System.out.println(Arrays.asList(dates));
    }

    @Test
    public void createProperLink() {
        System.out.println(handler.createProperLink("45", "50"));
        handler.performRequest("45", "50");
    }
}