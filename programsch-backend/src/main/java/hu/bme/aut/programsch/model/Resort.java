package hu.bme.aut.programsch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "resort")
@Getter
@Setter
@NoArgsConstructor
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
