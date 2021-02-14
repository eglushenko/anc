package ua.anc.test.application.repo;

import org.springframework.data.repository.CrudRepository;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.exception.EntityNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FamilyRepo extends CrudRepository<Family, UUID> {
    default Family findByIdOrError(UUID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(Family.class, id));
    }
}
