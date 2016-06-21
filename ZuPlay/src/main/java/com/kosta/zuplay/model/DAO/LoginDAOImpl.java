package com.kosta.zuplay.model.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public boolean firstLoginCheck(String naverId) {
		return false;
	}

	@Override
	public boolean joinMember(PlayerDTO playerDTO) {
		return false;
	}

}
