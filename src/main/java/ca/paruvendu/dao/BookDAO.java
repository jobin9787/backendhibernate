package ca.paruvendu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.paruvendu.domain.Book;

@Transactional
@Repository
public class BookDAO implements IBookDAO {

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> blurrySearch(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> findByTitleContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

 

}
