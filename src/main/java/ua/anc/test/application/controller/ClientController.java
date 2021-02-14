package ua.anc.test.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.anc.test.application.pojo.client.ClientPOJO;
import ua.anc.test.application.pojo.client.ClientReadPOJO;
import ua.anc.test.application.service.ClientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    private List<ClientReadPOJO> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientReadPOJO getClient(@PathVariable UUID id){
        return clientService.getClient(id);
    }

    @PostMapping
    public ClientReadPOJO createClient (@RequestBody ClientPOJO create){
        return clientService.create(create);
    }

    @PatchMapping ("/{id}")
    public ClientReadPOJO updateClient (@PathVariable UUID id, @RequestBody ClientPOJO update){
        return clientService.update(id, update);
    }

    /**
     * Patch not update families
     * @param id format UUID
     * @param patch ClientPOJO
     * @return ClientReadPOJO
     */
    @PutMapping("/{id}")
    public ClientReadPOJO putClient (@PathVariable UUID id, @RequestBody ClientPOJO patch){
        return clientService.patchClient(id, patch);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable UUID id){
        clientService.delete(id);
    }
}
