package org.oauth.example.mapper;

import java.util.List;

import org.oauth.example.model.Users;

public interface UsersMapper {

	public List<Users> getUserList();
	public Users getUserItem(String userId);
	public List<String> getAuthorities(Integer pn);
	
}
