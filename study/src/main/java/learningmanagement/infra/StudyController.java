package learningmanagement.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import learningmanagement.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/studies")
@Transactional
public class StudyController {

    @Autowired
    StudyRepository studyRepository;

    @RequestMapping(
        value = "/studies/{id}/submit",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Study submit(
        @PathVariable(value = "id") Long id,
        @RequestBody SubmitCommand submitCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /study/submit  called #####");
        Optional<Study> optionalStudy = studyRepository.findById(id);

        optionalStudy.orElseThrow(() -> new Exception("No Entity Found"));
        Study study = optionalStudy.get();
        study.submit(submitCommand);

        studyRepository.save(study);
        return study;
    }
}
//>>> Clean Arch / Inbound Adaptor
