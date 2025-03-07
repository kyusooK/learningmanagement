package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import learningmanagement.StudyApplication;
import learningmanagement.domain.LectureApplied;
import lombok.Data;

@Entity
@Table(name = "Study_table")
@Data
//<<< DDD / Aggregate Root
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private LectureId lectureId;

    @Embedded
    private UserId userId;

    private String assignment;

    private Boolean isSubmit;

    private Integer submitScore;

    private String feedback;

    @PostPersist
    public void onPostPersist() {
        LectureApplied lectureApplied = new LectureApplied(this);
        lectureApplied.publishAfterCommit();
    }

    public static StudyRepository repository() {
        StudyRepository studyRepository = StudyApplication.applicationContext.getBean(
            StudyRepository.class
        );
        return studyRepository;
    }

    //<<< Clean Arch / Port Method
    public void submit(SubmitCommand submitCommand) {
        //implement business logic here:

        Submitted submitted = new Submitted(this);
        submitted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateSubmitScore(AssignmentGraded assignmentGraded) {
        //implement business logic here:

        /** Example 1:  new item 
        Study study = new Study();
        repository().save(study);

        */

        /** Example 2:  finding and process
        
        // if assignmentGraded.studyId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> assignmentMap = mapper.convertValue(assignmentGraded.getStudyId(), Map.class);

        repository().findById(assignmentGraded.get???()).ifPresent(study->{
            
            study // do something
            repository().save(study);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
