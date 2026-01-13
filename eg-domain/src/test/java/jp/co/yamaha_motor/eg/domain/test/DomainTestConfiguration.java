package jp.co.yamaha_motor.eg.domain.test;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ymsl.solid.jpa.config.ExternalSqlConfiguration;
import com.ymsl.solid.jpa.repository.JpaExtensionRepositoryFactoryBean;

/**
 * Configuration class for domain layer tests.
 * This class sets up component scanning, entity scanning, and JPA repository
 * configuration
 * for the domain layer tests.
 * It imports necessary auto-configurations for data source and JPA.
 * The configuration is used in tests to provide the required Spring context.
 * 
 * @see BaseDomainTest
 */
@SpringBootConfiguration
@ComponentScan(basePackages = { "jp.co.yamaha_motor.eg.hdeg" })
@EntityScan("jp.co.yamaha_motor.eg.common.entity")
// @EnableJpaRepositories(basePackages =
// {"jp.co.yamaha_motor.eg.hdeg"}, repositoryFactoryBeanClass =
// JpaExtensionRepositoryFactoryBean.class)
@ImportAutoConfiguration({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
                ExternalSqlConfiguration.class })
public class DomainTestConfiguration {
}
