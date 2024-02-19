package com.connect.bookStoreProject.Configs;

import com.connect.bookStoreProject.Entities.Book;
import com.connect.bookStoreProject.Entities.BookSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookSpecs implements Specification<Book> {
    private BookSearch search;

    // if sear

    public BookSpecs(BookSearch search) {
        super();
        this.search = search;
    }


    @Override
    // toPredicate = Dynamic where condition
    public Predicate toPredicate(Root<Book> book, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (search.getBookPrice() != null && !search.getBookPrice().isEmpty()) {
            predicates.add(cb.like(book.get("price"), search.getBookPrice()));
        }
        else  if (search.getBookName() != null && !search.getBookName().isEmpty()) {
            predicates.add(cb.like(book.get("bookName"), search.getBookName()));
        }
        else if (search.getBookCatigory() != null && !search.getBookCatigory().isEmpty()) {
            predicates.add(cb.like(book.get("catigory"), search.getBookCatigory()));
        }

        // and means add all above in a query >> and statement
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
