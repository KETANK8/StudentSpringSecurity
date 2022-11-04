package com.studentswagger.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.studentswagger.exception.GlobalException;
import com.studentswagger.model.Students;
import com.studentswagger.modeldto.StudentsDTO;
import com.studentswagger.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository stdRepo;

	// METHOD 1
	// ADD NEW STUDENT IN DB
	public String addStudent(StudentsDTO std) {

		Students _std = Students.build(std.getStId(), std.getStFirstName(), std.getStFirstName(), std.getStUserName(),
				std.getStPassWord(), std.getStDomain(), std.getStEmail(), std.getStContact(), "ROLE_STUDENT",
				std.getStAge(), true);
		stdRepo.save(_std);
		return "Student added @" + new Date();

	}

	// METHOD 2
	// RETRIVE LIST OF ALL STUDENT FROM DB
	public List<Students> getAllStudent() {

		return stdRepo.findAll();
	}

	// METHOD 3
	// RETRIVE A STUDENT FROM DB USING PRIMARY KEY
	public Optional<Students> getStudentById(int stId) {
		return stdRepo.findById(stId);
	}

	// METHOD 4
	// RETRIVE LIST OF STUDENT WITH SAME DOMAIN
	public List<Students> getStudentByDomain(String stDomain) throws GlobalException {

		if (stdRepo.findByStDomain(stDomain).isEmpty())
			throw new GlobalException("User Not Found with Domain : " + stDomain);
		else
			return stdRepo.findByStDomain(stDomain);
	}

	// METHOD 5
	// RETRIVE LIST OF STUDENT OF SAME AGE
	public List<Students> getStudentByAge(int stAge) throws GlobalException {

		if (stdRepo.findByStAge(stAge).isEmpty())
			throw new GlobalException("User Not Found of Age : " + stAge);
		else
			return stdRepo.findByStAge(stAge);

	}

	// METHOD 6
	// UPDATE THE DETAILS OF EXISTING STUDENT WITH NEW DATA IN DB
	public String updateStudent(StudentsDTO std) throws GlobalException {
		int id = std.getStId();
		if (stdRepo.findByStId(id) != null) {
			Students _std = Students.build(std.getStId(), std.getStFirstName(), std.getStFirstName(),
					std.getStUserName(), std.getStPassWord(), std.getStDomain(), std.getStEmail(), std.getStContact(),
					"ROLE_STUDENT", std.getStAge(), true);
			stdRepo.save(_std);
			return "Student updated @" + new Date();
		} else
			throw new GlobalException("User not Found");
	}

	// METHOD 7
	// DELETE A STUDENT FROM DATABASE USING PRIMARY KEY
	public String deleteStudent(int stId) throws GlobalException {
		Students _std = stdRepo.findByStId(stId);
		if (_std != null) {
			stdRepo.deleteById(stId);
			return "Student Deleted @" + new Date();
		} else
			throw new GlobalException("User not found with Id : " + stId);
	}

	// METHOD 8
	// RETRIVE SORTED LIST OF STUDENT BASED ON GIVEN ATTRIBUTE
	public List<Students> sortStudent(String fields) {
		return stdRepo.findAll(Sort.by(Direction.DESC, fields));
	}
}
