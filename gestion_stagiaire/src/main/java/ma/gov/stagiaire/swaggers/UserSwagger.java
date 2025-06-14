package ma.gov.stagiaire.swaggers;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSwagger {

    @Bean
    public OpenAPI getopenAPI(){
        return new OpenAPI().info(new Info());
    }
}
