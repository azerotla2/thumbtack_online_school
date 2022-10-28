package net.thumbtack.school.library.dto.request;


public class AddBookDtoRequest {
    private String title;
    private String authors;
    private String section;

    public AddBookDtoRequest (String title, String authors, String section){
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
