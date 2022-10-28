package net.thumbtack.school.library.dto.request;

public class GetBookByTitleDtoRequest {
    private String title;

    public GetBookByTitleDtoRequest (String title){
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
