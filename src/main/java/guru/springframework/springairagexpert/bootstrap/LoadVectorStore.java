package guru.springframework.springairagexpert.bootstrap;

import guru.springframework.springairagexpert.config.VectorStoreProperties;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.ListDatabasesResponse;
import io.milvus.param.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoadVectorStore implements CommandLineRunner {
    private final VectorStore vectorStore;
    private final VectorStoreProperties vectorStoreProperties;
    private final MilvusServiceClient milvusClient;

    @Override
    public void run(String... args) {
        R<ListDatabasesResponse> databases = milvusClient.listDatabases();
        databases.getData().getDbNamesList().forEach(log::info);
        if (vectorStore.similaritySearch("Sportsman").isEmpty()) {
            log.info("Loading documents into vector store");
            vectorStoreProperties.documentsToLoad().forEach(document -> {
                log.debug("Loading document: " + document.getFilename());
                var documentReader = new TikaDocumentReader(document);
                var documents = documentReader.get();
                TextSplitter textSplitter = new TokenTextSplitter();
                List<Document> splitDocuments = textSplitter.apply(documents);
                vectorStore.add(splitDocuments);
            });
        }
        log.info("Vector store loaded");
    }
}



















