package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
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
        //implement business logic here:

        /** Example 1:  new item 
        LectureSuggestion lectureSuggestion = new LectureSuggestion();
        repository().save(lectureSuggestion);

        LectureSuggested lectureSuggested = new LectureSuggested(lectureSuggestion);
        lectureSuggested.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(userRegistered.get???()).ifPresent(lectureSuggestion->{
            
            lectureSuggestion // do something
            repository().save(lectureSuggestion);

            LectureSuggested lectureSuggested = new LectureSuggested(lectureSuggestion);
            lectureSuggested.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
