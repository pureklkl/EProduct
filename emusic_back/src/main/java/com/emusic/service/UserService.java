package com.emusic.service;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public void login(HttpServletRequest request, String username, String password);
}
