package controller.account;

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
 * Servlet implementation class salingProductController
 */
@WebServlet("/salingProduct")
public class SalingProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		UserInfo ui = new UserInfo();
		
		HttpSession session = req.getSession();
		ui = (UserInfo)session.getAttribute("ui");
		ViewSaleProduct vsp = new ViewSaleProduct();
		req.setAttribute("sil", vsp.userSaleItemsList(ui.getUserId()));
		DealProcess dp = new DealProcess();
		req.setAttribute("dp", dp);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/salingProductInformation.jsp");
		rd.forward(req, res);
	}

}
