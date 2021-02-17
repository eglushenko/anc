package ua.anc.test.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.exception.EntityNotFoundException;
import ua.anc.test.application.pojo.client.ClientPOJO;
import ua.anc.test.application.pojo.client.ClientReadPOJO;
import ua.anc.test.application.entity.Client;
import ua.anc.test.application.repo.ClientRepo;
import ua.anc.test.application.repo.FamilyRepo;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private FamilyRepo familyRepo;

    private Client getClientFromRepository(UUID id) {
        return clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Client.class, id));
    }

    private Family getFamilyFromRepository(UUID id) {
        return familyRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Family.class, id));
    }

    private Set<Family> translateToFamily(Set<UUID> ids) {
        Set<Family> families = new HashSet<>();
        ids.forEach(i -> {
            families.add(getFamilyFromRepository(i));
        });
        return families;
    }

    private Set<UUID> translateToUUID(Set<Family> families) {
        Set<UUID> uuids = new HashSet<>();
        families.forEach(f ->{
            uuids.add(f.getId());
        });
        return uuids;
    }

    private ClientReadPOJO toRead(Client client){
        ClientReadPOJO clientReadPOJO = new ClientReadPOJO();
        clientReadPOJO.setId(client.getId());
        clientReadPOJO.setFirstName(client.getFirstName());
        clientReadPOJO.setLastName(client.getLastName());
        clientReadPOJO.setPatronymic(client.getPatronymic());
        clientReadPOJO.setClientPriorityStatus(client.getClientPriorityStatus());
        clientReadPOJO.setINN(client.getINN());
        clientReadPOJO.setMaritalStatus(client.getMaritalStatus());
        clientReadPOJO.setDateOfBirth(client.getDateOfBirth());
        clientReadPOJO.setFamilies(translateToUUID(client.getFamilies()));
        return clientReadPOJO;
    }

    public List<ClientReadPOJO> getAllClients(){
        List<Client> clients = clientRepo.findAll();
        List<ClientReadPOJO> clientReadPOJOS = new ArrayList<>();
        clients.forEach(c ->{
            clientReadPOJOS.add(toRead(c));
        });

        return clientReadPOJOS;
    }


    public ClientReadPOJO getClient(UUID id){
        return toRead(getClientFromRepository(id));
    }


    public ClientReadPOJO create (ClientPOJO create){
        Client client = new Client();
        client.setFirstName(create.getFirstName());
        client.setLastName(create.getLastName());
        client.setPatronymic(create.getPatronymic());
        client.setClientPriorityStatus(create.getClientPriorityStatus());
        client.setINN(create.getINN());
        client.setMaritalStatus(create.getMaritalStatus());
        client.setDateOfBirth(create.getDateOfBirth());
        client.setFamilies(translateToFamily(create.getFamilies()));
        client = clientRepo.save(client);
        return toRead(client);
    }

    public ClientReadPOJO update (UUID id, ClientPOJO update){
        Client client = getClientFromRepository(id);

        if (update.getFirstName()!= null){
            client.setFirstName(update.getFirstName());
        }
        if (update.getLastName() != null){
            client.setLastName(update.getLastName());
        }
        if (update.getPatronymic() != null){
            client.setPatronymic(update.getPatronymic());
        }
        if (update.getINN() != null){
            client.setINN(update.getINN());
        }
        if (update.getClientPriorityStatus()!= null){
            client.setClientPriorityStatus(update.getClientPriorityStatus());
        }
        if (update.getDateOfBirth() != null){
            client.setDateOfBirth(update.getDateOfBirth());
        }
        if (update.getMaritalStatus() != null){
            client.setMaritalStatus(update.getMaritalStatus());
        }
        if (update.getEducation() != null){
            client.setEducation(update.getEducation());
        }
        if (update.getFamilies() != null){
            client.setFamilies(translateToFamily(update.getFamilies()));
        }
        client = clientRepo.save(client);

        return toRead(client);
    }

    public ClientReadPOJO patchClient(UUID id,ClientPOJO patch){
        Client client = getClientFromRepository(id);
        client.setFirstName(patch.getFirstName());
        client.setLastName(patch.getLastName());
        client.setPatronymic(patch.getPatronymic());
        client.setClientPriorityStatus(patch.getClientPriorityStatus());
        client.setINN(patch.getINN());
        client.setMaritalStatus(patch.getMaritalStatus());
        client.setDateOfBirth(patch.getDateOfBirth());
        client = clientRepo.save(client);
        return toRead(client);
    }

    public void delete(UUID id){
        clientRepo.delete(getClientFromRepository(id));
    }
}
