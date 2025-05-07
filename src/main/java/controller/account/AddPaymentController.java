package controller.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddPaymentController
 */
@WebServlet("/addPayment")
public class AddPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/payment/addPayment.jsp");
		rd.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean result = true;
		
		int addPayNum = Integer.parseInt(req.getParameter("depositAmount"));
		if(addPayNum < 500 || addPayNum > 10000) {
			result = false;
		}
		if(result) {
			req.setAttribute("depositAmount",addPayNum);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/payment/addPaymentConfirm.jsp");
			rd.forward(req, res);
		}else {
			req.setAttribute("inputError", "不正な金額が入力されました");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/payment/addPayment.jsp");
			rd.forward(req, res);
		}
	}
}
