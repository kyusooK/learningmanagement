package learningmanagement.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;

@Service
public class AzureAIService {
    @Value("${azure.openai.endpoint}")
    private String azureEndpoint;
    
    @Value("${azure.openai.key}")
    private String azureKey;

    @Value("${azure.openai.deployment}")
    private String deploymentName;

    private final RestTemplate restTemplate;
    
    public AzureAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Map<String, String> evaluateAssignment(String assignment, String submitContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", azureKey);
        
        String prompt = String.format(
            "다음 과제와 제출된 결과물을 분석하여, 점수(0-100)를 매기고 피드백을 제공해주세요. 응답 형식: '점수: [점수]\\n\\n[피드백]'\n\n과제내용:\n%s\n\n제출결과물:\n%s",
            assignment.replace("\n", "\\n"),
            submitContent.replace("\n", "\\n")
        );
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
    
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", Collections.singletonList(message));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 800);
    
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                azureEndpoint + "/openai/deployments/" + deploymentName + "/chat/completions?api-version=2023-05-15",
                request,
                String.class
            );
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String content = root.path("choices").get(0).path("message").path("content").asText();
            
            // API 응답 처리
            String[] parts = content.split("\\n\\n", 2);
            String scoreText = parts[0].split(": ")[1].trim();
            String feedback = parts[1];
            
            // 점수가 소수점이 있는 경우 정수로 변환
            double scoreDouble = Double.parseDouble(scoreText);
            int scoreInt = (int) Math.round(scoreDouble);
            
            // 점수 범위 확인 (0-100)
            scoreInt = Math.min(100, Math.max(0, scoreInt));
            
            Map<String, String> result = new HashMap<>();
            result.put("score", String.valueOf(scoreInt));  // 정수 점수를 문자열로 변환
            result.put("feedback", feedback);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to evaluate assignment: " + e.getMessage(), e);
        }
    }

    // 

    public String suggestLectures(List<String> categories, String userInterest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", azureKey);
        
        String prompt = String.format(
            "다음 정보를 바탕으로 사용자에게 적합한 강의 카테고리를 최대 3개 추천해주세요. 응답 형식: '추천카테고리: [카테고리1], [카테고리2], [카테고리3]'\n\n" +
            "현재 제공되는 강의 카테고리 목록:\n%s\n\n사용자의 관심 분야:\n%s",
            String.join(", ", categories),
            userInterest
        );
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
    
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", Collections.singletonList(message));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 300);
    
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                azureEndpoint + "/openai/deployments/" + deploymentName + "/chat/completions?api-version=2023-05-15",
                request,
                String.class
            );
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String content = root.path("choices").get(0).path("message").path("content").asText();
            
            return content.split(": ")[1].trim();
        } catch (Exception e) {
            throw new RuntimeException("Failed to suggest lectures: " + e.getMessage(), e);
        }
    }
    
    public String generateSuggestionContent(List<String> lectureTitles) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", azureKey);
        
        String prompt = String.format(
            "다음 강의 목록을 소개하는 추천 메시지를 작성해주세요. 응답은 '수강생의 관심강의 분야에 추천할만한 강의를 소개드립니다.' 로 시작해야 합니다.\n\n강의목록:\n%s",
            String.join("\n", lectureTitles)
        );
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
    
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", Collections.singletonList(message));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);
    
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            System.out.println("Sending request to Azure OpenAI API: " + requestBody);
            
            ResponseEntity<String> response = restTemplate.postForEntity(
                azureEndpoint + "/openai/deployments/" + deploymentName + "/chat/completions?api-version=2023-05-15",
                request,
                String.class
            );
            
            System.out.println("Received response from Azure OpenAI API: " + response.getBody());
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate suggestion content: " + e.getMessage(), e);
        }
    }
}
