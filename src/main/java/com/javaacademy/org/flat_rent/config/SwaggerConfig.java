package com.javaacademy.org.flat_rent.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Проект \"Сервис суточного бронирования квартир и комнат\"")
                .version("1.0.0")
                .description("API для управления бронированием квартир и комнат")
                .contact(new Contact()
                        .name("Чернов Андрей")
                        .email("che34@mail.ru")
                );
        return new OpenAPI().info(info);
    }
}
