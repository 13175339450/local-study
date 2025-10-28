package com.hxl.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 书籍实体类
 */
@Profile("test")
@Component
public class Book {
    // 私有属性
    private String isbn;      // 书号
    private String title;     // 书名
    private String author;    // 作者
    private double price;     // 价格

    // 无参构造
    public Book() {
    }

    // 全参构造
    public Book(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // getter和setter
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString方法
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}