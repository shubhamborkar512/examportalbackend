package com.exam.service;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.exam.models.UserAccount;
import com.exam.models.UserRole;
import com.exam.repos.RoleRepository;
import com.exam.repos.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepos;
	
	@Autowired
	private RoleRepository rolerepos;
	
	
//	Creating user
	public UserAccount createUser(UserAccount user,Set<UserRole> userroles) throws Exception {
		UserAccount local=userrepos.findByUsername(user.getUsername());
		if(local!=null) {
			//user already present
			System.out.println("User already present");
			throw new Exception("User already present");
		}
		else {
			//user create
			
			for(UserRole ur:userroles) {
				rolerepos.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userroles);
			local=this.userrepos.save(user);
		}
		return local;
	}
	
//	update user
	public UserAccount updateUser(UserAccount user) throws Exception {
		UserAccount local=this.userrepos.findByUsername(user.getUsername());
			if(local!=null && local.getId()!=user.getId()) {
				throw new Exception("User Already there try with another username");
			}
			return this.userrepos.save(user);
	}
	
	
//	get user
	public UserAccount getUser(String username) throws Exception {
		UserAccount local=this.userrepos.findByUsername(username);
		if(local==null) {
			throw new Exception("User not found");
		}
		return local;
	}
	
	
//	Delete user
	public void deleteUser(Long id) {
		try {
			userrepos.deleteById(id);
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	
}
