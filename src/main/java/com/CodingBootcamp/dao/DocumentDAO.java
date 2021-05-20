package com.CodingBootcamp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CodingBootcamp.model.Document;

@Repository
public interface DocumentDAO extends JpaRepository<Document, Long> {
	
	@Query("SELECT new Document(d.id,d.name,d.size)FROM Document d ")
	List<Document> findAll();

}