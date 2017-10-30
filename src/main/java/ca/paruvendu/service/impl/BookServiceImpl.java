package ca.paruvendu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.paruvendu.domain.Book;
import ca.paruvendu.repository.BookRepository;
import ca.paruvendu.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		List<Book> activeBookList = new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.isActive())
				activeBookList.add(book);
		}
		return activeBookList;
	}

	@Override
	public Book findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findOne(id);
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList = bookRepository.findByTitleContaining(keyword);
		List<Book> activeBookList = new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.isActive())
				activeBookList.add(book);
		}
		return activeBookList;

	}

	@Override
	public void removeBook(Long id) {
		bookRepository.delete(id);
	}

}
