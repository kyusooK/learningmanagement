package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TutorChecked extends AbstractEvent {

    private Long id;
    private Boolean tutorApprove;

    public TutorChecked(User aggregate) {
        super(aggregate);
    }

    public TutorChecked() {
        super();
    }
}
//>>> DDD / Domain Event
