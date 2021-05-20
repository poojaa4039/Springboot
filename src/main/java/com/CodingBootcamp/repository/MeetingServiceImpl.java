package com.CodingBootcamp.repository;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingBootcamp.customException.InvalidMeetingDateException;
import com.CodingBootcamp.dao.MeetingDAO;
import com.CodingBootcamp.model.Meeting;
import com.CodingBootcamp.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private MeetingDAO meetingDAO;
	private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

	@Override
	public String scheduleMeeting(Meeting m) {
		try {

			if (m.getDate().isBefore(LocalDate.now())) {
				logger.error("Event Scheduled date is expired");
				throw new InvalidMeetingDateException("Event schedule date is expired!!!");

			} else if (m.getStarttime().compareTo(m.getEndtime()) == 0)
				return "Start time and End time cannot be same!!";
			else if (m.getEndtime().compareTo(m.getStarttime()) < 0)
				return "Invalid Event End Time!!!";
			List<Meeting> meetings = meetingDAO.findAll();
			
			//first check whether any event on same date
			int datecheck = 0, timecheck = 0;
			
			for (Meeting ms : meetings) 
				if (m.getDate().compareTo(ms.getDate()) == 0) 
					datecheck++;
			//datecheck!=0 defines that there are some meetings on same date
			//so now check whether time slot available or not
			if(datecheck!=0) {
				for (Meeting ms : meetings) {
					if (m.getDate().compareTo(ms.getDate()) == 0) {

						boolean beforeSlot = (m.getStarttime().compareTo(ms.getStarttime()) < 0)
								&& (m.getEndtime().compareTo(ms.getStarttime()) < 0);
						boolean afterSlot = (m.getStarttime().compareTo(ms.getEndtime()) > 0)
								&& (m.getEndtime().compareTo(ms.getEndtime()) > 0);
						System.out.println("coming after slot- " + afterSlot + "coimg before slot " + beforeSlot);
						if (!beforeSlot && !afterSlot)
							timecheck++;
					} 
				}
				
				
				if (timecheck == 0) {
					meetingDAO.save(m);
					logger.info("Event Scheduled");
					return "Event Scheduled Successflly!!!";
				} else
					return "Event slot not available!!!";
			}
			
			
			else {
				meetingDAO.save(m);
				logger.info("Event Scheduled");
				return "Event Scheduled Successflly!!!";
			}
		

		} catch (InvalidMeetingDateException e) {
			return e.getMessage();
		}

	}

	@Override
	public List<Meeting> getMeetingDetails() {
		logger.info("Meeting details retrieved");
		return meetingDAO.findAll();
	}

}
