package controller.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.UserInfo;
import service.account.EditAccountService;

/**
 * Servlet implementation class RegistAccountConfirmController
 */
@WebServlet("/registerConfirm")
public class RegistAccountConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String accountType = req.getParameter("accountType");
		String buyAddress = req.getParameter("buyAddress");
		String saleAddress = req.getParameter("saleAddress");
		
		UserInfo ui = new UserInfo(userId, password);
		ui.setName(name);
		ui.setAccountType(Integer.parseInt(accountType));
		ui.setBuyAddress(buyAddress);
		ui.setSaleAddress(saleAddress);
		
		EditAccountService eas = new EditAccountService();
		boolean result = eas.EntryUserDo(ui);
		
		if(result) {
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/register/registerDone.jsp");
			rd.forward(req, res);
		}else {
			req.setAttribute("registError", "会員登録に失敗しました。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/register/registerConfirm.jsp");
			rd.forward(req, res);
		}
	}

}
