package com.ureka.myspring.expend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendRepository extends JpaRepository<Expend, Integer> {
	@Query("SELECT SUM(e.price) FROM Expend e")
    Integer sumPrice();
}