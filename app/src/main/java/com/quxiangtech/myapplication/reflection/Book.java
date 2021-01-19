package com.quxiangtech.myapplication.reflection;

public class Book {
    private static final String TAG = "Book";

    private String name;
    private String author;

    public Book() {
    }

    private Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String declaredMethod(int index) {
        String s = null;

        switch (index) {
            case 0:
                s = "I am declaredMethod 1 !";
                break;
            case 1:
                s = "I am declaredMethod 2 !";
                break;
            default:
                s = "I am declaredMethod 3 !";
        }

        return s;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
