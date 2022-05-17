package hu.bme.aut.programsch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "filter", schema = "public")
@NoArgsConstructor
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
}
