package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import learningmanagement.LecturesupportApplication;
import learningmanagement.domain.AssignmentGraded;
import lombok.Data;

@Entity
@Table(name = "Assignment_table")
@Data
//<<< DDD / Aggregate Root
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private StudyId studyId;

    private String assignment;

    private String submitContent;

    private String submitScore;

    private String feedback;

    public static AssignmentRepository repository() {
        AssignmentRepository assignmentRepository = LecturesupportApplication.applicationContext.getBean(
            AssignmentRepository.class
        );
        return assignmentRepository;
    }

    //<<< Clean Arch / Port Method
    public static void aiBasedGrade(Submitted submitted) {
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> lectureMap = mapper.convertValue(submitted.getLectureId(), Map.class);
        Map<Long, Object> userMap = mapper.convertValue(submitted.getUserId(), Map.class);


        RestTemplate restTemplate = new RestTemplate();
        String lectureUrl = "http :8082/lectrues" + lectureMap.get("id");
        ResponseEntity<Map> lectureResponse = restTemplate.getForEntity(lectureUrl, Map.class);
        String assignmentContent = lectureResponse.getBody().get("assignment").toString();

        Assignment assignment = new Assignment();
        assignment.setAssignment(assignmentContent);
        assignment.setSubmitContent(submitted.getAssignment());
        
        // AI 평가 수행
        AzureAIService aiService = LecturesupportApplication.applicationContext.getBean(AzureAIService.class);
        Map<String, String> evaluation = aiService.evaluateAssignment(
            assignment.getAssignment(),
            assignment.getSubmitContent()
        );
        
        assignment.setSubmitScore(evaluation.get("score"));
        assignment.setFeedback(evaluation.get("feedback"));
        
        repository().save(assignment);

        AssignmentGraded assignmentGraded = new AssignmentGraded(assignment);
        assignmentGraded.publishAfterCommit();


    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
