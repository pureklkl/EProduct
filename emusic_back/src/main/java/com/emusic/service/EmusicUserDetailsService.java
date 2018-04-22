package com.emusic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.UserDao;
import com.emusic.model.Privilege;
import com.emusic.model.Role;
import com.emusic.model.User;

@Service
@Transactional
public class EmusicUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
    	System.out.println("user detail");
    	User user = userDao.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ email);
        }
        boolean enabled = true; // enabled user - email validation
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails.User(
        		user.getEmail(),
        		user.getPassword(),
        		enabled,
        		accountNonExpired, credentialsNonExpired, accountNonLocked,
        		getAuthorities(user.getRoles())
        		);
    }
    
    private static List<GrantedAuthority> getAuthorities (Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        HashSet<String> privileges = new HashSet<String>();
        for (Role role : roles) {
        	authorities.add(new SimpleGrantedAuthority(role.getName()));
        	for(Privilege prvlg : role.getPrivileges()) {
        		privileges.add(prvlg.getName());
        	}
        }
        for(String prvlg : privileges) {
        	authorities.add(new SimpleGrantedAuthority(prvlg));
        }
        System.out.println(privileges);
        return authorities;
    }
}
