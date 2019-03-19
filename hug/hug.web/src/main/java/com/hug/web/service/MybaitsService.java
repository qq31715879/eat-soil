package com.hug.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hug.dao.bean.User;
import com.hug.dao.mapper.UserMapper;

@Service
public class MybaitsService {

	@Autowired
	private UserMapper userMapper;

	public Map<Integer, User> selectMap() {
		return userMapper.selectMap();
	}
}
