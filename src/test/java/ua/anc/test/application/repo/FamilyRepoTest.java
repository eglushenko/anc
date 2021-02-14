package ua.anc.test.application.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.anc.test.application.entity.Client;
import ua.anc.test.application.entity.Family;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyRepoTest {

    @Autowired
    private FamilyRepo familyRepo;

    @Test
    public void testSaveFamily(){
        Family family = new Family();
        family.setName("My crazy family )");
        family = familyRepo.save(family);
        Assert.assertNotNull(family.getId());
        assertTrue(familyRepo.findById(family.getId()).isPresent());
    }
}