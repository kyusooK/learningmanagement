package learningmanagement.domain;

import java.util.*;
import learningmanagement.domain.*;
import learningmanagement.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class Submitted extends AbstractEvent {

    private Long id;
    private Object lectureId;
    private Object userId;
    private String assignment;
    private Boolean isSubmit;
}
