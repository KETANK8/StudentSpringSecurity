package com.studentswagger.service;

import java.util.Optional;

import com.studentswagger.model.Students;
import com.studentswagger.repository.StudentRepository;
import com.studentswagger.userdetailsimpl.StudentDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Students> st = studentRepository.findByStUserName(username);
		if (st == null)
			throw new UsernameNotFoundException(username);
		return st.map(StudentDetailsImpl::new).get();
	}
}
