package com.studentswagger.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.studentswagger.exception.GlobalException;
import com.studentswagger.model.Students;
import com.studentswagger.modeldto.StudentsDTO;
import com.studentswagger.service.AdminService;

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
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService admService;

	// MAPPING METHOD 1
	// TO HANDLE REQUEST TO ADD NEW STUDENT IN DATABASE
	@PostMapping("/add")
	public ResponseEntity<String> addStudent(@RequestBody @Valid StudentsDTO std) {
		String status = admService.addStudent(std);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 2
	// TO HANDLE REQUEST TO FETCH ALL STUDENT IN DATABASE
	@GetMapping("/fetch/all")
	public ResponseEntity<List<Students>> getAllStudents() {
		return new ResponseEntity<>(admService.getAllStudent(), HttpStatus.OK);
	}

	// MAPPING METHOD 3
	// TO HANDLE REQUEST TO FETCH STUDENT DETAIL BASED ON STUDENT ID
	@GetMapping("/fetch/id/{stId}")
	public ResponseEntity<Optional<Students>> getStudentById(@PathVariable int stId) {
		return new ResponseEntity<>(admService.getStudentById(stId), HttpStatus.OK);
	}

	// MAPPING METHOD 4
	// HANDLE REQUEST TO FETCH STUDENT DATA BASED ON STUDENT DOMAIN
	@GetMapping("/fetch/domain/{stDomain}")
	public ResponseEntity<List<Students>> getStudentByDomain(@PathVariable String stDomain) throws GlobalException {
		if (admService.getStudentByDomain(stDomain) != null)
			return new ResponseEntity<>(admService.getStudentByDomain(stDomain), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 5
	// HANDLE REQUEST TO FETCH STUDENT DATA BASED ON STUDENT AGE
	@GetMapping("/fetch/age/{stAge}")
	public ResponseEntity<List<Students>> getStudentByAge(@PathVariable int stAge) throws GlobalException {
		if (admService.getStudentByAge(stAge) != null)
			return new ResponseEntity<>(admService.getStudentByAge(stAge), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 6
	// HANLE REQUEST TO UPDATE STUDENT DATA IN DATABASE
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentsDTO std) throws GlobalException {
		String status = admService.updateStudent(std);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 7
	// HANDLE REQUEST TO DELETE STUDENT DATA FROM DATABASE USING PRIMARY KEY
	@DeleteMapping("/remove/{stId}")
	public ResponseEntity<String> deleteStudent(@PathVariable int stId) throws GlobalException {
		String status = admService.deleteStudent(stId);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 8
	// HANDLE REQUEST TO SORT THE STUDENT USING ANY COLUMN DATA
	@GetMapping("/fetch/sort/{field}")
	public ResponseEntity<List<Students>> sortStudent(@PathVariable String field) {
		return new ResponseEntity<>(admService.sortStudent(field), HttpStatus.OK);
	}

	// Request Method 9
	// Method to handle the request to add a list of students in database
	// Setting request mapping with post method to store the list
	@PostMapping("add/list/students")
	public String addAllStudents(@RequestBody List<Students> std) {
		// calling addAllStudents method from service
		// to add the students list in database
		// return success message
		return admService.addAllStudents(std);
	}
}
