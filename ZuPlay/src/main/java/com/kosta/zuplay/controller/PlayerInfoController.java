package com.kosta.zuplay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.kosta.zuplay.model.service.stock.DealHistoryService;
import com.kosta.zuplay.model.service.stock.PlayerStockService;
import com.kosta.zuplay.model.service.stock.StockHistoryService;

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
	private StockHistoryService stockHistoryService;
	
	@Autowired
	private DealHistoryService dealHistoryService;

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
			list = playerInfoService.playerInfoSelectAll((String) session.getAttribute("playerNickname"), keyword);
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
	@RequestMapping(value = { "userInfo2" }, produces = "application/json;charset=UTF-8")
	public String userInfo2(HttpSession session, String tragetPlayer) throws Exception {
		PlayerDTO playerDTO = playerInfoService.getPlayer(tragetPlayer);
		playerDTO.setEarningRate(earningRateService.calDailyEarningRate(tragetPlayer));
		playerDTO.setTotalEarningRate(earningRateService.calEarningRate(tragetPlayer));
		playerDTO.setPlayerItemDTO(inventoryService.playerItemWorn(tragetPlayer));
		playerDTO.setTotalMoney(playerInfoService.getTotalMoney(tragetPlayer));
		List<PlayerDTO> playerList = new ArrayList<PlayerDTO>();
		playerList.add(playerDTO);
		return new Gson().toJson(playerList);
	}

	@RequestMapping(value = { "userInfo" })
	public ModelAndView userInfo(HttpSession session, String targetPlayer) throws Exception {
		SettingDAO settingDAO = sqlSession.getMapper(SettingDAO.class);
		ModelAndView mv = new ModelAndView("userInfo");
		String playerNickname = (String) session.getAttribute("playerNickname");
		PlayerDTO playerDTO = playerInfoService.getPlayerDetail(targetPlayer);
		if (playerDTO.getPlayerGender().equals("M"))
			playerDTO.setPlayerGender("남성");
		else
			playerDTO.setPlayerGender("여성");
		List<PlayerItemDTO> playerItemList = inventoryService.playerItemSelectAll(targetPlayer);
		mv.addObject("playerDTO", playerDTO);
		mv.addObject("playerItemList", playerItemList);

		if (playerDTO.getLikerList().contains(targetPlayer)) {
			mv.addObject("isLike", true);
		} else {
			mv.addObject("isLike", false);
		}
		if (!targetPlayer.equals(playerNickname)) {
			if (settingDAO.settingSelect(targetPlayer).getPsMyPage().equals("F")) {
				mv.addObject("psMyPage", false);
			} else {
				mv.addObject("psMyPage", true);
			}
		} else {
			mv.addObject("psMyPage", true);
		}
		mv.addObject("likeNum", playerDTO.getLikerList().size());

		if (context.getAttribute("#" + targetPlayer) != null)
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
	
	@RequestMapping(value={"getEarningRateList"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEarningRateList(HttpSession session, String targetPlayer) throws Exception {
		try {
			String json = new Gson().toJson(stockHistoryService.getEarningRateList(targetPlayer));
			System.out.println(json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
	
	@RequestMapping(value={"getBest"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getBest(HttpSession session, String targetPlayer) throws Exception {
		try {
			return new Gson().toJson(stockHistoryService.getBest3(targetPlayer));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
	
	
	@RequestMapping(value={"getWorst"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getWorst(HttpSession session, String targetPlayer) throws Exception {
		try {
			return new Gson().toJson(stockHistoryService.getWorst3(targetPlayer));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
	
	@RequestMapping(value={"getHistoryCount"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getHistoryCount(HttpSession session, String targetPlayer) throws Exception {
		try {	
			return new Gson().toJson(dealHistoryService.getStockHistory(targetPlayer).size());
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
	
	@RequestMapping(value={"getStockDealHistory"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getStockDealHistory(HttpSession session, String targetPlayer, String orderBy, boolean asc, @RequestParam(defaultValue="1") int page) throws Exception {
		try {	
			return new Gson().toJson(stockHistoryService.getStockDealHistory(targetPlayer, orderBy, asc, page));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
}
