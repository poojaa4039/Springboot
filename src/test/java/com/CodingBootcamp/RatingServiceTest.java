package com.CodingBootcamp;



import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.model.Rating;
import com.CodingBootcamp.repository.RatingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class RatingServiceTest {
@Autowired
private RatingServiceImpl rsi;
	@Test
	void testGetRating() {
		Rating r=new Rating();
		r.setName("Aditya Ranjan");
		r.setRating(4.5);
		r.setName("Naveen Kumar");
		r.setRating(4.5);
		r.setName("Mansi Tripathi");
		r.setRating(4.5);
		r.setName("Prakhar");
		r.setRating(4.5);
		rsi.getRating(r);
		
	}

	@Test
	void testFindAvgRating() {
		rsi.findAvgRating();
	}

}
