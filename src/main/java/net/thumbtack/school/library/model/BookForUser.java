package net.thumbtack.school.library.model;

import java.util.HashSet;

// REVU не нужен. В Book все есть, а в Employee можно добавить 2 List<Book> - список книг, которые он передал и которые взял
public class BookForUser {

    private String numberBook;
    private String title;
    private HashSet<String> authors;
    private HashSet<String> sections;

    public BookForUser(String number, String title, HashSet<String> authors, HashSet<String> sections){
        setNumberBook(number);
        setTitle(title);
        setAuthor(authors);
        setSection(sections);
    }

    public void setNumberBook(String numberBook) {
        this.numberBook = numberBook;
    }

    public String getNumberBook() {
        return numberBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(HashSet<String> authors) {
        this.authors = authors;
    }

    public HashSet<String> getAuthors() {
        return authors;
    }

    public void setSection(HashSet<String> sections) {
        this.sections = sections;
    }

    public HashSet<String> getSections() {
        return sections;
    }
}
