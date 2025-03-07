package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import learningmanagement.UserApplication;
import learningmanagement.domain.UserRegistered;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private Boolean isTutor;

    private Boolean tutorApprove;

    private String interest;

    private String suggestionLecture;

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void checkTutor(CheckTutorCommand checkTutorCommand) {
        //implement business logic here:

        TutorChecked tutorChecked = new TutorChecked(this);
        tutorChecked.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void suggestLecture(LectureSuggested lectureSuggested) {
        //implement business logic here:

        /** Example 1:  new item 
        User user = new User();
        repository().save(user);

        */

        /** Example 2:  finding and process
        
        // if lectureSuggested.userIdlectureId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> lectureSuggestionMap = mapper.convertValue(lectureSuggested.getUserId(), Map.class);
        // Map<Long, Object> lectureSuggestionMap = mapper.convertValue(lectureSuggested.getLectureId(), Map.class);

        repository().findById(lectureSuggested.get???()).ifPresent(user->{
            
            user // do something
            repository().save(user);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
