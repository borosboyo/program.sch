package hu.bme.aut.programsch.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "filter", schema = "public")
public class Filter {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String userId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> filteredCircles;

    public Filter(String uid) {
        userId = uid;
    }

    public Filter() {

    }
}
