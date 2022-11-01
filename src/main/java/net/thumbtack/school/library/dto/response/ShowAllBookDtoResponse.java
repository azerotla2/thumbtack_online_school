package net.thumbtack.school.library.dto.response;

import java.util.List;

public class ShowAllBookDtoResponse {
    private List<BookDtoResponse> collectionBook;

    public ShowAllBookDtoResponse (List<BookDtoResponse> allBooksCollection){
        setCollectionBook(allBooksCollection);
    }

    public void setCollectionBook(List<BookDtoResponse> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public List<BookDtoResponse> getCollectionBook() {
        return collectionBook;
    }
}
