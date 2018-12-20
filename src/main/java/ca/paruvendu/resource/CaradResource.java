package ca.paruvendu.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.paruvendu.domain.Carad;
import ca.paruvendu.domain.Search;
import ca.paruvendu.service.impl.CaradService;


@RestController
@RequestMapping("/carad")

public class CaradResource {

	private static final Logger logger = LoggerFactory.getLogger(CaradResource.class);

	@Autowired
	private CaradService caradService;
	
	

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CrossOrigin(origins="http://192.168.1.101")
	public Carad addCarad(@RequestBody Carad carad) {

		
		return caradService.save(carad);

	}

  
	@RequestMapping(value = "/add/image", method = RequestMethod.POST)
	@CrossOrigin(origins="http://192.168.1.101")
	public ResponseEntity upload(@RequestParam("id") String id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Carad carad = caradService.findById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			
			List<MultipartFile> multi = multipartRequest.  getFiles("uploads[]");
			
			int i=1;
			logger.info("create file");			
			File theDir = new File(new URI("file:///var/www/image/carad/"+id));
			Path path = Paths.get(theDir.toString());
			String fullpath=path.toUri().toString();
			logger.info("fullpath  "+fullpath);	
        	saveFilesToServer(multi,id);
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	  public void saveFilesToServer(List<MultipartFile> multipartFiles, String id) throws IOException {
		  	String directory = "file:///var/www/image/carad/"+id;
			int i=1;
		  	File file;
			try {
				file = new File(new URI(directory));
				file.mkdirs();
				Path path = Paths.get(file.toString());
				String fullpath=path.toUri().toString();
				logger.info("creation of folder "+ fullpath);	
				
				for (MultipartFile multipartFile : multipartFiles) {
					file = new File(new URI(directory+"/" + id+i+".png"));
					IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
					i++;
				}
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		  }
	  
	  @RequestMapping(value="caradList", method=RequestMethod.GET)
	  public List<Carad> getCaradLsit(){
		return   caradService.findAll();
		  
	  }
	  
	  @RequestMapping("/{id}")
	  public Carad getCaradById(@PathVariable("id")  String id){
		  ObjectMapper mapper = new ObjectMapper();
		  int numFiles=0;
		  File file;
		  String[] files;
		  //Convert object to JSON string
		  logger.info("Find by id--> "+ id);
		  Carad carad= caradService.findById(id);
		  String directory = "src/main/resources/static/image/carad/"+id;
		  
		  try {
			   file = new File(directory);
			    files = file.list();
			    numFiles = files.length;
			   logger.info("numFiles---> "+ numFiles);
			  } catch (NullPointerException e) {
			    System.out.println("File null !");
			  }
			   
					  
		    carad.setFileNumber(numFiles);
			String jsonInString;
			try {
				jsonInString = mapper.writeValueAsString(carad);
				System.out.println(jsonInString);
				 logger.info("jsonInString---> "+jsonInString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  logger.info("id---> "+id);
		  return carad;
	  }
	  
	  
	  @RequestMapping(value="/searchCarad/key", method=RequestMethod.POST)
	  List<Carad>  getCaradByKeyword(@RequestBody Search search){
		  logger.info("search element 1---> "+search.getElement1());
		  return caradService.findByKeyword(search);
	  }
	  

	

}
