package com.devup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Properties;
@EnableSwagger2
@Configuration
@EnableWebMvc
@ComponentScan("com.devup")
@EnableJpaRepositories("com.devup.repository")
@EnableTransactionManagement
@PropertySource(value  = {
        "classpath:application.properties"} )
public class MyAppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment environment;

//    Start Spring JPA config details
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean local=new LocalContainerEntityManagerFactoryBean();
        local.setJpaVendorAdapter(getJpaVendorAdaper());
        local.setDataSource(datasource());
        local.setPersistenceUnitName("myJpaPersistenceUnit");
        local.setPackagesToScan("com.devup");
        local.setJpaProperties(hibernateProperties());
        return local;
    }
@Bean
    public Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return properties;
}

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    @Bean
    public JpaVendorAdapter getJpaVendorAdaper() {
        JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        return adapter;
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                localContainerEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
@Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .select().apis(RequestHandlerSelectors.any())
            .build().pathMapping("/")
            .apiInfo(metaData());

}
    private ApiInfo metaData(){

        Contact contact = new Contact("pooya-fils", "https://github.com/pooyafils","" +
                "mygithub");

        return new ApiInfo(
                "spring mvc rest ",
                " product api",
                "1.0",
                "Terms of Service",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
