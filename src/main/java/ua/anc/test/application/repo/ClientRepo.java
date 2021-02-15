package ua.anc.test.application.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.anc.test.application.entity.Client;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepo extends CrudRepository<Client, UUID> {

    List<Client> findAll();



}

