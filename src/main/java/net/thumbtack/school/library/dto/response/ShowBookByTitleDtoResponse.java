package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.BookForUser;

import java.util.ArrayList;

public class ShowBookByTitleDtoResponse {
    private ArrayList<BookForUser> collectionBook;

    public ShowBookByTitleDtoResponse (ArrayList<BookForUser> collectionBook){
        setCollectionBook(collectionBook);
    }

    public void setCollectionBook(ArrayList<BookForUser> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public ArrayList<BookForUser> getCollectionBook() {
        return collectionBook;
    }
}
