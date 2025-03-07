package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LectureSuggested extends AbstractEvent {

    private Long id;
    private UserId userId;
    private LectureId lectureId;
    private String suggestionContent;

    public LectureSuggested(LectureSuggestion aggregate) {
        super(aggregate);
    }

    public LectureSuggested() {
        super();
    }
}
//>>> DDD / Domain Event
