package com.kosta.zuplay.model.service.system;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.LoginDAO;
import com.kosta.zuplay.model.dao.PlayerItemDAO;
import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Service
public class LoginServiceImpl implements LoginService {

   @Autowired
   private SqlSession sqlSession;
   
   @Autowired
   private PlayerInfoService playerInfoService;
   
   @Override
   public boolean firstLoginCheck(String naverId) throws Exception{
      LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);   
      PlayerDTO playerDTO=loginDAO.firstLoginCheck(naverId);
      if(playerDTO!=null){
         return false;
      }
      return true;
   }

   @Override
   public boolean joinMember(PlayerDTO playerDTO) throws Exception{
	  String nickname=playerDTO.getPlayerNickname();
	  for(int i = 0;i<nickname.length();i++){
		  if(nickname.charAt(i)=='<'){
			  return false;
		  }
	  }
      LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
      PlayerItemDAO playerItemDAO=sqlSession.getMapper(PlayerItemDAO.class);
      SettingDAO settingDAO=sqlSession.getMapper(SettingDAO.class);
      int result=loginDAO.joinMember(playerDTO);
      settingDAO.settingInsert(playerDTO.getPlayerNickname());
      if(playerDTO.getPlayerGender().equals("M")){
         playerItemDAO.auctionInsertPlayerItem(new PlayerItemDTO(0,playerDTO.getPlayerNickname(),"m_hair_10",0,null));
      }else{
         playerItemDAO.auctionInsertPlayerItem(new PlayerItemDTO(0,playerDTO.getPlayerNickname(),"f_hair_00",0,null));
      }
      if(result==0){
         return false;
      }
      return true;
   }

   @Override
   public boolean checkRepetition(String playerNickname) throws Exception{
      LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
      String playerNaverId=loginDAO.checkRepetiton(playerNickname);
      if(playerNaverId!=null){
         return false;
      }
      return true;
   }

   @Override
   public String getNickname(String playerNaverId) throws Exception{
      LoginDAO loginDAO=sqlSession.getMapper(LoginDAO.class);
      return loginDAO.getNickname(playerNaverId);
   }

   @Override
   public boolean getRubyPerDay(String playerNickname) throws Exception{
      if(playerInfoService.updateRuby(playerNickname,playerInfoService.getRuby(playerNickname) + 1000))
         return true;
      return false;
   }
}