package com.example.ashish.login_form.Student.StudentList.student_model;

public class model {
    private int id;
    private String name;
    private  String cls;
    private Integer roll_number;
    private  String contact_number;
    private String email_id;

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return email_id;
    }

    public String getCls() {
        return cls;
    }

    public Integer getRoll_number() {
        return roll_number;
    }

    public String getContact_number() {
        return contact_number;
    }

    public int getID(){return  id;}

    public model(Integer id,String name, Integer roll_number,  String cls,String email_id, String contact_number) {
        this.id = id;
        this.name = name;
        this.email_id = email_id;
        this.cls = cls;
        this.roll_number = roll_number;
        this.contact_number = contact_number;
    }
}
