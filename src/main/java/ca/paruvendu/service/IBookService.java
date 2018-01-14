package ca.paruvendu.service;

import java.util.List;



import ca.paruvendu.domain.Book;


public interface IBookService {

	public List<Book> findAll();

	public Book findById(Long id);

	public Book save(Book book);

	List<Book> blurrySearch(String title);

	public void removeBook(Long id);
}
