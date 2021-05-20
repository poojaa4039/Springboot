package com.CodingBootcamp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingBootcamp.model.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Long>{

}
