package jp.co.yamaha_motor.hdeg.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;
import javax.sql.DataSource;

@Component
public class CommonTestUtil {
    @Autowired
    protected DataSource dataSource;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public void seedUp(Resource testData) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
        populator.setScripts(testData);
        populator.execute(dataSource);
    }
}
