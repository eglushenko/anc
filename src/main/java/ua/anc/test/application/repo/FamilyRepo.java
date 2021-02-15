package ua.anc.test.application.repo;

import org.springframework.data.repository.CrudRepository;
import ua.anc.test.application.entity.Family;

import java.util.UUID;

public interface FamilyRepo extends CrudRepository<Family, UUID> {

}
