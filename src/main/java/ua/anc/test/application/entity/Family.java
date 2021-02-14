package ua.anc.test.application.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

//    @ManyToMany(mappedBy = "families", cascade = CascadeType.ALL)
//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//@JoinTable(name = "client_families",
//        joinColumns = @JoinColumn(name = "family_id"),
//        inverseJoinColumns = @JoinColumn(name = "client_id")
//)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "client_families",
            joinColumns = @JoinColumn(name = "family_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Client> clients = new HashSet<>();


}
