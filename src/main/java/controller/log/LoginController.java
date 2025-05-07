package controller.log;

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
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request, response);
	}
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String loginId = req.getParameter("userId");
		String password = req.getParameter("password");
		UserInfo ui = new UserInfo();
		LoginService ls = new LoginService();
		
		ui = ls.LoginCheck(loginId, password);
		if(ui != null) {
			HttpSession session = req.getSession();
			session.setAttribute("ui",ui);
			RequestDispatcher rd = req.getRequestDispatcher("/home"); 
			rd.forward(req, res);
		}else {
			req.setAttribute("loginError", "ユーザーID、またはパスワードが間違っています。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(req, res);
		}
	}

}
