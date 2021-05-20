package com.CodingBootcamp.service;

import java.util.List;

import com.CodingBootcamp.model.Feedback;

public interface FeedbackService {

	List<Feedback> showFeedback();

	void storeFeedback(Feedback feedback);

}
