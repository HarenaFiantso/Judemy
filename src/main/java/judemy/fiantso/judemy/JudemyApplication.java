package judemy.fiantso.judemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"judemy.fiantso", "judemy.fiantso.controller"})
public class JudemyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudemyApplication.class, args);
    }

}
