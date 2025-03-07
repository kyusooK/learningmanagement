package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
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
        //implement business logic here:

        /** Example 1:  new item 
        Assignment assignment = new Assignment();
        repository().save(assignment);

        AssignmentGraded assignmentGraded = new AssignmentGraded(assignment);
        assignmentGraded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if submitted.lectureIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> studyMap = mapper.convertValue(submitted.getLectureId(), Map.class);
        // Map<Long, Object> studyMap = mapper.convertValue(submitted.getUserId(), Map.class);

        repository().findById(submitted.get???()).ifPresent(assignment->{
            
            assignment // do something
            repository().save(assignment);

            AssignmentGraded assignmentGraded = new AssignmentGraded(assignment);
            assignmentGraded.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
