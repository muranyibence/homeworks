/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.library;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Bence
 */
public class Journal extends PrintedMedia {
    Date release;

    public Journal(Date release, String title, int ISBN) {
        super(title, ISBN);
        this.release = release;
    }



    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.release);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Journal other = (Journal) obj;
        if (!Objects.equals(this.release, other.release)) {
            return false;
        }
        return true;
    }
    
    
}
