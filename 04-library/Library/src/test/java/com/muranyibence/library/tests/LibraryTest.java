package com.muranyibence.library.tests;


import com.muranyibence.library.Book;
import com.muranyibence.library.Journal;
import com.muranyibence.library.Library;
import com.muranyibence.library.LibraryException;
import com.muranyibence.library.Member;
import com.muranyibence.library.Person;
import java.sql.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bence
 */
public class LibraryTest {

    Library library;

    Member member1;
    Member member2;
    Book book1;
    Book book2;
    Book book3;
    Book book4;
    Journal journal1;
    Journal journal2;
    Journal journal3;
    Journal journal4;

    public LibraryTest() {
        library = Library.getInstance();
    }

    @Before
    public void setUp() {
        member1 = new Member(1, "member1", new Date(1990, 8, 10), Person.Gender.MALE);
        member2 = new Member(2, "member2", new Date(1970, 8, 10), Person.Gender.FEMALE);

        book1 = new Book(new Person("Author1", new Date(1932, 11, 21), Person.Gender.MALE), "Book 1", 1232323221);

        book2 = new Book(new Person("Author2", new Date(1962, 11, 21), Person.Gender.MALE), "Book 2", 1232357486);
        book3 = new Book(new Person("Author3", new Date(1912, 11, 21), Person.Gender.FEMALE), "Book 3", 1232357486);
             book4 = new Book(new Person("Author4", new Date(1955, 1, 1), Person.Gender.MALE), "Book 3", 1232857486);

        journal1 = new Journal(new Date(1999, 11, 21), "Journal1", 42141541);
        journal2 = new Journal(new Date(1979, 11, 21), "Journal2", 42161541);

        journal3 = new Journal(new Date(1959, 3, 24), "Journal3", 42141151);
        journal4 = new Journal(new Date(1979, 11, 27), "Journal4", 42166743);

    }

    @Test
    public void testIsLent() {
        library.lendPrintedMedia(book1, member1);
        assertTrue(library.isLent(book1));
        assertFalse(library.isLent(book2));

    }

    @Test
    public void testLendBook() {

        assertEquals(false, library.isLent(book1));
        library.lendPrintedMedia(book1, member1);
        assertEquals(true, library.isLent(book1));
    }

    @Test
    public void testlendJournal() {

        assertEquals(false, library.isLent(journal1));
        library.lendPrintedMedia(journal1, member1);
        assertEquals(true, library.isLent(journal1));
    }

    @Test(expected = LibraryException.class)
    public void testBookAlreadyLended() {

        library.lendPrintedMedia(book1, member1);
        library.lendPrintedMedia(book1, member2);
    }

    @Test(expected = LibraryException.class)
    public void testJournalAlreadyLended() {

        library.lendPrintedMedia(journal2, member1);
        library.lendPrintedMedia(journal2, member2);
    }

    @Test(expected = LibraryException.class)
    public void testReturnBookWithoutLendBefore() {
        library.returnPrintedMedia(book3);
    }

    @Test(expected = LibraryException.class)
    public void testReturnJournalWithoutLendBefore() {
        library.returnPrintedMedia(journal3);
    }

    @Test
    public void testReturnedBookcanLendAgain() {
        library.lendPrintedMedia(book4, member1);
        library.returnPrintedMedia(book4);
        library.lendPrintedMedia(book4, member2);
    }

    @Test
    public void testReturnedJournalcanLendAgain() {
        library.lendPrintedMedia(journal4, member1);
        library.returnPrintedMedia(journal4);
        library.lendPrintedMedia(journal4, member2);
    }

}
