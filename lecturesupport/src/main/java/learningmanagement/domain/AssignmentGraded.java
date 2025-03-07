package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AssignmentGraded extends AbstractEvent {

    private Long id;
    private StudyId studyId;
    private String assignment;
    private String submitContent;
    private String submitScore;
    private String feedback;

    public AssignmentGraded(Assignment aggregate) {
        super(aggregate);
    }

    public AssignmentGraded() {
        super();
    }
}
//>>> DDD / Domain Event
