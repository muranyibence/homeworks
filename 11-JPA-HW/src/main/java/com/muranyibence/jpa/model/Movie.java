package com.muranyibence.jpa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Bence
 */
@Entity
public class Movie implements Serializable {

    @Id
    private Long id;
    private String title;
    private String studio;
    @ManyToMany(mappedBy = "filmography")
    private List<Person> castMembers;

    public Movie() {
        //empty
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<Person> getCastMembers() {
        return castMembers;
    }

    public void setCastMembers(List<Person> castMembers) {
        this.castMembers = castMembers;
    }

}
