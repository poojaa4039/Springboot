package com.CodingBootcamp;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.CodingBootcamp.controller.DocumentController;
import com.CodingBootcamp.model.Document;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class DocumentControllerTest {
	@Autowired
	private DocumentController dc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@SuppressWarnings("deprecation")
	@Test
	void testuploadFile() throws Exception {

		MockMultipartFile mockMultipartFile = new MockMultipartFile("document", "project.txt", "text/plain",
				"This is a Test".getBytes());
		dc.uploadFile(mockMultipartFile);
		//MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		//mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file(mockMultipartFile));
	}

	@Test
	void testGetRandomFile() {
		Document d = new Document();
		// d.setContent(d.getContent());
		// d.setId(d.getId());
		// d.setName(d.getName());
		// d.setSize(d.getSize());

		dc.getRandomFile(d.getId());
	}

}
