package org.oauth.example.service;

import java.util.List;

import org.oauth.example.mapper.UsersMapper;
import org.oauth.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

	@Autowired
	private UsersMapper usersMapper;

	public List<Users> getUserList() {
		return usersMapper.getUserList();
	}

	public Users getUserItem(String userId) {
		return usersMapper.getUserItem(userId);
	}
	
	public List<String> getAuthorities(Integer pn){
		return usersMapper.getAuthorities(pn);
	}

}
