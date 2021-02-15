package ua.anc.test.application.pojo.client;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.anc.test.application.entity.Family;
import ua.anc.test.application.enums.ClientPriorityStatus;
import ua.anc.test.application.enums.EducationStatus;
import ua.anc.test.application.enums.MaritalStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class ClientReadPOJO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String iNN;

    private LocalDate dateOfBirth;

    private EducationStatus education;

    private ClientPriorityStatus clientPriorityStatus;

    private MaritalStatus maritalStatus;

    private Set<UUID> families;


}
