package com.CodingBootcamp;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.controller.MeetingController;
import com.CodingBootcamp.model.Meeting;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class MeetingControllerTest {

	@Autowired
	MeetingController meetcon;
	
	@Test
	void getMeetings() {
		meetcon.getMeeting();
	}
	@Test
	void validInputTest() {
		Meeting m=new Meeting();
		m.setDate(LocalDate.of(2022, 5, 21));
		m.setEnd_time(LocalTime.of(18, 20));
		m.setMeeting_link("https://zoom");
		m.setStart_time(LocalTime.of(19, 20));
		meetcon.send(m);
	}
}
