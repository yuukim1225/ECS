package service.product;

import dao.saleItem.SaleItemDAO;
import domain.SaleItemInfo;
import dto.saleItem.SaleItemDTO;

public class SaleProductInformation {
	public SaleItemInfo getSaleProductInfo(int itemSaleId) {
		SaleItemDAO sida = new SaleItemDAO();
		SaleItemDTO sidt = new SaleItemDTO();
		SaleItemInfo sii = null;
		
		sidt = sida.getSaleItemInfo(itemSaleId);
		if(sidt != null) {
			sii = new SaleItemInfo();
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
		}
		return sii;
	}

}
