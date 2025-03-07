package learningmanagement.domain;

import learningmanagement.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "assignments",
    path = "assignments"
)
public interface AssignmentRepository
    extends PagingAndSortingRepository<Assignment, Long> {}
