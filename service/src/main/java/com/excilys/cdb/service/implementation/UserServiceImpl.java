package com.excilys.cdb.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Role;
import com.excilys.cdb.persistence.UserDao;

/**
 * This class is the intermediary between user interface and User DAO. Transmits
 * queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		com.excilys.cdb.model.User user = userDao.getByName(name);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);
	}
	

	private User buildUserForAuthentication(com.excilys.cdb.model.User user, List<GrantedAuthority> authorities) {

		return new User(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authSet.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(authSet);

		return result;
	}

}
