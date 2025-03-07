package learningmanagement.infra;

import learningmanagement.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AssignmentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Assignment>> {

    @Override
    public EntityModel<Assignment> process(EntityModel<Assignment> model) {
        return model;
    }
}
