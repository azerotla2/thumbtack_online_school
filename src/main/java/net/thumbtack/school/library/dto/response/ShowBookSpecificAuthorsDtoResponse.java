package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.BookForUser;

import java.util.Set;

public class ShowBookSpecificAuthorsDtoResponse {
    private Set<BookForUser> collectionBook;

    public ShowBookSpecificAuthorsDtoResponse(Set<BookForUser> collectionBook){
        setCollectionBook(collectionBook);
    }

    public void setCollectionBook(Set<BookForUser> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public Set<BookForUser> getCollectionBook() {
        return collectionBook;
    }
}
