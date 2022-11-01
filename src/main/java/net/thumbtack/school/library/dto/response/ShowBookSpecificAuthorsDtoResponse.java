package net.thumbtack.school.library.dto.response;


import java.util.List;

public class ShowBookSpecificAuthorsDtoResponse {
    private List<BookDtoResponse> collectionBook;

    public ShowBookSpecificAuthorsDtoResponse(List<BookDtoResponse> collectionBook){
        setCollectionBook(collectionBook);
    }

    public void setCollectionBook(List<BookDtoResponse> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public List<BookDtoResponse> getCollectionBook() {
        return collectionBook;
    }
}
