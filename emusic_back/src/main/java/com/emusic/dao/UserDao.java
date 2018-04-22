package com.emusic.dao;

import com.emusic.model.User;

public interface UserDao {
	String addNewUser(User user);
	User getUserByEmail(String email);
	User getUserById(String id);
	void updateUser(User user);
}
