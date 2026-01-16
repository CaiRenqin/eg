package jp.co.yamaha_motor.hdeg.test.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.core.io.Resource;
import jp.co.yamaha_motor.hdeg.test.util.CommonTestUtil;

public class BaseFeatureTest {
    @Autowired
    private CommonTestUtil commonUtil;
    @Autowired
    protected MockMvc mockMvc;

    protected MvcResult mvcResult;

    public void seedUp(Resource testData) {
        commonUtil.seedUp(testData);
    }

    public void setup() {

    }
}
