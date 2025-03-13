package learningmanagement.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

import learningmanagement.UserApplication;
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

    @Lob
    private String suggestionLecture;

    @PostPersist
    public void onPostPersist() {
        if(this.getIsTutor() != null && this.getIsTutor().equals(true)){

        }else{
            if(this.getInterest() !=null){
                UserRegistered userRegistered = new UserRegistered(this);
                userRegistered.publishAfterCommit();
            }
        }
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    public void checkTutor(CheckTutorCommand checkTutorCommand) {
        repository().findById(this.getId()).ifPresent(user ->{

            this.setTutorApprove(checkTutorCommand.getTutorApprove());

            TutorChecked tutorChecked = new TutorChecked(this);
            tutorChecked.publishAfterCommit();

        });
    }

    public static void suggestLecture(LectureSuggested lectureSuggested) {
       
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> userMap = mapper.convertValue(lectureSuggested.getUserId(), Map.class);

        repository().findById(Long.valueOf(userMap.get("id").toString())).ifPresent(user->{
            
            user.setSuggestionLecture(lectureSuggested.getSuggestionContent());
            repository().save(user);

        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
