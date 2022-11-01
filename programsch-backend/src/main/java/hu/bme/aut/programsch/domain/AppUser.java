package hu.bme.aut.programsch.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "appusers", schema = "public")
@Proxy(lazy = false)
@NoArgsConstructor
@Getter
@Setter
public final class AppUser implements Serializable {
    @Id
    @Column(unique = true)
    public String uid;

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public String room;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> permissions;


    public AppUser(String uid, String name, String email, String room, List<String> permissions) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.room = room;
        this.permissions = permissions;
    }

    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AppUser(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }
}
