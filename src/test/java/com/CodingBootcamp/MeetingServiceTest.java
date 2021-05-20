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

import com.CodingBootcamp.model.Meeting;
import com.CodingBootcamp.repository.MeetingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class MeetingServiceTest {
@Autowired
private MeetingServiceImpl meetcon;
@Test
void validInputTest() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2022, 5, 21));
	m.setEnd_time(LocalTime.of(18, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(19, 20));
	meetcon.scheduleMeeting(m);
}
@Test
void ExpiredDateTest() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2020, 5, 11));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(19, 20));
	m.setEnd_time(LocalTime.of(20, 20));
	meetcon.scheduleMeeting(m);
}

@Test
void SameStartAndEndTime() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 8, 11));
	m.setEnd_time(LocalTime.of(18, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(18, 20));
	meetcon.scheduleMeeting(m);
}

@Test
void SameDate() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 5, 19));
	m.setEnd_time(LocalTime.of(18, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(18, 20));
	meetcon.scheduleMeeting(m);
}

@Test
void InvalidEndTime() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 8, 11));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(18, 20));
	m.setEnd_time(LocalTime.of(16, 20));
	meetcon.scheduleMeeting(m);
}

@Test
void InvalidInput1() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 5, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(11, 20));
	m.setEnd_time(LocalTime.of(14, 40));
	meetcon.scheduleMeeting(m);
}

@Test
void InvalidInput2() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 5, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(14, 20));
	m.setEnd_time(LocalTime.of(18, 30));
	meetcon.scheduleMeeting(m);
}

@Test
void InvalidInput3() {
	Meeting m=new Meeting();
	m.setDate(LocalDate.of(2021, 5, 20));
	m.setMeeting_link("https://zoom");
	m.setStart_time(LocalTime.of(14, 20));
	m.setEnd_time(LocalTime.of(19, 40));
	meetcon.scheduleMeeting(m);
}

	@Test
	void testGetMeetingDetails() {
		meetcon.getMeetingDetails();
	}
	
}
