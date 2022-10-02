package hu.bme.aut.programsch.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "event", schema = "public")
@Data
public final class Event implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @JsonBackReference
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Circle circle;

    @Column
    private LocalDateTime start;
    @Column(name = "`end`")
    private LocalDateTime end;
    @Column
    private String place;
    @Column(length = 1000)
    private String facebookUrl;
    @Column(length = 1000)
    private String poster;
    @Column
    private String tldr;
    @Column(length = 1000)
    private String description;
}