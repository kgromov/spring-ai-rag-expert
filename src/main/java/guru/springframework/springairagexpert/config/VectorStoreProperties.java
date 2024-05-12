package guru.springframework.springairagexpert.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.util.List;

@ConfigurationProperties(prefix = "sfg.aiapp")
public record VectorStoreProperties(List<Resource> documentsToLoad) {
}
