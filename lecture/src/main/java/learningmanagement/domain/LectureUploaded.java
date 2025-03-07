package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LectureUploaded extends AbstractEvent {

    private Long id;
    private UserId userId;
    private String title;
    private String specifics;
    private String category;
    private String assignment;

    public LectureUploaded(Lecture aggregate) {
        super(aggregate);
    }

    public LectureUploaded() {
        super();
    }
}
//>>> DDD / Domain Event
