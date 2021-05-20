package com.CodingBootcamp.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.CodingBootcamp.model.Meeting;


public interface MeetingDAO extends JpaRepository<Meeting, Long> {
   // @Query("Select * from meeting  where date >= :d")
    //List<Meeting> findByDate(@Param("date")LocalDate d);
}
