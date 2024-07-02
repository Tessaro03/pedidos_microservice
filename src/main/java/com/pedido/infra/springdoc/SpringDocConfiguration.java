package com.pedido.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI custOpenAPI(){
        return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title("Pedidos-food API")
            .description("")
            .contact(new Contact()
            .name("Time Backend"))
            .license(new License()
            .name("Linkedin Desenvolvedor")
            .url("https://www.linkedin.com/in/gabriel-tessaro-b29728216/")));
    }
}
