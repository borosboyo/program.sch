package hu.bme.aut.programsch.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appusers")
@Proxy(lazy = false)
@NoArgsConstructor
public final class AppUserEntity {
    @Id
    @Column(unique = true)
    public String uid;

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public String room;

    @Column
    public CardType cardType;
    
    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> permissions;


    public AppUserEntity(String uid, String name, String email, String room, CardType cardType, List<String> permissions) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.room = room;
        this.cardType = cardType;
        this.permissions = permissions;
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
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
