package learningmanagement.domain;

import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

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
}
