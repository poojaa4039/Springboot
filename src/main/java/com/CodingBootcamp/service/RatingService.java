package com.CodingBootcamp.service;

import java.util.List;

import com.CodingBootcamp.model.Rating;

public interface RatingService {

	void getRating(Rating rating);

	List<String> findAvgRating();

}
