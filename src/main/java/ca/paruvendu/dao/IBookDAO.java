package ca.paruvendu.dao;

import java.util.List;

import ca.paruvendu.domain.Book;

public interface IBookDAO {
	public List<Book> findAll();

	public Book findOne(Long id);

	public Book save(Book book);

	List<Book> blurrySearch(String title);

	public void delete(Long id);
	
	public List<Book> findByTitleContaining(String keyword);
}
