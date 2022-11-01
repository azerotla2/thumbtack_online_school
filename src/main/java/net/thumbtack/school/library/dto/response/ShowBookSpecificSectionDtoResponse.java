package net.thumbtack.school.library.dto.response;

import java.util.List;

public class ShowBookSpecificSectionDtoResponse {
    private List<BookDtoResponse> collectionBook;

    public ShowBookSpecificSectionDtoResponse(List<BookDtoResponse> collectionBook) {
        setCollectionBook(collectionBook);
    }

    public List<BookDtoResponse> getCollectionBook() {
        return collectionBook;
    }

    public void setCollectionBook(List<BookDtoResponse> collectionBook) {
        this.collectionBook = collectionBook;
    }
}
