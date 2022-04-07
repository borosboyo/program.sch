package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "day")
@Data
@NoArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate date;

    @Column
    @OneToMany(mappedBy = "day", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Event> events;

    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

    public Day(LocalDate date) {
        this.date = date;
    }

}
