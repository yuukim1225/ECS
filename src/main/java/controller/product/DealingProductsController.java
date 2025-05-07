package controller.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserInfo;
import service.deal.DealProcess;
import service.product.ViewSaleProduct;

/**
 * Servlet implementation class DealProductController
 */
@WebServlet("/dealingProducts")
public class DealingProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserInfo ui = new UserInfo();
		ViewSaleProduct vsp = new ViewSaleProduct();
		
		HttpSession session = req.getSession();
		ui = (UserInfo)session.getAttribute("ui");
		req.setAttribute("dsil", vsp.dealSaleItemsList(ui.getUserId()));
		DealProcess dp = new DealProcess();
		req.setAttribute("dp", dp);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/product/dealingProducts.jsp");
		rd.forward(req, res);
	}
}
