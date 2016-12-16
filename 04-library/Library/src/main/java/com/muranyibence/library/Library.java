/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.library;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bence
 */
public class Library {

    private static Library instance;

    private Map<PrintedMedia, Member> lendings;
    private static Logger libraryLog = Logger.getLogger(Library.class.getName());

    public Library() {
        this.lendings = new HashMap<>();
        libraryLog = Logger.getLogger(Library.class.getName());
    }

    public static Library getInstance() {
        if (null == instance) {
            instance = new Library();
        }
        return instance;
    }

    public boolean isLent(PrintedMedia printedMedia) {
        return this.lendings.containsKey(printedMedia);
    }

    public void lendPrintedMedia(PrintedMedia printedMedia, Member member) {
        if (lendings.containsKey(printedMedia)) {
            throw new LibraryException(printedMedia.getTitle() + " is already lended by " + lendings.get(printedMedia).getName());
        } else {
            lendings.put(printedMedia, member);
            libraryLog.log(Level.FINE, printedMedia.getTitle() + " is lended by " + lendings.get(printedMedia).getName());
        }
    }

    public void returnPrintedMedia(PrintedMedia printedMedia) {
        if (lendings.containsKey(printedMedia)) {
            libraryLog.log(Level.FINE, printedMedia.getTitle() + " is returned by " + lendings.get(printedMedia).getName());
            lendings.remove(printedMedia);
        } else {
            throw new LibraryException(printedMedia.getTitle() + " is in the library, so you can't lend it");
        }
    }

    public static Logger getLibraryLog() {
        return libraryLog;
    }

}
