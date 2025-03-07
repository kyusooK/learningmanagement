package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Submitted extends AbstractEvent {

    private Long id;
    private LectureId lectureId;
    private UserId userId;
    private String assignment;
    private Boolean isSubmit;

    public Submitted(Study aggregate) {
        super(aggregate);
    }

    public Submitted() {
        super();
    }
}
//>>> DDD / Domain Event
