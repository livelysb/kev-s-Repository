package com.kosta.zuplay.model.serviceImpl;

import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.PlayerDTO;
import com.kosta.zuplay.model.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public boolean firstLoginCheck(String naverId) {
		return false;
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		return false;
	}

}
