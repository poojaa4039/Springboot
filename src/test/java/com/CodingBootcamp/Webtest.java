package com.CodingBootcamp;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.controller.WebSecurityConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class Webtest {
@Autowired
WebSecurityConfig webs;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConfigureHttpSecurity() {
		//webs.configure(web);
	}

}
