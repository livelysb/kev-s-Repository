package com.kosta.zuplay.model.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.LoginDAOImpl;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAOImpl loginDaoImpl;
	
	@Override
	public boolean firstLoginCheck(String naverId) {
		return loginDaoImpl.firstLoginCheck(naverId);
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		return loginDaoImpl.joinMember(playerDTO);
	}

}
