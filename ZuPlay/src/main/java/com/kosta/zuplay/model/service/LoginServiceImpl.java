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
		
		PlayerDTO playerDTO=loginDAO.firstLoginCheck(naverId);
		
		System.out.println("[ Log ] naverId = "+ naverId);
		
		if(playerDTO!=null){
			System.out.println("[ Log ] 기존회원 로그인");
			return false;
		}
		System.out.println("[ Log ] 신규회원 최초 로그인");
		return true;
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		int result=loginDAO.joinMember(playerDTO);
		System.out.println("[ Log ] playerNaverId = " +playerDTO.getPlayerNaverId()+" | playerNickname = "+playerDTO.getPlayerNickname()+" | playerGender = "+playerDTO.getPlayerGender()+" | playerAge = "+playerDTO.getPlayerAge());
		if(result==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean checkRepetition(String playerNickname) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		String playerNaverId=loginDAO.checkRepetiton(playerNickname);
		if(playerNaverId!=null){
			return false;
		}
		return true;
	}

	@Override
	public String getNickname(String playerNaverId) {
		LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
		return loginDAO.getNickname(playerNaverId);
	}
}
