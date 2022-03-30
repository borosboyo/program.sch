package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
