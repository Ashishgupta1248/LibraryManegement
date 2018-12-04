package com.example.ashish.login_form.Books.Books_Model;

public class getbooksModel {
    private String bookNAme;
    private  String TeacherNAme;
    private int bookId;
    private int studentId;
    private int TeacherId;
    private String DATE;

    public int getRowID() {
        return rowID;
    }

    int rowID;

    public String getDATE() {
        return DATE;
    }

    public String getBookNAme() {
        return bookNAme;
    }

    public String getTeacherNAme() {
        return TeacherNAme;
    }

    public int getBookId() {
        return bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getTeacherId() {
        return TeacherId;
    }

    public getbooksModel(int rowID,String bookNAme, String teacherNAme, int bookId, int studentId, int teacherId, String DATE) {
        this.bookNAme = bookNAme;
        this.TeacherNAme = teacherNAme;
        this.bookId = bookId;
        this.studentId = studentId;
        this.TeacherId = teacherId;
        this.DATE = DATE;
        this.rowID = rowID;
    }
}
