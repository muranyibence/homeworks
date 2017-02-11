package com.muranyibence.movie.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Bence
 */
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "jnd_actor_movie",
            joinColumns = @JoinColumn(name = "movie_fk"),
            inverseJoinColumns = @JoinColumn(name = "actor_fk"))
    private List<Actor> actors;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST)
    private List<Trailer> trailers;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    public Movie() {
        //empty
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void addTrailer(Trailer trailer) {
        trailers.add(trailer);
    }

}
