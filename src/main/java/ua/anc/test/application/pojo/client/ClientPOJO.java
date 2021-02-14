package ua.anc.test.application.pojo.client;


import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.enums.ClientPriorityStatus;
import ua.anc.test.application.enums.MaritalStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
public class ClientPOJO {

    private String firstName;

    private String lastName;

    private String patronymic;

    private String iNN;

    private LocalDate dateOfBirth;

    private String education;

    private ClientPriorityStatus clientPriorityStatus;

    private MaritalStatus maritalStatus;

    private Set<UUID> families;


    //    private UUID familyId;

}
