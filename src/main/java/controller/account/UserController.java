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
import service.log.LoginService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserInfo ui = new UserInfo();
		String userId;
		String password;
		
		HttpSession session = req.getSession();
		ui = (UserInfo)session.getAttribute("ui");
		userId = ui.getUserId();
		password = ui.getPassword();
		
		LoginService ls = new LoginService();
		ui = ls.LoginCheck(userId, password);
		session.setAttribute("ui",ui);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/user.jsp");
		rd.forward(req, res);
	}

}
