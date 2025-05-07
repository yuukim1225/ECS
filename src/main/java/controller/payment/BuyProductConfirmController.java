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
 * Servlet implementation class BuyProductDoneController
 */
@WebServlet("/buyProductCofirm")
public class BuyProductConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String strSaleItemId = req.getParameter("saleItemId");
		String saleUserId = req.getParameter("saleUserId");
		String buyUserId = req.getParameter("buyUserId");

		int saleItemId = Integer.parseInt(strSaleItemId);
		boolean cofirmResult = false;
		boolean updateResult = false;
		
		SaleItemInfo sii = new SaleItemInfo();
		SaleProductInformation spi = new SaleProductInformation();
		DealProcess dp = new DealProcess();
		UserInfo saleUi = new UserInfo();
		UserInfo buyUi = new UserInfo();
		
		buyUi = dp.referBuyUserData(buyUserId); 
		saleUi = dp.referSaleUserData(saleUserId);
		sii = spi.getSaleProductInfo(saleItemId);
		cofirmResult = dp.confirmStatusSaleToSaled(saleItemId);
		req.setAttribute("saleItemInfo",sii);
		req.setAttribute("buyUi",buyUi);
		req.setAttribute("saleUi",saleUi);
		
		if(cofirmResult) {
			updateResult = dp.updateStatusSaleToSaled(saleItemId,buyUserId);
			if(updateResult) {
				/*購入後のアカウント、商品の最新情報を渡す*/
				buyUi = dp.referBuyUserData(buyUserId); 
				saleUi = dp.referSaleUserData(saleUserId);
				sii = spi.getSaleProductInfo(saleItemId);
				req.setAttribute("saleItemInfo",sii);
				req.setAttribute("buyUi",buyUi);
				req.setAttribute("saleUi",saleUi);
				
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productBuyDone.jsp"); 
				rd.forward(req, res);
			}else {
				req.setAttribute("dealError", "購入失敗しました。");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productInfo.jsp");
				rd.forward(req, res);
			}
		}else {
			req.setAttribute("dealError", "本商品は成約済みです");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productInfo.jsp");
			rd.forward(req, res);
		}
	}
}
