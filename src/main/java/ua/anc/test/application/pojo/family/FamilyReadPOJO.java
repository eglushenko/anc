package ua.anc.test.application.pojo.family;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.anc.test.application.entity.Client;

import java.util.Set;
import java.util.UUID;

@Data
public class FamilyReadPOJO {

    private UUID id;

    private String name;

    private Set<UUID> clients;


}
