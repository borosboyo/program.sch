package hu.bme.aut.programsch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resort", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "resort", fetch = FetchType.EAGER)
    private List<Circle> circles;

    public Resort(String name) {
        this.name = name;
    }
}
