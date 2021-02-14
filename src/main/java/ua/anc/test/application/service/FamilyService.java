package ua.anc.test.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.anc.test.application.entity.Client;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.pojo.family.FamilyPOJO;
import ua.anc.test.application.pojo.family.FamilyReadPOJO;
import ua.anc.test.application.repo.ClientRepo;
import ua.anc.test.application.repo.FamilyRepo;

import java.util.*;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepo familyRepo;

    @Autowired
    private ClientRepo clientRepo;

    private FamilyReadPOJO toRead (Family family){
        FamilyReadPOJO familyReadPOJO = new FamilyReadPOJO();
        familyReadPOJO.setName(family.getName());
        familyReadPOJO.setId(family.getId());
        familyReadPOJO.setClients(translateToUUID(family.getClients()));
        return familyReadPOJO;
    }

    private Set<Client> translateToClient(Set<UUID> ids){
        Set<Client> clients = new HashSet<>();
        ids.forEach(i ->{
            clients.add(clientRepo.findByIdOrError(i));
        });
        return clients;
    }

    private Set<UUID> translateToUUID(Set<Client> clients){
        Set<UUID> uuids = new HashSet<>();
        clients.forEach(f ->{
            uuids.add(f.getId());
        });
        return uuids;
    }

    public FamilyReadPOJO getFamily(UUID id){
        return toRead(familyRepo.findByIdOrError(id));
    }

    public FamilyReadPOJO patchFamily (UUID id, FamilyPOJO patch) {
        Family family = familyRepo.findByIdOrError(id);
        family.setName(patch.getName());
        if(patch.getClients() != null){
            family.setClients(translateToClient(patch.getClients()));
        }
        family = familyRepo.save(family);
        return toRead(family);
    }

    public FamilyReadPOJO create(FamilyPOJO create, UUID clientId){

        Family family = new Family();
        family.setName(create.getName());
        if (create.getClients() != null){
            family.setClients(translateToClient(create.getClients()));
        }else{
            Client client = clientRepo.findByIdOrError(clientId);
            Set<Client> clients = new HashSet<>();
            clients.add(client);
            family.setClients(clients);
        }
        family = familyRepo.save(family);
        return toRead(family);
    }

    public FamilyReadPOJO update(UUID id, FamilyPOJO update){
        Family family = familyRepo.findByIdOrError(id);
        if(update.getName() != null){
            family.setName(update.getName());
        }
        if(update.getClients() != null){
            family.setClients(translateToClient(update.getClients()));
        }
        family = familyRepo.save(family);
        return toRead(family);
    }

    public void delete(UUID id){
        familyRepo.delete(familyRepo.findByIdOrError(id));
    }
}
