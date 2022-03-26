package hu.bme.aut.programsch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resort")
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
    @OneToMany(mappedBy = "resort")
    private List<CircleEntity> circleEntities;

    public Resort(String name) {
        this.name = name;
    }
}
