package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.BookForUser;

import java.util.ArrayList;

public class ShowAllBookDtoResponse {
    private ArrayList<BookForUser> collectionBook;

    public ShowAllBookDtoResponse (ArrayList<BookForUser> allBooksCollection){
        setCollectionBook(allBooksCollection);
    }

    public void setCollectionBook(ArrayList<BookForUser> collectionBook) {
        this.collectionBook = collectionBook;
    }

    public ArrayList<BookForUser> getCollectionBook() {
        return collectionBook;
    }
}
