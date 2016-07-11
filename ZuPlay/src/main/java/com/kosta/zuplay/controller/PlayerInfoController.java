package com.kosta.zuplay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dao.SettingDAO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.dto.player.PlayerItemDTO;
import com.kosta.zuplay.model.service.item.InventoryService;
import com.kosta.zuplay.model.service.player.EarningRateService;
import com.kosta.zuplay.model.service.player.PlayerInfoService;
import com.kosta.zuplay.model.service.player.RankService;
import com.kosta.zuplay.model.service.stock.PlayerStockService;

@Controller
public class PlayerInfoController {

	@Autowired
	private PlayerInfoService playerInfoService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private PlayerStockService playerStockService;

	@Autowired
	private RankService rankService;
	
	@Autowired
	private EarningRateService earningRateService;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private ServletContext context;

	@RequestMapping(value = { "playerInfoSelectAll" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String playerInfoSelectAll(HttpSession session, String keyword) throws Exception {
		List<PlayerDTO> list;
		session.getAttribute("playerNickname");
		try {
			list = playerInfoService.playerInfoSelectAll(keyword);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPlayerNickname().equals(session.getAttribute("playerNickname"))) {
					list.remove(i);
				}
			}
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	@RequestMapping(value = { "updatePI" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updatePI(HttpSession session) throws Exception {
		try {
			String playerNickname = (String) session.getAttribute("playerNickname");
			PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
			Gson gson = new Gson();
			String json = gson.toJson(playerDTO);
			return json;
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}

	}
	@ResponseBody
	@RequestMapping(value = {"userInfo2"}, produces = "application/json;charset=UTF-8")
	public String userInfo2(HttpSession session, String tragetPlayer) throws Exception {
		String playerNickname = (String) session.getAttribute("playerNickname");
		PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
		playerDTO.setEarningRate(earningRateService.calDailyEarningRate(playerNickname));
		playerDTO.setTotalEarningRate(earningRateService.calEarningRate(playerNickname));
		playerDTO.setPlayerItemDTO(inventoryService.playerItemWorn(playerNickname));
		List<PlayerDTO> playerList = new ArrayList<PlayerDTO>();
		playerList.add(playerDTO);
		return new Gson().toJson(playerList);
	}
	
	@RequestMapping(value = { "userInfo" })
	public ModelAndView userInfo(HttpSession session, String targetPlayer) throws Exception {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		ModelAndView mv = new ModelAndView("userInfo");
		String playerNickname = (String) session.getAttribute("playerNickname");
		PlayerDTO playerDTO = playerInfoService.getPlayerDetail(playerNickname);
		if (playerDTO.getPlayerGender().equals("M"))
			playerDTO.setPlayerGender("남성");
		else
			playerDTO.setPlayerGender("여성");
		List<PlayerItemDTO> playerItemList = inventoryService.playerItemSelectAll(playerNickname);
		mv.addObject("playerDTO", playerDTO);
		mv.addObject("playerItemList", playerItemList);

		if (playerDTO.getLikerList().contains(playerNickname)) {
			mv.addObject("isLike", true);
		} else {
			mv.addObject("isLike", false);
		}
		if (!targetPlayer.equals(playerNickname)) {
			if (settingDAO.settingSelect(targetPlayer).getPsMyPage().equals("F")){
				mv.addObject("psMyPage",false);
			}else{
				mv.addObject("psMyPage",true);
			}
		}else{
			mv.addObject("psMyPage", true);
		}
		mv.addObject("likeNum", playerDTO.getLikerList().size());
		
		if(context.getAttribute("#"+targetPlayer)!=null)
			mv.addObject("onOrOff", true);
		else
			mv.addObject("onOrOff", false);
		
		return mv;
	}

	@RequestMapping(value = { "playerStock" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String playerStock(HttpSession session) throws Exception {
		String playerNickname = (String) session.getAttribute("playerNickname");
		try {
			Gson gson = new Gson();
			String json = gson.toJson(playerStockService.getPlayerStocksDetail(playerNickname));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}

	@RequestMapping(value = { "getRank" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getRank(HttpSession session, String kind) throws Exception {
		try {
			return new Gson().toJson(rankService.getRank(kind));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}

	}
}
