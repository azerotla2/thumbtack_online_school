package net.thumbtack.school.library.dto.request;

import java.util.HashSet;

public class AddBookDtoRequest {
    private String title;
    private HashSet<String> authors;
    private HashSet<String> section;

    public AddBookDtoRequest (String title, HashSet<String> authors, HashSet<String> section){
        setTitle(title);
        setAuthors(authors);
        setSection(section);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashSet<String> getAuthors() {
        return authors;
    }

    public void setAuthors(HashSet<String> authors) {
        this.authors = authors;
    }

    public HashSet<String> getSection() {
        return section;
    }

    public void setSection(HashSet<String> section) {
        this.section = section;
    }
}
