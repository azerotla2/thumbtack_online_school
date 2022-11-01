package net.thumbtack.school.library.dto.response;

import java.util.HashSet;

public class BookDtoResponse {

    private int numberBook;
    private String title;
    private HashSet<String> authors;
    private HashSet<String> sections;

    public BookDtoResponse(int number, String title, HashSet<String> authors, HashSet<String> sections){
        setNumberBook(number);
        setTitle(title);
        setAuthor(authors);
        setSection(sections);
    }

    public void setNumberBook(int numberBook) {
        this.numberBook = numberBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(HashSet<String> authors) {
        this.authors = authors;
    }

    public void setSection(HashSet<String> sections) {
        this.sections = sections;
    }
}
