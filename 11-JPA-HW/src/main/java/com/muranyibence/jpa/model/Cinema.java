package com.muranyibence.jpa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author Bence
 */
@Entity
@IdClass(CinemaID.class)
public class Cinema implements Serializable {

    @Id
    private String franchise;
    @Id
    private String name;

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
