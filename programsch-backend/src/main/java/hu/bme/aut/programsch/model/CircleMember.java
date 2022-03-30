package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "circleMembers")
public final class CircleMember implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Circle circle;

    @Column
    private String name;

    @Column(name = "`rank`")
    private String rank;

}
