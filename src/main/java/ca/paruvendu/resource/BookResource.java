package ca.paruvendu.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ca.paruvendu.domain.Book;
import ca.paruvendu.domain.Gift;
import ca.paruvendu.service.IBookService;
import ca.paruvendu.service.GiftService;

@RestController
@RequestMapping("/book")
public class BookResource {
	
	private static final Logger logger = LoggerFactory.getLogger(BookResource.class);
	@Autowired
	private IBookService bookService;
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Book addBookPost(@RequestBody Book book){
		return bookService.save(book);
		}
	
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity upload(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Book book = bookService.findById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Book book = bookService.findById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping("bookList")
    public List<Book> getBookLsit(){
		
		return bookService.findAll();
	}
	
	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id ){
		return bookService.findById(id);
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity remove(@RequestBody String id) throws IOException {
		bookService.removeBook(Long.parseLong(id));
		String fileName = id + ".png";

		Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

		return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}


@RequestMapping(value="update",method=RequestMethod.POST)
public Book update(@RequestBody Book book){
	return bookService.save(book);
}
}
