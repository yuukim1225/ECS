package controller.payment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.SaleItemInfo;
import domain.UserInfo;
import service.deal.DealProcess;
import service.product.SaleProductInformation;

/**
 * Servlet implementation class BuyProductConfirmController
 */
@WebServlet("/buyProduct")
public class BuyProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String userId = req.getParameter("userId");
		String strItemSaleId = req.getParameter("itemSaleId");
		int ItemSaleId = Integer.parseInt(strItemSaleId);
		boolean result = false;
		
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		DealProcess dp = new DealProcess();
		UserInfo saleUi = new UserInfo();
		UserInfo buyUi = new UserInfo();
		
		buyUi = dp.referBuyUserData(userId); 
		sii = spi.getSaleProductInfo(ItemSaleId);
		saleUi = dp.referSaleUserData(sii.getUserId());
		result = dp.confirmStatusSaleToSaled(ItemSaleId);
		req.setAttribute("saleItemInfo",sii);
		req.setAttribute("buyUi",buyUi);
		req.setAttribute("saleUi",saleUi);
		
		if(result) {
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productBuyConfirm.jsp"); 
			rd.forward(req, res);
		}else {
			req.setAttribute("dealError", "本商品は成約済みです");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productInfo.jsp");
			rd.forward(req, res);
		}
	}

}
