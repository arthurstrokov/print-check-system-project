package com.gmail.arthurstrokov.printcheck.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config for swagger
 *
 * @author Arthur Strokov
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Print Check System Project")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("arthurstrokov@gmail.com")
                                                .name("Arthur Strokov")
                                )
                );
    }
}
