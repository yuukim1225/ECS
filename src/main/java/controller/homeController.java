package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.deal.DealProcess;
import service.product.SearchProductInformation;
import service.product.ViewSaleProduct;

/**
 * Servlet implementation class homeController
 */
@WebServlet("/home")
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		DealProcess dp = new DealProcess();
		req.setAttribute("dp", dp);
		
		ViewSaleProduct vsp = new ViewSaleProduct();
		req.setAttribute("sil", vsp.allSaleItemsList());
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(req, res);	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String searchFlag = req.getParameter("searchFlag");
		if(searchFlag != null && searchFlag.equals("1")){
			int SearchColumn = 0;
			String searchWord;
			SearchProductInformation spi = new SearchProductInformation();
			
			if(req.getParameter("searchType").equals("productName")) {
				SearchColumn = 0;
				searchWord = req.getParameter("searchProductName");
				req.setAttribute("sil", spi.searchProductFromSaleInfo(SearchColumn, searchWord));
			};
			if(req.getParameter("searchType").equals("productCategory")) {
				SearchColumn = 1;
				searchWord = req.getParameter("searchProductCategory");
				req.setAttribute("sil", spi.searchProductFromSaleInfo(SearchColumn, searchWord));
			};
			if(req.getParameter("searchType").equals("userName")) {
				SearchColumn = 2;
				searchWord = req.getParameter("searchUserName");
				req.setAttribute("sil", spi.searchProductFromSaleInfo(SearchColumn, searchWord));
			};
		}else {
			ViewSaleProduct vsp = new ViewSaleProduct();
			req.setAttribute("sil", vsp.allSaleItemsList());
		}
		DealProcess dp = new DealProcess();
		req.setAttribute("dp", dp);
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/home.jsp");
		rd.forward(req, res);	
	}
}
