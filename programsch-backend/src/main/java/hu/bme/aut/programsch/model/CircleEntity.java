package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "circles")
public final class CircleEntity implements Serializable {
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
    @Column
    @OneToMany(mappedBy = "circle", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<CircleMemberEntity> members;

    @Column
    @OneToMany(mappedBy = "circle", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<OpeningEntity> openings;
    @Column
    private String facebookUrl;
    @Column
    private String websiteUrl;
    @Column
    private Long virGroupId;

    @ManyToOne
    private Resort resort;

    @Column
    @ManyToMany
    private List<AppUserEntity> filteredBy;

    public CircleEntity(String displayName, Resort resort) {
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

    public List<CircleMemberEntity> getMembers() {
        return members;
    }

    public void setMembers(List<CircleMemberEntity> members) {
        this.members = members;
    }

    public List<OpeningEntity> getOpenings() {
        return openings;
    }

    public void setOpenings(List<OpeningEntity> openings) {
        this.openings = openings;
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
