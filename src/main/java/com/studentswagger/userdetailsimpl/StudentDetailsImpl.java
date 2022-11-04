package com.studentswagger.userdetailsimpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.studentswagger.model.Students;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StudentDetailsImpl implements UserDetails {

	private Students std;
	private List<GrantedAuthority> authorities;

	public StudentDetailsImpl() {
		super();
	}

	public StudentDetailsImpl(Students std) {
		this.std = std;
		this.authorities = Arrays.stream(std.getStRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return std.getStPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return std.getStUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return std.stActive;
	}

}
