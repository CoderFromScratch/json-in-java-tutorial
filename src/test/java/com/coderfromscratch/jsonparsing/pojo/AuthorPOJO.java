package com.coderfromscratch.jsonparsing.pojo;

import java.util.List;

public class AuthorPOJO {

    private String authorName;
    private List<BookPOJO> books;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<BookPOJO> getBooks() {
        return books;
    }

    public void setBooks(List<BookPOJO> books) {
        this.books = books;
    }
}
