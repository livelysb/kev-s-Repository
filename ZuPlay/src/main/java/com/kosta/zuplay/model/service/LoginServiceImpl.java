package com.kosta.zuplay.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.LoginDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean firstLoginCheck(String naverId) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		return loginDAO.firstLoginCheck(naverId);
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		return loginDAO.joinMember(playerDTO);
	}
}
