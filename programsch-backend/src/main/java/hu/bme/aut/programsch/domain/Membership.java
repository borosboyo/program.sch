package hu.bme.aut.programsch.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "membership", schema = "public")
public final class Membership implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String circleName;

    @Column
    private String role;

    @Column
    private String appUserUid;

    public Membership(String appUserUid, String name, String status) {
        this.appUserUid = appUserUid;
        this.circleName = name;
        this.role = status;
    }
}
