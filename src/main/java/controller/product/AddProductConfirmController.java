package controller.product;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.saleItem.SaleItemDAO;
import domain.SaleItemInfo;
import domain.UserInfo;
import service.deal.DealProcess;
import service.product.AddProductInformation;

@WebServlet("/addProductConfirm")
@MultipartConfig
public class AddProductConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		boolean result;
		
		String productName = req.getParameter("productName");
		int productPrice = Integer.parseInt(req.getParameter("productPrice"));
		String userName = req.getParameter("userName");
		String productCategory = req.getParameter("productCategory");
		String productText = req.getParameter("productText");
		//String productImg = req.getParameter("productImg");
		
		SaleItemInfo sii = new SaleItemInfo();
		sii.setItemSaleName(productName);
		sii.setItemSalePrice(productPrice);
		sii.setUserId(userName);
		sii.setItemSaleCategory(productCategory);
		sii.setItemSaleText(productText);
		
		String base64Image = req.getParameter("productImg");
		
		//////////////////////////////
        if (base64Image != null && !base64Image.isEmpty()) {
            // Base64デコード
        	byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            
         // バイトデータをBufferedImageに変換
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(bis);
            bis.close();        
            SaleItemDAO sida = new SaleItemDAO();
            List<Integer> idSaleItemsAll = sida.IdSaleItemsAll();
            
            AddProductInformation api = new AddProductInformation();
            String dirPath = "/ECS/jsp/img/" + Integer.toString(idSaleItemsAll.size() + 1);
            //String dirPath = "../workspace/ECS/src/main/webapp/jsp/img/" + Integer.toString(idSaleItemsAll.size() + 1);
            String upDirPath = "C:\\Users\\manoy\\OneDrive\\デスクトップ\\DevEnv1\\ECS\\src\\main\\webapp\\jsp\\img\\" + Integer.toString(idSaleItemsAll.size() + 1);
            api.makeDirectory(upDirPath);
            
            dirPath = dirPath + "/image.png";
            File outputFile = new File(upDirPath, "image.png");
            ImageIO.write(bufferedImage, "png", outputFile);
            sii.setItemSaleImg(dirPath);
        } else {
            //res.sendError(HttpServletResponse.SC_BAD_REQUEST, "画像データが見つかりません");
        }
		
		AddProductInformation api = new AddProductInformation();
		result = api.addProductDo(sii);
		if(result) {
			UserInfo sui = new UserInfo();
			DealProcess dp = new DealProcess();
			sui = dp.referSaleUserData(sii.getUserId());
			
			req.setAttribute("sii",sii);
			req.setAttribute("sui",sui);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/addProductDone.jsp");
			rd.forward(req, res);
		}else {
			req.setAttribute("inputError", "商品の出品に失敗しました。");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/product/addProduct.jsp");
			rd.forward(req, res);
		}
	}
}
