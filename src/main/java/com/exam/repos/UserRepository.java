package com.exam.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.exam.models.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount,Long>{
	public UserAccount findByUsername(String username);
}
