package learningmanagement.domain;

import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class LectureSuggested extends AbstractEvent {

    private Long id;
    private Object userId;
    private Object lectureId;
    private String suggestionContent;
}
