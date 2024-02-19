package com.connect.bookStoreProject.Services;


import com.connect.bookStoreProject.Configs.BookSpecs;
import com.connect.bookStoreProject.Entities.Book;
import com.connect.bookStoreProject.Entities.BookSearch;
import com.connect.bookStoreProject.Repos.BookRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;


    public List<Book> saveAllBooks() {

        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setBookName("bookato" + i + 3 + "tatoo");
            book.setSize((long) (i + 200));
            book.setPrice((i + 50 + "$"));
            bookList.add(book);

        }
        System.out.println(bookList);

        return bookRepo.saveAll(bookList);
    }

    public List<Book> findBySpecs(BookSearch searchWord) {
        BookSpecs bookSpecs = new BookSpecs(searchWord);
        return bookRepo.findAll(bookSpecs);

    }

    Pageable firstPageWithTwoElements = PageRequest.of(0, 3);


    public Page<Book> findPageBySpecs(BookSearch searchWord, Pageable pageable) {
        BookSpecs bookSpecs2 = new BookSpecs(searchWord);
        return bookRepo.findAll(bookSpecs2, pageable);
    }


    public void printObjects(BookSearch searchWord, Pageable pageable){
        Page<Book> books = findPageBySpecs(searchWord, pageable);
        for (Book book : books) {
            System.out.println(">>>>>>>>>>>");
            System.out.println(book.getBookId());
            System.out.println(book.getBookName());
            System.out.println(book.getCatigory());
            System.out.println(book.getPrice());
        }

    }


    public void createFile() throws IOException {
//        EXCEL File Consists of Workbooks >> Sheets >> ecach sheat has Rows & Cells
//        Create The Workebook
            XSSFWorkbook myWorkbook = new XSSFWorkbook();
//        Create The Sheet || with index or name
            XSSFSheet mySheet = myWorkbook.createSheet("Mohsen");
//        Create Row Index starts from 0
            mySheet.createRow(0);
            mySheet.getRow(0).createCell(0).setCellValue("Project");
            mySheet.getRow(0).createCell(1).setCellValue("Mohsen");


            File myFile = new File("A:\\Tasks\\Feb.2024\\dataSours5.xlsx");
//        FileOutputStream is used to write primitive values into a file
//        for character-oriented data, it is preferred to use FileWriter than FileOutputStream.
            FileOutputStream addFile = new FileOutputStream(myFile);
            myWorkbook.write(addFile);

            myWorkbook.close();

        }


    public ByteArrayOutputStream createNewExcel(BookSearch searchWord, Pageable pageable) throws IOException {
        Page<Book> books = findPageBySpecs(searchWord, pageable);

        // Create a new workbook
        XSSFWorkbook myWorkbook = new XSSFWorkbook();

        // Create a new sheet in the workbook
        XSSFSheet mySheet = myWorkbook.createSheet("Mohsen");

        // Iterate over the books and write them into the sheet
        int rowNum = 0;
        for (Book book : books) {
            Row row = mySheet.createRow(rowNum++);
            row.createCell(0).setCellValue(book.getBookId());
            row.createCell(1).setCellValue(book.getBookName());
            row.createCell(2).setCellValue(book.getSize());
            row.createCell(3).setCellValue(book.getPrice());
            row.createCell(4).setCellValue(book.getCatigory());
        }

        System.out.println("EEEEEEEEEEE");
        // Write the workbook content to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        myWorkbook.write(byteArrayOutputStream);
        myWorkbook.close();

        return byteArrayOutputStream;
    }
//    public ByteArrayOutputStream createNewExcel(BookSearch searchWord, Pageable pageable) throws IOException {
//        Page<Book> books = findPageBySpecs(searchWord, pageable);
//
////        EXCEL File Consists of Workbooks >> Sheets >> ecach sheat has Rows & Cells
////        Create The Workebook
//        XSSFWorkbook myWorkbook = new XSSFWorkbook();
////        Create The Sheet || with index or name
//        XSSFSheet mySheet = myWorkbook.createSheet("Mohsen");
////        Create Row Index starts from 0
//
//        int i = 0;
//        for (Book book : books) {
//            mySheet.createRow(i);
//            mySheet.getRow(i).createCell(0).setCellValue(book.getBookId());
//            mySheet.getRow(i).createCell(1).setCellValue(book.getBookName());
//            mySheet.getRow(i).createCell(2).setCellValue(book.getSize());
//            mySheet.getRow(i).createCell(3).setCellValue(book.getPrice());
//            mySheet.getRow(i).createCell(4).setCellValue(book.getCatigory());
//            i++;
//        }
//
////        File myFile = new File("A:\\Tasks\\Feb.2024\\bookPage1.xlsx");
//        File myFile = new File("bookPage1.xlsx");
//        FileOutputStream addFile = new FileOutputStream(myFile);
//
////        FileOutputStream is used to write primitive values into a file
////        for character-oriented data, it is preferred to use FileWriter than FileOutputStream.
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        myWorkbook.write(byteArrayOutputStream);
//        myWorkbook.close();
//        return byteArrayOutputStream;
//    }


//    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    myWorkbook.write(byteArrayOutputStream);
//    myWorkbook.close();
//
//    return byteArrayOutputStream;

}
