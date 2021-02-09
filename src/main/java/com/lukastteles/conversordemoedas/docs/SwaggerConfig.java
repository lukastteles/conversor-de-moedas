package com.lukastteles.conversordemoedas.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 * @author Lukas Teles
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Manage API documentation parameters
     * @return {@link springfox.documentation.spring.web.plugins.Docket}
     */
    @Bean
    public Docket apiDoc(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lukastteles.conversordemoedas.controller"))
                .build();
    }
}
