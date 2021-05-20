package com.CodingBootcamp.model;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 @JsonFormat(pattern="yyyy-MM-dd")
	    private LocalDate date;
	 
	 @JsonFormat(pattern="HH:mm")
	 private LocalTime   start_time;
	 
	 @JsonFormat(pattern="HH:mm")
	private LocalTime end_time;
	private String meeting_link;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	/*public String  getStart_time() {
		//hh:mm or hh:mm:ss will denote the digits, a- to denote am or pm
		 DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm a");
		return this.start_time.format(pattern);
	}*/

	public void setStart_time(LocalTime  start_time) {
		this.start_time = start_time;
	}

	
	public LocalTime  getStarttime() {
				return this.start_time;
	}

	public LocalTime getEndtime() {
		return this.end_time;
	}
	
	
	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}

	public String getMeeting_link() {
		return meeting_link;
	}

	public void setMeeting_link(String meeting_link) {
		this.meeting_link = meeting_link;
	}

	public Meeting(long id, LocalDate date, LocalTime  start_time, LocalTime end_time, String meeting_link) {
		super();
		this.id = id;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.meeting_link = meeting_link;
	}

	public Meeting() {
	}

}
