package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import learningmanagement.LecturesupportApplication;
import learningmanagement.domain.LectureSuggested;
import lombok.Data;

@Entity
@Table(name = "LectureSuggestion_table")
@Data
//<<< DDD / Aggregate Root
public class LectureSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private LectureId lectureId;

    private String suggestionContent;

    public static LectureSuggestionRepository repository() {
        LectureSuggestionRepository lectureSuggestionRepository = LecturesupportApplication.applicationContext.getBean(
            LectureSuggestionRepository.class
        );
        return lectureSuggestionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void aiBasedSuggestLecture(UserRegistered userRegistered) {
    // 1. Lecture 서비스에서 모든 강의 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Map>> lectureResponse = restTemplate.exchange(
            "http://localhost:8082/lectures",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Map>>() {}
        );
        List<Map> lectures = lectureResponse.getBody();
        
        // 카테고리 목록 추출
        List<String> allCategories = lectures.stream()
        .map(lecture -> lecture.get("category").toString())
        .distinct()
        .collect(Collectors.toList());

        // 2. AI를 사용하여 사용자의 관심사에 맞는 카테고리 추천받기
        String recommendedCategories = LecturesupportApplication.applicationContext
            .getBean(AzureAIService.class)
            .suggestLectures(allCategories, userRegistered.getInterest());

        // 3. 추천받은 카테고리에 해당하는 강의 찾기
        List<String> recommendedCategoryList = Arrays.asList(recommendedCategories.split(",\\s*"));
        List<String> recommendedLectureTitles = lectures.stream()
            .filter(lecture -> recommendedCategoryList.contains(lecture.get("category").toString()))
            .map(lecture -> lecture.get("title").toString())
            .limit(3)
            .collect(Collectors.toList());
        
        // 4. AI를 사용하여 추천 메시지 생성
        String suggestionContent = LecturesupportApplication.applicationContext
            .getBean(AzureAIService.class)
            .generateSuggestionContent(recommendedLectureTitles);
        
        // 5. LectureSuggestion 생성 및 저장
        LectureSuggestion lectureSuggestion = new LectureSuggestion();
        lectureSuggestion.setUserId(new UserId(userRegistered.getId()));
        lectureSuggestion.setSuggestionContent(suggestionContent);
        repository().save(lectureSuggestion);
        
        // 6. 이벤트 발행
        LectureSuggested lectureSuggested = new LectureSuggested(lectureSuggestion);
        lectureSuggested.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
