package Salvation.Clinic.confiq;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@EnableAsync
public class AppConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

<<<<<<< HEAD:src/main/java/Salvation/Clinic/confiq/AppConfig.java

=======
>>>>>>> origin/master:src/main/java/delivery/management/config/AppConfig.java

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

<<<<<<< HEAD:src/main/java/Salvation/Clinic/confiq/AppConfig.java
                        registry.addMapping("/**")
=======
                         registry.addMapping("/**")
>>>>>>> origin/master:src/main/java/delivery/management/config/AppConfig.java
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("Access-Control-Request-Method",
                                "Access-Control-Request-Headers", "Authorization",
<<<<<<< HEAD:src/main/java/Salvation/Clinic/confiq/AppConfig.java
                                "Access-Control-Allow-Origin", "Cache-Control", "Content-Type");
=======
                                "Access-Control-Allow-Origin", "Cache-Control", "Content-Type")
                                .allowCredentials(true);

>>>>>>> origin/master:src/main/java/delivery/management/config/AppConfig.java
            }
        };
    }






}
