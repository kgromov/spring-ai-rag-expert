package guru.springframework.springairagexpert.config;

import io.milvus.client.MilvusServiceClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.MilvusVectorStore;
import org.springframework.ai.vectorstore.MongoDBAtlasVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class VectorStoreConfig {

    @Profile("milvus")
    @Qualifier("vectorStore")
    @Bean
    public VectorStore milvusVectorStore(MilvusServiceClient milvusClient, EmbeddingModel embeddingModel) {
        return new MilvusVectorStore(milvusClient, embeddingModel, true);
    }

    @Profile("mongodb")
    @Qualifier("vectorStore")
    @Bean
    public VectorStore mongodbVectorStore(MongoTemplate mongoTemplate, EmbeddingModel embeddingModel) {
        return new MongoDBAtlasVectorStore(mongoTemplate, embeddingModel, true);
    }
}
