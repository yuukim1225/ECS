package controller.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.SaleItemInfo;
import service.deal.DealProcess;
import service.product.SaleProductInformation;

/**
 * Servlet implementation class DealProduct
 */
@WebServlet("/dealProduct")
public class DealProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int saleItemId = Integer.parseInt(req.getParameter("itemSaleId"));
		SaleProductInformation spdi = new SaleProductInformation();
		SaleItemInfo saleItemInfo = spdi.getSaleProductInfo(saleItemId);
		req.setAttribute("saleItemInfo", saleItemInfo);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/dealProduct.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		boolean result = false;
		
		int saleItemId = Integer.parseInt(req.getParameter("saleItemId"));
		DealProcess dp = new DealProcess();
		
		if(req.getParameter("shippingStatus") != null) {
			int shippingStatus = Integer.parseInt(req.getParameter("shippingStatus"));
			if(shippingStatus == 0) {
				result = dp.updateStatusSaledTo(saleItemId, 2);/* 2 : 商品発送準備中 */
			}
			if(shippingStatus == 1) {
				result = dp.updateStatusSaledTo(saleItemId, 3);/* 3 : 商品発送済み */
			}
		}
		
		else if(req.getParameter("receivingStatus") != null) {
			int receivingStatus = Integer.parseInt(req.getParameter("receivingStatus"));
			if(receivingStatus == 1) {
				result = dp.updateStatusSaledTo(saleItemId, 4);/* 4 : 商品受け取り済み */
			}else {
				result = true;
			}
		}
		
		else if(req.getParameter("tradeEvaluation") != null) {
			int tradeEvaluation = Integer.parseInt(req.getParameter("tradeEvaluation"));
			if(tradeEvaluation == 0 || tradeEvaluation == 1 ) {
				result = dp.updateStatusSaledToEnd(saleItemId, tradeEvaluation);/* 5 : 取引完了 */
			}
		}
		
		if(result) {
			req.setAttribute("msg", "取引のステータス更新が完了しました");
		}else {
			req.setAttribute("msg", "取引のステータス更新が失敗しました");
		}
		SaleProductInformation spdi = new SaleProductInformation();
		SaleItemInfo saleItemInfo = spdi.getSaleProductInfo(saleItemId);
		req.setAttribute("saleItemInfo", saleItemInfo);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/dealProduct.jsp");
		rd.forward(req, res);
	}
}
