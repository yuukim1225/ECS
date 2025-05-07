package service.product;

import java.util.ArrayList;
import java.util.List;

import dao.saleItem.SaleItemDAO;
import domain.SaleItemInfo;
import dto.saleItem.SaleItemDTO;

public class SearchProductInformation {
	public List<SaleItemInfo> searchProductFromSaleInfo(int SearchColumn, String searchWord){
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		List<SaleItemInfo> spfsl = new ArrayList<SaleItemInfo>();
		List<Integer> IdSpfsl = new ArrayList<Integer>();
		
		searchWord = "%" + searchWord +"%";
		IdSpfsl = sida.IdSaleItemsSearch(SearchColumn, searchWord);
		for(int i = 0; i< IdSpfsl.size(); i++) {
			sidt = sida.getSaleItemInfo(IdSpfsl.get(i));
			
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
				
			spfsl.add(sii);
		}
		return spfsl;
	}
}
