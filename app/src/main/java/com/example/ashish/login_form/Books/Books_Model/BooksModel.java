package com.example.ashish.login_form.Books.Books_Model;

public class BooksModel {
    String BookName;
    String BookAuthor;
    String Bookdition;
    String BookPublisher;
    Integer bookid;
    Integer no_of_books_available;


    public String getBookPublisher() {
        return BookPublisher;
    }


    public Integer getNo_of_books_available() {
        return no_of_books_available;
    }

    public String getBookName() {
        return BookName;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public String getBookdition() {
        return Bookdition;
    }

    public Integer getBookid() {
        return bookid;
    }

    public BooksModel(Integer bookid,String bookName, String bookAuthor, String bookdition ,String BookPublisher,Integer no_of_books_available) {
       this. BookName = bookName;
        this.  BookAuthor = bookAuthor;
        this. Bookdition = bookdition;
        this.bookid = bookid;
        this.no_of_books_available=no_of_books_available;
        this.BookPublisher=BookPublisher;
    }
}
