package guru.springframework.springairagexpert.service;

import guru.springframework.springairagexpert.model.Answer;
import guru.springframework.springairagexpert.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OpenAIService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public Answer getAnswer(Question question) {
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.query(question.question()).withTopK(5)
        );
        List<String> contentList = documents.stream().map(Document::getContent).toList();
        return new Answer(
                chatClient.prompt()
                        .user(message -> message.params(Map.of(
                                "input", question.question(),
                                "documents", String.join("\n", contentList))
                        ))
                        .call()
                        .content()
        );
    }
}
