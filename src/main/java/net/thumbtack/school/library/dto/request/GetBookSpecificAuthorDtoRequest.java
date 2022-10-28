package net.thumbtack.school.library.dto.request;


public class GetBookSpecificAuthorDtoRequest {
    private String authors;

    public GetBookSpecificAuthorDtoRequest(String authors){
        setAuthors(authors);
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }
}
