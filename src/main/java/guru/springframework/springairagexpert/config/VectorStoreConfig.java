package guru.springframework.springairagexpert.config;

import io.milvus.client.MilvusServiceClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.MilvusVectorStore;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class VectorStoreConfig {

    @Profile("milvus")
    @Qualifier("vectorStore")
    @Bean
    public VectorStore milvusVectorStore(MilvusServiceClient milvusClient, EmbeddingModel embeddingModel) {
        return new MilvusVectorStore(milvusClient, embeddingModel, true);
    }

    @Profile("pgvector")
    @Qualifier("vectorStore")
    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel, JdbcTemplate template) {
        return new PgVectorStore(template, embeddingModel);
    }
}
