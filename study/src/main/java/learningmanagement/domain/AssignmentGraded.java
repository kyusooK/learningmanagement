package learningmanagement.domain;

import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class AssignmentGraded extends AbstractEvent {

    private Long id;
    private Object studyId;
    private String assignment;
    private String submitContent;
    private Integer submitScore;
    private String feedback;
}
