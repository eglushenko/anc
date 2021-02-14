package ua.anc.test.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.anc.test.application.pojo.family.FamilyPOJO;
import ua.anc.test.application.pojo.family.FamilyReadPOJO;
import ua.anc.test.application.service.FamilyService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class FamilyController {

    @Autowired
    private FamilyService familyService;


    @GetMapping("client/{clientId}/family/{familyId}")
    public FamilyReadPOJO getFamily(@PathVariable UUID familyId){
        return familyService.getFamily(familyId);
    }


    @PostMapping("client/{clientId}/family")
    public FamilyReadPOJO create(@PathVariable UUID clientId, @RequestBody FamilyPOJO create){
        return familyService.create(create, clientId);
    }
    @PatchMapping("client/{clientId}/family/{familyId}")
    public FamilyReadPOJO updateFamily(@PathVariable UUID familyId, @RequestBody FamilyPOJO update){
        return familyService.update(familyId, update);
    }

    /**
     * If FamilyPOJO not have clients (empty), clients field not update!!!
     * @param familyId
     * @param patch FamilyPOJO
     * @return FamilyReadPOJO
     */
    @PutMapping("client/{clientId}/family/{familyId}")
    public FamilyReadPOJO patchFamily(@PathVariable UUID familyId, @RequestBody FamilyPOJO patch){
        return familyService.patchFamily(familyId, patch);
    }

    @DeleteMapping("client/{clientId}/family/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID familyId){
        familyService.delete(familyId);
    }
}
