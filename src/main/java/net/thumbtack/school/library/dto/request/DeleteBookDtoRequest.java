package net.thumbtack.school.library.dto.request;

public class DeleteBookDtoRequest {
    private String idBook;

    public DeleteBookDtoRequest(String idBook){
        setIdBook(idBook);
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdBook() {
        return idBook;
    }
}
