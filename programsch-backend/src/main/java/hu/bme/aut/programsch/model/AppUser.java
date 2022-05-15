package hu.bme.aut.programsch.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appusers", schema = "public")
@Proxy(lazy = false)
@NoArgsConstructor
public final class AppUser {
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
    public List<String> permissions;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

}
