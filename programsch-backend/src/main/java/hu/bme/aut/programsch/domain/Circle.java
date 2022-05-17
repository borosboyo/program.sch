package hu.bme.aut.programsch.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "circles", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Circle implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String alias;
    @Column
    private String displayName;
    @Column
    private String description;
    @Column
    private int founded;

    //@Column
    //@OneToMany(mappedBy = "circle", fetch = FetchType.LAZY, orphanRemoval = true)
    //@JsonManagedReference
    //private List<CircleMember> members;

    @Column
    @OneToMany(mappedBy = "circle", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Event> events;

    @Column
    private String facebookUrl;
    @Column
    private String websiteUrl;
    @Column
    private Long virGroupId;

    @ManyToOne
    private Resort resort;

    public Circle(String displayName, Resort resort) {
        this.displayName = displayName;
        this.resort = resort;
    }

}
