package com.CodingBootcamp.service;

import java.util.List;

import com.CodingBootcamp.model.Meeting;

public interface MeetingService {
 public String scheduleMeeting(Meeting m);
 public  List<Meeting>  getMeetingDetails();
}
