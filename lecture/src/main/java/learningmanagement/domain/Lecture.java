package learningmanagement.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import learningmanagement.LectureApplication;
import learningmanagement.domain.LectureUploaded;
import lombok.Data;

@Entity
@Table(name = "Lecture_table")
@Data
//<<< DDD / Aggregate Root
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UserId userId;

    private String title;

    private String specifics;

    private String category;

    private String assignment;

    @PostPersist
    public void onPostPersist() {
        LectureUploaded lectureUploaded = new LectureUploaded(this);
        lectureUploaded.publishAfterCommit();
    }

    public static LectureRepository repository() {
        LectureRepository lectureRepository = LectureApplication.applicationContext.getBean(
            LectureRepository.class
        );
        return lectureRepository;
    }
}
//>>> DDD / Aggregate Root
