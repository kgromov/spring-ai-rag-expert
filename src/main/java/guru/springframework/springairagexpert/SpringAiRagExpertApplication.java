package guru.springframework.springairagexpert;

import guru.springframework.springairagexpert.config.VectorStoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({VectorStoreProperties.class})
@SpringBootApplication
public class SpringAiRagExpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiRagExpertApplication.class, args);
    }

}
