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

/**
 * Servlet implementation class EditAccountConfirmController
 */
@WebServlet("/editAccountConfirm")
public class EditAccountConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String accountType = req.getParameter("accountType");
		String buyAddress = req.getParameter("buyAddress");
		String saleAddress = req.getParameter("saleAddress");
		
		EditAccountService eas = new EditAccountService();
		
		UserInfo ui = new UserInfo(userId, password);
		ui.setName(name);
		ui.setAccountType(Integer.parseInt(accountType));
		ui.setBuyAddress(buyAddress);
		ui.setSaleAddress(saleAddress);
		boolean result = eas.EditUserDo(ui);
		if(result) {
			HttpSession session = req.getSession();
			session.setAttribute("ui",ui);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfoDone.jsp"); 
			rd.forward(req, res);
		}else {
			req.setAttribute("EditError", "編集後の情報を更新できませんでした。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfoConfirm.jsp");
			rd.forward(req, res);
		}
	}
}
