package learningmanagement.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SubmitCommand {

    private Long id;
    private String assignment;
}
