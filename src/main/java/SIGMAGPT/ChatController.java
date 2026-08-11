package SIGMAGPT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @GetMapping("/chat")
    public String generate(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        try {
            String url = "https://api.groq.com/openai/v1/chat/completions";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(groqApiKey);

            Map<String, Object> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", message);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "llama-3.3-70b-versatile");
            requestBody.put("messages", List.of(userMessage));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            Map response = restTemplate.postForObject(url, entity, Map.class);

            String aiResponse = "Groq se koi reply nahi aaya.";

            if (response != null && response.containsKey("choices")) {
                List choices = (List) response.get("choices");
                if (!choices.isEmpty()) {
                    Map firstChoice = (Map) choices.get(0);
                    Map messageObj = (Map) firstChoice.get("message");
                    aiResponse = messageObj.get("content").toString();
                }
            }

            // Message Database me save ho raha hai
            ChatMessage chatMessage = new ChatMessage(message, aiResponse);
            chatRepository.save(chatMessage);

            return aiResponse;

        } catch (Exception e) {
            return "Error calling Groq API: " + e.getMessage();
        }
    }

    // Purani chat history fetch karne ke liye API
    @GetMapping("/history")
    public List<ChatMessage> getHistory() {
        return chatRepository.findAll();
    }
}