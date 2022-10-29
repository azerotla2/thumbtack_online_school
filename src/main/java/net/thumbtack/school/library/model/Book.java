package net.thumbtack.school.library.model;

import java.util.HashSet;

public class Book {

    private String idBook;
    private String title;
    private HashSet<String> authors;
    private HashSet<String> section;
    private String returnDate; //может поставить сразу формат даты // REVU да, лучше так, тогда LocalDate
    private Boolean reserved;
    private Employee holder;
    private Employee reader;

//    private static boolean RESERVED = false;
//    private static boolean FREE = true;

    public Book(String idBook, String title, HashSet<String> authors, HashSet<String> section, String returnDate, Boolean reserved, Employee holder, Employee reader){
        setIdBook(idBook);
        setTitle(title);
        setAuthors(authors);
        setSection(section);
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

    public void setSection(HashSet<String> section) {
        this.section = section;
    }

    public HashSet<String> getSection() {
        return section;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean getReserved() {
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

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdBook() {
        return idBook;
    }

}
