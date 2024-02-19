package com.connect.bookStoreProject.Controllers;


import com.connect.bookStoreProject.Entities.Book;
import com.connect.bookStoreProject.Entities.BookSearch;
import com.connect.bookStoreProject.Repos.BookRepo;
import com.connect.bookStoreProject.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/connect")
public class Controller {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepo bookRepo;


    @GetMapping("/allBooks")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAllBooks(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveAllBooks());
    }


    @PostMapping("/spec")
    public ResponseEntity<?> findByAutherSpec(@RequestBody BookSearch searchWord) {
        return ResponseEntity.ok(bookService.findBySpecs(searchWord));
    }

    Page<Book> bookList;


    @PostMapping("/page")
    public Page<Book> findByPagAutherSpec(@RequestBody BookSearch searchWord, Pageable pageable) {
        return  bookService.findPageBySpecs(searchWord,pageable);
    }


    @PostMapping("/file")
    public File generateFile(@RequestBody BookSearch searchWord, Pageable pageable) throws IOException {
//    return bookService.createNewExcel(searchWord,pageable);
    return null;
    }

    @PostMapping("/print")
    public void printObject(@RequestBody BookSearch searchWord, Pageable pageable) throws IOException {
        bookService.printObjects(searchWord, pageable);
    }


    @GetMapping("/do")
    public void geneFile() throws IOException {
        bookService.createFile();
    }



    @PostMapping("/genExcel")
    public ResponseEntity<?> findAllByEmpSpec(@RequestBody BookSearch bookSearch, Pageable pageable) throws IOException {
        ByteArrayOutputStream excelFile = bookService.createNewExcel(bookSearch, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Book.csv"); // Set the file name

        return new ResponseEntity<>(excelFile.toByteArray(), headers, 200);
    }
}


//    @GetMapping("/export/excel")
//    public ResponseEntity<byte[]> exportBooksToExcel(BookSearch searchWord, Pageable pageable) {
//        try {
//            ByteArrayOutputStream excelBytes = bookService.createNewExcel(searchWord, pageable);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", "books.xlsx");
//
//            return new ResponseEntity<>(excelBytes.toByteArray(), headers, HttpStatus.OK);
//        } catch (IOException e) {
//            // Handle the exception appropriately
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }