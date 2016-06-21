package com.kosta.zuplay.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean firstLoginCheck(String naverId) {
		PlayerDTO playerDTO=sqlSession.selectOne("com.kosta.zuplay.loginMapper", naverId);
		if(playerDTO.getPlayerNickname()!=null){
			return false;
		}
		return true;
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		int result=sqlSession.insert("com.kosta.zuplay.loginMapper", playerDTO);
		if(result>0){
			return true;
		}
		return false;
	}

}
