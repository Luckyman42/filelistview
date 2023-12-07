package  com.example.filelistview.config;

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
    @Value("${filelistview.openapi.dev-1-url}")
    private String dev1Url;
    @Value("${filelistview.openapi.dev-2-url}")
    private String dev2Url;
    @Bean
    public OpenAPI myOpenAPI() {
        Server dev1Server = new Server();
        dev1Server.setUrl(dev1Url);
        dev1Server.setDescription("Server 1 Url");

        Server dev2Server = new Server();
        dev2Server.setUrl(dev2Url);
        dev2Server.setDescription("Server 2 Url");

        Info info = new Info()
                .title("Files Tree List View API")
                .version("1.0")
                .description("This API exposes endpoints.");
        return new OpenAPI().info(info).servers(List.of(dev1Server,dev2Server));
    }
}