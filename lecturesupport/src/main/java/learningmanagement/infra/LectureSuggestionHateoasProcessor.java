package learningmanagement.infra;

import learningmanagement.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class LectureSuggestionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<LectureSuggestion>> {

    @Override
    public EntityModel<LectureSuggestion> process(
        EntityModel<LectureSuggestion> model
    ) {
        return model;
    }
}
