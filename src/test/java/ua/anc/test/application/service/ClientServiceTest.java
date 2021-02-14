package ua.anc.test.application.service;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.exception.EntityNotFoundException;
import ua.anc.test.application.pojo.client.ClientReadPOJO;
import ua.anc.test.application.entity.Client;
import ua.anc.test.application.repo.ClientRepo;
import ua.anc.test.application.repo.FamilyRepo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private FamilyRepo familyRepo;

    private Family createFamily(){
        Family family = new Family();
        family.setName("My family");
        family = familyRepo.save(family);
        return family;
    }

    private Client createClient(){
        Client client = new Client();
        client.setFirstName("Jhon");
        client.setLastName("Dou");
        client.setPatronymic("Adamovich");
        client.setDateOfBirth(LocalDate.parse("2010-12-31"));
        client = clientRepo.save(client);
        return client;
    }

    @Test
    @Transactional
    public void testGetClient(){

        Client client = createClient();
        ClientReadPOJO read = clientService.getClient(client.getId());

        Assertions.assertThat(read).isEqualToComparingFieldByField(client);

    }

    @Test
    @Transactional
    public void testGetClientWithFamily(){

        Client client = new Client();
        client.setFirstName("Jhon");
        client.setLastName("Dou");
        client.setPatronymic("Adamovich");
        client.setDateOfBirth(LocalDate.parse("2010-12-31"));
        client = clientRepo.save(client);

        Family family = createFamily();
        Set<Family> familySet = new HashSet<>();
        familySet.add(family);
        client.setFamilies(familySet);
        client = clientRepo.save(client);
        ClientReadPOJO read = clientService.getClient(client.getId());

        Assertions.assertThat(read).isEqualToIgnoringGivenFields(client, "families");
        Assert.assertTrue(read.getFamilies().contains(family.getId()));

    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetClientWrongId() {
        clientService.getClient(UUID.randomUUID());
    }

    @Test
    public void testDeleteClient() {
        Client client = createClient();

        clientService.delete(client.getId());

        Assert.assertFalse(clientRepo.existsById(client.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteClientNotFoundId() {
        clientService.delete(UUID.randomUUID());
    }


}