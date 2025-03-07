package learningmanagement.domain;

import learningmanagement.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "lectureSuggestions",
    path = "lectureSuggestions"
)
public interface LectureSuggestionRepository
    extends PagingAndSortingRepository<LectureSuggestion, Long> {}
