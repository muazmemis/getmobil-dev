package dev.muazmemis.getmobil_dev.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${server.servlet.context-path}") String contextPath) {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://ec2-3-238-15-179.compute-1.amazonaws.com:8080" + contextPath).description("AWS"),
                        new Server().url("http://localhost:8080" + contextPath).description("localhost"),
                        new Server().url("http://localhost:8081" + contextPath).description("localhost2")))
                .info(new Info().title("GetMobil API")
                        .description("This is the REST API for GetMobil")
                        .version("1.0.0")
                        .contact(new Contact().name("GetMobil").url("https://getmobil.com").email("info@getmobil.com"))
                        .license(new License().name("Apache 2.0").url("https://apache.org")));
    }

}
