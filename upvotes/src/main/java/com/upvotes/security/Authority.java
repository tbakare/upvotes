package com.upvotes.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.upvotes.domain.User;

@Entity
public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = 6832794557152803060L;
	private int id;
	private String authority;
	private User user;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
