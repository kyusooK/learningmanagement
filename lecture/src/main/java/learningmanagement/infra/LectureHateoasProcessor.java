package learningmanagement.infra;

import learningmanagement.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class LectureHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Lecture>> {

    @Override
    public EntityModel<Lecture> process(EntityModel<Lecture> model) {
        return model;
    }
}
