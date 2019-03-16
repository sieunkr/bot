package eddy.bot.broker.entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AirController.class)
public class AirControllerTest {

    @Test
    public void findAll() {

    }

    @Test
    public void findByStationName() {
    }
}