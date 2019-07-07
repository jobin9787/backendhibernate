package ca.paruvendu.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import ca.paruvendu.domain.GiveFree;
import ca.paruvendu.service.IGiveFreeService;

@RestController
@RequestMapping("givefree")
public class GiveFreeResource {
	
	
	private static final Logger logger = LoggerFactory.getLogger(GiveFreeResource.class);
	
	@Autowired
	IGiveFreeService giveFreeservcie;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public GiveFree addFreeAd(@RequestBody GiveFree giveFree){
		logger.info("Add for free all");
		return giveFreeservcie.save(giveFree);
		 
	}
	
	
	@RequestMapping(value = "/add/image", method = RequestMethod.POST)
	public ResponseEntity upload(@RequestParam("id") String id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			GiveFree giveFree = giveFreeservcie.findById(id);
			   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			    Iterator<String> it = multipartRequest.getFileNames();
			     List<MultipartFile> multi = multipartRequest.  getFiles("uploads[]");
			      logger.info("create file");			
			     File theDir = new File("src/main/resources/static/image/carad/"+id);
			     Path path = Paths.get(theDir.toString());
			     saveFilesToServer(multi,id);
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
			} 
		     catch (Exception e) {
			    e.printStackTrace();
			   return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public void saveFilesToServer(List<MultipartFile> multipartFiles, String id) throws IOException {
		String directory = "src/main/resources/static/image/givefree/" + id;
		int i = 1;
		File file;
		try {
			file = new File(directory);
			file.mkdirs();
			Path path = Paths.get(file.toString());

			logger.info("creation of folder " + path);

			for (MultipartFile multipartFile : multipartFiles) {
				file = new File(directory + "/" + id + i + ".png");
				IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/freeList", method=RequestMethod.GET)
	public List<GiveFree> getFreeList(){
		return giveFreeservcie.findAll();
	}

}
