package learningmanagement.infra;

import learningmanagement.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudyHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Study>> {

    @Override
    public EntityModel<Study> process(EntityModel<Study> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/submit")
                .withRel("submit")
        );

        return model;
    }
}
