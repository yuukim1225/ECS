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
import service.account.DeleteAccountService;

/**
 * Servlet implementation class DeleteAccountConfilmController
 */
@WebServlet("/deleteAccount")
public class DeleteAccountConfilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/delete/deleteAccountConfirm.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		boolean result;
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		UserInfo ui = new UserInfo(userId, password);
		DeleteAccountService das = new DeleteAccountService();
		result = das.userDeleteDo(ui);
		
		if(result) {
			HttpSession session = req.getSession();
			session.invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/delete/deleteAccountDone.jsp");
			rd.forward(req, res);
		}else {
			req.setAttribute("deleteError", "登録情報の削除に失敗しました。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/delete/deleteAccountConfirm.jsp");
			rd.forward(req, res);
		}
	}

}
