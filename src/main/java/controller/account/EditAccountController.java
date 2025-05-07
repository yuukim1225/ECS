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
import service.validation.UserInfoValidationCheckService;

/**
 * Servlet implementation class EditAccountController
 */
@WebServlet("/editAccount")
public class EditAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfo.jsp"); 
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
//		boolean result;
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String accountType = req.getParameter("accountType");
		String buyAddress = req.getParameter("buyAddress");
		String saleAddress = req.getParameter("saleAddress");
		
		UserInfoValidationCheckService uivcs = new UserInfoValidationCheckService();
		uivcs.isBlank("お名前", name);
		uivcs.isBlank("パスワード", password);
		uivcs.isBlank("送付先住所", buyAddress);
		uivcs.isBlank("配送元住所", saleAddress);
		uivcs.length("お名前", name, 1, 10);
		uivcs.length("パスワード", password, 4, 8);
		
//		EditAccountService eas = new EditAccountService();
		if(uivcs.hasErrorMsg()) {
			req.setAttribute("inputError", uivcs.getErrorMsgList());
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfo.jsp"); 
			rd.forward(req, res);
		}else {
			UserInfo ui = new UserInfo(userId, password);
			ui.setName(name);
			ui.setAccountType(Integer.parseInt(accountType));
			ui.setBuyAddress(buyAddress);
			ui.setSaleAddress(saleAddress);
//			result = eas.EditUserDo(ui);
//			if(result) {
				HttpSession session = req.getSession();
				session.setAttribute("ui",ui);
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfoConfirm.jsp"); 
				rd.forward(req, res);
//			}else {
//				uivcs.addErrorMsg("会員情報を変更できませんでした。");
//				req.setAttribute("inputError", uivcs.getErrorMsgList());
//				RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/edit/editUserInfo.jsp"); 
//				rd.forward(req, res);
//			}
		}
	}

}
