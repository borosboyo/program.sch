package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "openings")
public final class OpeningEntity implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long dateStart;
    @Column
    private long dateEnd;
    @Column
    private String prUrl;
    @Column
    private String eventDescription;
    @Column
    private String feeling;
    @Column
    private long orderStart;
    @Column
    private long orderEnd;
    @JsonBackReference
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private CircleEntity circle;
}