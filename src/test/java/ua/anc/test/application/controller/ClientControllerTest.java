package ua.anc.test.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.anc.test.application.entity.Client;
import ua.anc.test.application.exception.EntityNotFoundException;
import ua.anc.test.application.pojo.client.ClientPOJO;
import ua.anc.test.application.pojo.client.ClientReadPOJO;
import ua.anc.test.application.enums.ClientPriorityStatus;
import ua.anc.test.application.service.ClientService;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser("USER")
    public void testCreateClient() throws Exception {
        ClientPOJO createDTO = new ClientPOJO();
        createDTO.setFirstName("first");
        createDTO.setLastName("Last");
        createDTO.setClientPriorityStatus(ClientPriorityStatus.DEFAULT);
        createDTO.setINN("1233443252");

        ClientReadPOJO read = new ClientReadPOJO();
        read.setId(UUID.randomUUID());
        read.setFirstName("first");
        read.setLastName("Last");
        read.setClientPriorityStatus(ClientPriorityStatus.DEFAULT);
        read.setINN("1233443252");
        Mockito.when(clientService.create(createDTO)).thenReturn(read);

        String resultJson = mvc.perform(post("/api/v1/client/")
                .content(objectMapper.writeValueAsString(createDTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ClientReadPOJO clientReadPOJO = objectMapper.readValue(resultJson, ClientReadPOJO.class);
        Assertions.assertThat(clientReadPOJO)
                .isEqualToComparingFieldByField(read);
    }

    @Test
    @WithMockUser("USER")
    public void testGetClientWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Client.class, wrongId);

        Mockito.when(clientService.getClient(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/client/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue(resultJson.contains(exception.getMessage()));
    }


    @Test
    @WithMockUser("ADMIN")
    public void testDeleteClient() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/client/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(clientService).delete(id);
    }

}