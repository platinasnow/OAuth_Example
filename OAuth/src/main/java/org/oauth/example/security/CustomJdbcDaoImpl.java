package org.oauth.example.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.oauth.example.model.Users;
import org.oauth.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomJdbcDaoImpl implements UserDetailsService {

	@Autowired
	private UsersService usersService;
	
	private Collection<? extends GrantedAuthority> getUserAuthorities(Users authorities) {
		int size = authorities.getAuthority().size();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(size);
		for (int idx = 0; idx < size; idx++) {
			authList.add(new GrantedAuthorityImpl(authorities.getAuthority().get(idx)));
		}
		return authList;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userVO = new Users();
		userVO.setUserId(username);
		userVO = usersService.getUserItem(username);
		userVO.setAuthority(usersService.getAuthorities(userVO.getPn()));
		
		//return new User(username, userVO.getPassword(), getUserAuthorities(userVO));
		return new CustomUser(userVO.getPn(), username, userVO.getPassword(), userVO.getPhoneNum(), userVO.getName(), true, getUserAuthorities(userVO));
	}

}
