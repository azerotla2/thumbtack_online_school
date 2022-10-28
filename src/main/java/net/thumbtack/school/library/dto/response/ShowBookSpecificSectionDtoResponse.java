package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.BookForUser;

import java.util.Set;

public class ShowBookSpecificSectionDtoResponse {
    private Set<BookForUser> collectionBook;

    public ShowBookSpecificSectionDtoResponse(Set<BookForUser> collectionBook) {
        setCollectionBook(collectionBook);
    }

    public Set<BookForUser> getCollectionBook() {
        return collectionBook;
    }

    public void setCollectionBook(Set<BookForUser> collectionBook) {
        this.collectionBook = collectionBook;
    }
}
