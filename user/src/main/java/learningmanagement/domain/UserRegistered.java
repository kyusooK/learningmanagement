package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserRegistered extends AbstractEvent {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private Boolean isTutor;
    private Boolean tutorApprove;
    private String interest;

    public UserRegistered(User aggregate) {
        super(aggregate);
    }

    public UserRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
