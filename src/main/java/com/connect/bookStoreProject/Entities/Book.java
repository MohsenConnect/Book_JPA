package com.connect.bookStoreProject.Entities;


import jakarta.persistence.*;
import jakarta.servlet.http.PushBuilder;
import lombok.Data;


@Table
@Entity
@Data

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bookId;


    @Column
    public String bookName;

    @Column
    public String catigory;

    @Column
    public String price;

    @Column
    public Long size;


}
