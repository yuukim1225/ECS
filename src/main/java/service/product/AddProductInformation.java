package service.product;

import java.io.File;

import dao.saleItem.SaleItemDAO;
import domain.SaleItemInfo;
import dto.saleItem.SaleItemDTO;

public class AddProductInformation {
	
	public boolean addProductDo(SaleItemInfo saleItemInfo) {
		SaleItemDAO saleItemDAO = new SaleItemDAO();
		int result;
		
		SaleItemDTO saleItemDTO = new SaleItemDTO();
		saleItemDTO.setItemSaleName(saleItemInfo.getItemSaleName());
		saleItemDTO.setItemSalePrice(saleItemInfo.getItemSalePrice());
		saleItemDTO.setUserId(saleItemInfo.getUserId());
		saleItemDTO.setItemSaleCategory(saleItemInfo.getItemSaleCategory());
		saleItemDTO.setItemSaleText(saleItemInfo.getItemSaleText());
		saleItemDTO.setItemSaleImg(saleItemInfo.getItemSaleImg());
		
		result = saleItemDAO.insertSaleItemInfo(saleItemDTO);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}
	public boolean makeDirectory(String dirPath){
	    File file = new File(dirPath);
	    
	    if (file.mkdir()) {
	        System.out.println("フォルダの作成に成功しました");
	        String str = file.getAbsolutePath();
	        System.out.println("pass : " + str);
	        return true;
	    } else {
	        System.out.println("フォルダの作成に失敗しました");
	        String str = file.getAbsolutePath();
	        System.out.println("pass : " + str);
	        return false;
	    }
	}
}
