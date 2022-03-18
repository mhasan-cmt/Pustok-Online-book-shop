package com.teamphoenix.pustok_onlinebookshop.entity;

public class Book {
    String book_id,
            price,
            book_name,
            book_image,
            language,
            description,
            writer_id,
            page_number,
            category_id,
            publisher_id,
            book_type,
    writer_name
    ;

    public Book(String book_id,
                String price,
                String book_name,
                String book_image,
                String language,
                String description,
                String writer_id,
                String page_number,
                String category_id,
                String publisher_id,
                String writer_name,
                String book_type) {
        this.book_id = book_id;
        this.price = price;
        this.book_name = book_name;
        this.book_image = book_image;
        this.language = language;
        this.description = description;
        this.writer_id = writer_id;
        this.page_number = page_number;
        this.category_id = category_id;
        this.publisher_id = publisher_id;
        this.book_type = book_type;
        this.writer_name=writer_name;
    }

    public Book(String price,
                String book_name,
                String book_image,
                String language,
                String description,
                String writer_id,
                String page_number,
                String category_id,
                String publisher_id,
                String book_type,
                String writer_name) {
        this.price = price;
        this.book_name = book_name;
        this.book_image = book_image;
        this.language = language;
        this.description = description;
        this.writer_id = writer_id;
        this.page_number = page_number;
        this.category_id = category_id;
        this.publisher_id = publisher_id;
        this.book_type = book_type;
        this.writer_name = writer_name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + book_id + '\'' +
                ", price='" + price + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_image='" + book_image + '\'' +
                ", language='" + language + '\'' +
                ", description='" + description + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", page_number='" + page_number + '\'' +
                ", category_id='" + category_id + '\'' +
                ", publisher_id='" + publisher_id + '\'' +
                ", book_type='" + book_type + '\'' +
                ", writer_name='" + writer_name + '\'' +
                '}';
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Book() {
    }

    public String getWriter_name() {
        return writer_name;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getPrice() {
        return price;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_image() {
        return book_image;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public String getPage_number() {
        return page_number;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public String getBook_type() {
        return book_type;
    }
}
