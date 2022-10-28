package net.thumbtack.school.library.dto.request;


public class GetBookSpecificSectionDtoRequest {
    private String sections;

    public GetBookSpecificSectionDtoRequest (String sections){
        setSections(sections);
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getSections() {
        return sections;
    }
}
