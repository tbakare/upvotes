package com.upvotes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

class UserDetailsServiceTest {

	@SuppressWarnings("deprecation")
	@Test
	public void generate_encrypted_password() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "asdfasdf";
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println(encodedPassword);
		
		Assert.doesNotContain(rawPassword, encodedPassword );
		}
	

}
