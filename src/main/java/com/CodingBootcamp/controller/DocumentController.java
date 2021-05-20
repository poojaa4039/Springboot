package com.CodingBootcamp.controller;
import java.io.IOException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.CodingBootcamp.dao.DocumentDAO;
import com.CodingBootcamp.model.Document;

@CrossOrigin(origins="http://localhost:3000",exposedHeaders = {"Content-Disposition"})
@RestController
@RequestMapping("/")
public class DocumentController{


	@Autowired
	private DocumentDAO repo;
	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);




	@PostMapping("/upload")
	public String uploadFile(@RequestParam("document") MultipartFile multipartfile) throws IOException {

		logger.info("File Uploaded");

		System.out.println(multipartfile);
		String fileName=StringUtils.cleanPath(multipartfile.getOriginalFilename());  
System.out.println("file "+fileName);
		Document document=new Document();
		document.setName(fileName);
		document.setContent(multipartfile.getBytes());
		document.setSize(multipartfile.getSize());
		repo.save(document);
		return "redirect:/";
	}

	@GetMapping("/download")
	public ResponseEntity<byte[]> getRandomFile(@Param("id") Long id) {
		
		logger.info("File Downloaded");

		long amountOfFiles = repo.count();
		System.out.println(amountOfFiles);


		if (amountOfFiles == 0) {
			return ResponseEntity.ok(new byte[0]);
		}
		List<Document> list= repo.findAll();
		id=list.get(list.size()-1).getId();
		Document fileEntity = repo.findById(id).get();
		System.out.println(fileEntity.getName());
		HttpHeaders header = new HttpHeaders();

		header.setContentLength(fileEntity.getContent().length);
		header.set("Content-Disposition", "attachment; filename=" + fileEntity.getName());

		return new ResponseEntity<>(fileEntity.getContent(), header, HttpStatus.OK);
	}
}

