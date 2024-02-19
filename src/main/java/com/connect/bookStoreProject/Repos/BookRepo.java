package com.connect.bookStoreProject.Repos;



import com.connect.bookStoreProject.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository <Book,Long> ,JpaSpecificationExecutor<Book> {


}
