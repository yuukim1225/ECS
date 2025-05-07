package controller.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.SaleItemInfo;
import domain.UserInfo;
import service.deal.DealProcess;
import service.validation.ProductInfoValidationCheckService;


@WebServlet("/addProduct")
@MultipartConfig
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/addProduct.jsp");
		rd.forward(req, res);	

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String productName = req.getParameter("productName");
		String strProductPrice = req.getParameter("productPrice");
		String userId = req.getParameter("userId");
		String productCategory = req.getParameter("productCategory");
		String productText = req.getParameter("productText");
		//String productImg = req.getParameter("productImg");
		
		ProductInfoValidationCheckService pivcs = new ProductInfoValidationCheckService();
		pivcs.isBlank("商品名" ,productName);
		pivcs.isBlank("価格[P]", strProductPrice);
		pivcs.isBlank("出品者", userId);
		pivcs.isBlank("商品カテゴリ", productCategory);
		pivcs.length("商品名",productName, 30);
		pivcs.length("商品カテゴリ", productCategory, 10);
		
		if(pivcs.hasErrorMsg()) {
			req.setAttribute("inputError", pivcs.getErrorMsgList());
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/addProduct.jsp"); 
			rd.forward(req, res);
		}else {
			int productPrice = Integer.parseInt(req.getParameter("productPrice"));
			SaleItemInfo sii = new SaleItemInfo();
			sii.setItemSaleName(productName);
			sii.setItemSalePrice(productPrice);
			sii.setUserId(userId);
			sii.setItemSaleCategory(productCategory);
			sii.setItemSaleText(productText);
			
			UserInfo sui = new UserInfo();
			DealProcess dp = new DealProcess();
			sui = dp.referSaleUserData(userId);
			
			req.setAttribute("sii",sii);
			req.setAttribute("sui",sui);
			///////////////////////////////////////
			
			InputStream inputStream = req.getPart("productImg").getInputStream();
	        byte[] bytes = inputStream.readAllBytes();
	        inputStream.close();

	        // Base64エンコード
	        String base64Image = Base64.getEncoder().encodeToString(bytes);
	        req.setAttribute("imageData", base64Image);
			
			///////////////////////////////////////
			//req.setAttribute("productImg", req.getPart("productImg"));
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/addProductConfirm.jsp"); 
			rd.forward(req, res);
		}
	}
}
