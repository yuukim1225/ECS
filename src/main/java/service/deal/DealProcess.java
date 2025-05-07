package service.deal;

import dao.saleItem.SaleItemDAO;
import dao.user.UserDAO;
import domain.SaleItemInfo;
import domain.UserInfo;
import dto.saleItem.SaleItemDTO;
import dto.user.UserDTO;
import service.product.SaleProductInformation;

public class DealProcess {
	public boolean confirmStatusSaleToSaled(int saleItemId) {
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		
		sii = spi.getSaleProductInfo(saleItemId);
		
		if(sii != null) {
			if(sii.getItemSaleState()== 0) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public boolean updateStatusSaleToSaled(int saleItemId, String buyUserId) {
		int result = 0;
		
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		
		sii = spi.getSaleProductInfo(saleItemId);
		if(sii != null) {
			sidt.setItemSaleId(sii.getItemSaleId());
			sidt.setItemSaleName(sii.getItemSaleName());
			sidt.setItemSalePrice(sii.getItemSalePrice());
			sidt.setUserId(sii.getUserId());
			sidt.setBuyUserId(sii.getBuyUserId());
			sidt.setItemSaleCategory(sii.getItemSaleCategory());
			sidt.setItemSaleText(sii.getItemSaleText());
			sidt.setItemSaleDate(sii.getItemSaleDate());
			sidt.setItemSaleImg(sii.getItemSaleImg());
			sidt.setItemSaleState(sii.getItemSaleState());
			
			result = sida.updateItemSaleStatus(sidt, buyUserId);
			if(result == 1) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean updateStatusSaledTo(int saleItemId, int updateDealStatus) {
		int result = 0;
		
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		
		sii = spi.getSaleProductInfo(saleItemId);
		if(sii != null) {
			sidt.setItemSaleId(sii.getItemSaleId());
			sidt.setItemSaleName(sii.getItemSaleName());
			sidt.setItemSalePrice(sii.getItemSalePrice());
			sidt.setUserId(sii.getUserId());
			sidt.setBuyUserId(sii.getBuyUserId());
			sidt.setItemSaleCategory(sii.getItemSaleCategory());
			sidt.setItemSaleText(sii.getItemSaleText());
			sidt.setItemSaleDate(sii.getItemSaleDate());
			sidt.setItemSaleImg(sii.getItemSaleImg());
			sidt.setItemSaleState(sii.getItemSaleState());
			
			result = sida.updateItemSaleStatus(sidt, updateDealStatus);
			if(result == 1) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean updateStatusSaledToEnd(int saleItemId, int goodPBadP) {
		int result = 0;
		
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		
		sii = spi.getSaleProductInfo(saleItemId);
		if(sii != null) {
			sidt.setItemSaleId(sii.getItemSaleId());
			sidt.setItemSaleName(sii.getItemSaleName());
			sidt.setItemSalePrice(sii.getItemSalePrice());
			sidt.setUserId(sii.getUserId());
			sidt.setBuyUserId(sii.getBuyUserId());
			sidt.setItemSaleCategory(sii.getItemSaleCategory());
			sidt.setItemSaleText(sii.getItemSaleText());
			sidt.setItemSaleDate(sii.getItemSaleDate());
			sidt.setItemSaleImg(sii.getItemSaleImg());
			sidt.setItemSaleState(sii.getItemSaleState());
			
			result = sida.updateItemSaleStatusInDealEnd(sidt, goodPBadP);
			if(result == 1) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public UserInfo referSaleUserData(String userId) {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.getUserInfo(userId);
		
		if(userDTO != null ) {
			UserInfo userInfo = new UserInfo(userDTO.getUserId(), userDTO.getPassword());
			userInfo.setName(userDTO.getName());
			userInfo.setTradeNum(userDTO.getTradeNum());
			userInfo.setGoodNum(userDTO.getGoodNum());
			userInfo.setBadNum(userDTO.getBadNum());
			userInfo.setSaleAddress(userDTO.getSaleAddress());
			
			return userInfo;
		}
		return null;
	}
	
	public UserInfo referBuyUserData(String userId) {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.getUserInfo(userId);
		
		if(userDTO != null ) {
			UserInfo userInfo = new UserInfo(userDTO.getUserId(), userDTO.getPassword());
			userInfo.setName(userDTO.getName());
			userInfo.setTradeNum(userDTO.getTradeNum());
			userInfo.setGoodNum(userDTO.getGoodNum());
			userInfo.setBadNum(userDTO.getBadNum());
			userInfo.setHoldPoint(userDTO.getHoldPoint());
			userInfo.setSaleAddress(userDTO.getBuyAddress());
			
			return userInfo;
		}
		return null;
	}

}
