package net.thumbtack.school.library.dto.response;

import java.util.List;

public class ShowBookByTitleDtoResponse {
    private List<BookDtoResponse> collectionBook;

    public ShowBookByTitleDtoResponse (List<BookDtoResponse> collectionBook){
        setCollectionBook(collectionBook);
    }

    public void setCollectionBook(List<BookDtoResponse> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public List<BookDtoResponse> getCollectionBook() {
        return collectionBook;
    }
}
