package learningmanagement.domain;

import learningmanagement.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "studies", path = "studies")
public interface StudyRepository
    extends PagingAndSortingRepository<Study, Long> {}
