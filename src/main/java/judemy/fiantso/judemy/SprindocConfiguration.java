package judemy.fiantso.judemy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SprindocConfiguration {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info().title("Judemy documentation").version("1.0.0").description("Judemy is a " +
                "platform that provides a wide range of educational resources, including video lessons, quizzes, and\n" +
                "interactive content. Users can sign up, browse courses, watch lessons, take quizzes, and track their " +
                "progress. Teachers\n" +
                "can create and manage courses, add lessons, and assess students' performance."));
    }
}
