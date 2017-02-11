package com.muranyibence.movie.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Bence
 */
@Entity
public class Trailer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private VideoType videoType;
    private String title;
    private Date publicated;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movie_fk")
    private Movie movie;

    public Trailer() {
        //empty
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicated() {
        return publicated;
    }

    public void setPublicated(Date publicated) {
        this.publicated = publicated;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
