package jp.co.yamaha_motor.hdeg.test.feature;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.yamaha_motor.hdeg.test.BaseWebTest;
import jp.co.yamaha_motor.hdeg.test.HdegTestApplication;

/**
 * Sample test for web module.
 * Use the custom {@link HdegTestApplication} to run the test so the unnecessary
 * part can be excluded.
 * Just focus on the specific functions to be tested.
 */
@Import(WebTestCase.TestController.class)
class WebTestCase extends BaseWebTest {

    @Autowired
    private MockMvc target;

    @Test
    void test() throws Exception {

        MvcResult result = target.perform(MockMvcRequestBuilders.post("/test")).andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("test", result.getResponse().getContentAsString());
    }

    @RestController
    public static class TestController {
        @PostMapping("/test")
        public String test() {
            return "test";
        }
    }
}
