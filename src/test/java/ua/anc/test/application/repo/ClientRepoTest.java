package ua.anc.test.application.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.anc.test.application.entity.Client;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRepoTest {

    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void testSaveClient(){
        Client client = new Client();
        client.setFirstName("Jhon");
        client.setLastName("Dou");
        client = clientRepo.save(client);
        Assert.assertNotNull(client.getId());
        assertTrue(clientRepo.findById(client.getId()).isPresent());
    }
}