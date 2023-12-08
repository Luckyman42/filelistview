package  com.example.filelistview.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration is the Head of the Swagger UI
 */
@Configuration
public class OpenAPIConfig {

    /**
     * This is for swagger
     * @return New OpenApi instance
     */
    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("Files Tree List View API")
                .version("1.0")
                .description("This API exposes endpoints.");
        return new OpenAPI().info(info);
    }
}