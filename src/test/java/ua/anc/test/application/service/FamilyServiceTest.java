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
import ua.anc.test.application.pojo.family.FamilyReadPOJO;
import ua.anc.test.application.repo.FamilyRepo;

import java.util.UUID;



@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyServiceTest {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyRepo familyRepo;

    private Family createFamily(){
        Family family = new Family();
        family.setName("My family");
        family = familyRepo.save(family);
        return family;
    }

    @Test
    @Transactional
    public void testGetFamily(){

        Family family = createFamily();
        FamilyReadPOJO read = familyService.getFamily(family.getId());

        Assertions.assertThat(read).isEqualToComparingFieldByField(family);

    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetFamilyWrongId() {
        familyService.getFamily(UUID.randomUUID());
    }

    @Test
    public void testDeleteFamily() {
        Family family = createFamily();

        familyService.delete(family.getId());

        Assert.assertFalse(familyRepo.existsById(family.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteFamilyNotFoundId() {
        familyService.delete(UUID.randomUUID());
    }
}