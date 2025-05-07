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
import service.account.EditAccountService;
import service.log.LoginService;

/**
 * Servlet implementation class AddPaymentController
 */
@WebServlet("/addPaymentConfirm")
public class AddPaymentConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean result;
		
		UserInfo ui = new UserInfo();
		EditAccountService eas = new EditAccountService();
		String userId;
		String password;
		
		HttpSession session = req.getSession();
		ui = (UserInfo)session.getAttribute("ui");
		int addPayNum = Integer.parseInt(req.getParameter("depositAmount"));
		userId = ui.getUserId();
		password = ui.getPassword();
		
		LoginService ls = new LoginService();
		ui = ls.LoginCheck(userId, password);
		
		result = eas.AddPayment(ui, addPayNum);
		if(result) {
			ui = ls.LoginCheck(userId, password);
			session.setAttribute("ui",ui);
			req.setAttribute("depositAmount",addPayNum);
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/payment/addPaymentDone.jsp");
			rd.forward(req, res);
		}else {
			session.setAttribute("ui",ui);
			req.setAttribute("inputError", "入金処理が失敗しました。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/payment/addPayment.jsp");
			rd.forward(req, res);
		}
	}
}
