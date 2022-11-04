package com.studentswagger.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.studentswagger.exception.GlobalException;
import com.studentswagger.model.Students;
import com.studentswagger.modeldto.StudentsDTO;
import com.studentswagger.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService stdService;

	// MAPPING METHOD 1
	// TO HANDLE REQUEST TO ADD NEW STUDENT IN DATABASE
	@PostMapping("/add")
	public ResponseEntity<String> addStudent(@RequestBody @Valid StudentsDTO std) {
		String status = stdService.addStudent(std);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 2
	// TO HANDLE REQUEST TO FETCH STUDENT DETAIL BASED ON STUDENT ID
	@GetMapping("/fetch/id/{stId}")
	public ResponseEntity<Optional<Students>> getStudentById(@PathVariable int stId) {
		return new ResponseEntity<>(stdService.getStudentById(stId), HttpStatus.OK);
	}

	// MAPPING METHOD 3
	// HANLE REQUEST TO UPDATE STUDENT DATA IN DATABASE
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentsDTO std) throws GlobalException {
		String status = stdService.updateStudent(std);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 4
	// HANDLE REQUEST TO DELETE STUDENT DATA FROM DATABASE USING PRIMARY KEY
	@DeleteMapping("/remove/{stId}")
	public ResponseEntity<String> deleteStudent(@PathVariable int stId) throws GlobalException {
		String status = stdService.deleteStudent(stId);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
