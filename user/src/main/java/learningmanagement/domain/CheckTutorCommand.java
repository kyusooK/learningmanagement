package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CheckTutorCommand {

    private Long id;
    private Boolean tutorApprove;
}
