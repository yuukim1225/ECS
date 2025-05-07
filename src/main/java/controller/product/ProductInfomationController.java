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
 * Servlet implementation class ProductInfomation
 */
@WebServlet("/productInfo")
public class ProductInfomationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int saleItemId = Integer.parseInt(req.getParameter("saleItemId"));
		SaleProductInformation spdi = new SaleProductInformation();
		SaleItemInfo saleItemInfo = spdi.getSaleProductInfo(saleItemId);
		req.setAttribute("saleItemInfo", saleItemInfo);
		DealProcess dp = new DealProcess();
		req.setAttribute("dp", dp);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/productInfo.jsp");
		rd.forward(req, res);

		
	}
}
