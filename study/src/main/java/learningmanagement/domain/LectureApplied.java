package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LectureApplied extends AbstractEvent {

    private Long id;
    private UserId userId;
    private LectureId lectureId;
    private Boolean isSubmit;
    private String assignment;

    public LectureApplied(Study aggregate) {
        super(aggregate);
    }

    public LectureApplied() {
        super();
    }
}
//>>> DDD / Domain Event
