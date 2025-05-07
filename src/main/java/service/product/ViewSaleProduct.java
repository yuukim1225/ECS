package service.product;

import java.util.ArrayList;
import java.util.List;

import dao.saleItem.SaleItemDAO;
import domain.SaleItemInfo;
import dto.saleItem.SaleItemDTO;

public class ViewSaleProduct {
	public List<SaleItemInfo> allSaleItemsList() {
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		
		List<Integer> idSaleItemsAll = sida.IdSaleItemsAll();
		List<SaleItemInfo> sil = new ArrayList<SaleItemInfo>();
		
		for(int i = 0; i < idSaleItemsAll.size(); i++) {
			sidt = sida.getSaleItemInfo(idSaleItemsAll.get(i));
			
			SaleItemInfo sii = new SaleItemInfo();
			sii.setItemSaleId(sidt.getItemSaleId());
			sii.setItemSaleName(sidt.getItemSaleName());
			sii.setItemSalePrice(sidt.getItemSalePrice());
			sii.setUserId(sidt.getUserId());
			sii.setBuyUserId(sidt.getBuyUserId());
			sii.setItemSaleCategory(sidt.getItemSaleCategory());
			sii.setItemSaleText(sidt.getItemSaleText());
			sii.setItemSaleDate(sidt.getItemSaleDate());
			sii.setItemSaleImg(sidt.getItemSaleImg());
			sii.setItemSaleState(sidt.getItemSaleState());
			sil.add(sii);
		}
		return sil;
	}
	
	public List<SaleItemInfo> dealSaleItemsList(String userId){
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		
		List<Integer> idSaleItemsDeal = sida.IdSaleItemsDeal(userId);
		List<SaleItemInfo> sil = new ArrayList<SaleItemInfo>();
		
		for(int i = 0; i < idSaleItemsDeal.size(); i++) {
			sidt = sida.getSaleItemInfo(idSaleItemsDeal.get(i));
			
			SaleItemInfo sii = new SaleItemInfo();
			sii.setItemSaleId(sidt.getItemSaleId());
			sii.setItemSaleName(sidt.getItemSaleName());
			sii.setItemSalePrice(sidt.getItemSalePrice());
			sii.setUserId(sidt.getUserId());
			sii.setBuyUserId(sidt.getBuyUserId());
			sii.setItemSaleCategory(sidt.getItemSaleCategory());
			sii.setItemSaleText(sidt.getItemSaleText());
			sii.setItemSaleDate(sidt.getItemSaleDate());
			sii.setItemSaleImg(sidt.getItemSaleImg());
			sii.setItemSaleState(sidt.getItemSaleState());
			sil.add(sii);
		}
		return sil;
	}
	
	public List<SaleItemInfo> userSaleItemsList(String userId){
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		
		List<Integer> idSaleItemsUser = sida.IdSaleItemsUser(userId);
		List<SaleItemInfo> sil = new ArrayList<SaleItemInfo>();
		
		for(int i = 0; i < idSaleItemsUser.size(); i++) {
			sidt = sida.getSaleItemInfo(idSaleItemsUser.get(i));
			
			SaleItemInfo sii = new SaleItemInfo();
			sii.setItemSaleId(sidt.getItemSaleId());
			sii.setItemSaleName(sidt.getItemSaleName());
			sii.setItemSalePrice(sidt.getItemSalePrice());
			sii.setUserId(sidt.getUserId());
			sii.setBuyUserId(sidt.getBuyUserId());
			sii.setItemSaleCategory(sidt.getItemSaleCategory());
			sii.setItemSaleText(sidt.getItemSaleText());
			sii.setItemSaleDate(sidt.getItemSaleDate());
			sii.setItemSaleImg(sidt.getItemSaleImg());
			sii.setItemSaleState(sidt.getItemSaleState());
			sil.add(sii);
		}
		return sil;
	}
}
