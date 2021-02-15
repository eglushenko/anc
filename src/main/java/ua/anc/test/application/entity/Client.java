package ua.anc.test.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.anc.test.application.enums.ClientPriorityStatus;
import ua.anc.test.application.enums.EducationStatus;
import ua.anc.test.application.enums.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String iNN;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private EducationStatus education;

    @Enumerated(EnumType.STRING)
    private ClientPriorityStatus clientPriorityStatus;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "client_families",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "family_id")
    )
//@ManyToMany(mappedBy = "clients",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Family> families = new HashSet<>();

}
