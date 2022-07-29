package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public SecurityFilter securityFilter(){
        return new SecurityFilter();
    }
    @Bean
    public FilterRegistrationBean securityFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(securityFilter());
        registration.addUrlPatterns("/app/*");
        registration.setName("securityFilter");
        registration.setOrder(1);
        return registration;
    }


}
