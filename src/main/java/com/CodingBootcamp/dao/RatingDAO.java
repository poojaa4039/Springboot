package com.CodingBootcamp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingBootcamp.model.Rating;



public interface RatingDAO extends JpaRepository<Rating, Long> {

}
