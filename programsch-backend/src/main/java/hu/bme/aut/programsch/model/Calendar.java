package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calendar")
@Data
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @OneToMany(mappedBy = "calendar", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Day> days = new ArrayList<>();

}
