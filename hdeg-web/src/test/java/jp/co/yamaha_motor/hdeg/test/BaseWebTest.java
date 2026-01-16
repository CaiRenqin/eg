package jp.co.yamaha_motor.hdeg.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for web module test cases.
 * Use the custom {@link HdegTestApplication} to run the test so the unnecessary
 * part can be excluded.
 * Just focus on the specific functions to be tested.
 */
@SpringBootTest(classes = { HdegTestApplication.class })
@ActiveProfiles({ "development-test", "h2-test" })
@Rollback
@AutoConfigureMockMvc
public abstract class BaseWebTest {
}
