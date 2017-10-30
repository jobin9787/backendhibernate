package ca.paruvendu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.paruvendu.domain.Book;

public interface BookRepository extends CrudRepository<Book,Long>{
public List<Book> findByTitleContaining(String keyword);
}
