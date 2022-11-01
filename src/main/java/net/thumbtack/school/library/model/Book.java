package net.thumbtack.school.library.model;

import java.util.Date;
import java.util.HashSet;

public class Book {

    private int idBook;
    private String title;
    private HashSet<String> authors;
    private HashSet<String> section;
    private Date returnDate;
    private boolean reserved;
    private Employee holder;
    private Employee reader;


    public Book(int idBook, String title, HashSet<String> authors, HashSet<String> section, Date returnDate, boolean reserved, Employee holder, Employee reader){
        setIdBook(idBook);
        setTitle(title);
        setAuthors(authors);
        setSections(section);
        setReturnDate(returnDate);
        setReserved(reserved);
        setHolder(holder);
        setReader(reader);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthors(HashSet<String> authors) {
        this.authors = authors;
    }

    public HashSet<String> getAuthors() {
        return authors;
    }

    public void setSections(HashSet<String> section) {
        this.section = section;
    }

    public HashSet<String> getSections() {
        return section;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean getReserved() {
        return reserved;
    }

    public void setHolder(Employee holder) {
        this.holder = holder;
    }

    public Employee getHolder() {
        return holder;
    }

    public void setReader(Employee reader) {
        this.reader = reader;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBook() {
        return idBook;
    }

}
