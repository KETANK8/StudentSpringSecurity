package com.studentswagger.repository;

import java.util.List;
import java.util.Optional;

import com.studentswagger.model.Students;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer> {

	List<Students> findByStDomain(String stDomain);

	List<Students> findByStAge(int stAge);

	Students findByStId(int stId);

	Optional<Students> findByStUserName(String UserName);

}
