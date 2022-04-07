package hu.bme.aut.programsch.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "circles")
@NoArgsConstructor
public final class Circle implements Serializable {
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
    //@Column
    //@OneToMany(mappedBy = "circle", fetch = FetchType.LAZY, orphanRemoval = true)
    //@JsonManagedReference
    //private List<Event> openings;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Long getVirGroupId() {
        return virGroupId;
    }

    public void setVirGroupId(Long virGroupId) {
        this.virGroupId = virGroupId;
    }


    public Resort getResort() {
        return resort;
    }

    public void setResort(Resort resort) {
        this.resort = resort;
    }

}
