package com.emusic.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.UserDao;
import com.emusic.model.User;

@Repository
@Transactional
public class UserDaoImpl extends DaoImpl implements UserDao {
	
	@Override
	public String addNewUser(User user) {
		Session session = getSession();
		
		User dupUser = getUserByEmail(user.getEmail(), session);
		
		if(dupUser != null) {
			return null;
		}
		session.save(user);
		session.flush();
		return user.getId();
	}

	public void updateUser(User user) {
		Session session = getSession();
		session.saveOrUpdate(user);
		session.flush();
	}
	
	private User getUserByEmail(String email, Session session) {
		return getUniqueField("email", email, User.class, session);
	}	
	
	@Override
	public User getUserById(String id) {
		Session session = getSession();
		User user = (User) session.get(User.class, id);
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		Session session = getSession();
		return getUserByEmail(email, session);
	}
	
	
}
