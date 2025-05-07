package dao.saleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;
import dao.TransactionManager;
import dao.user.UserDAO;
import dto.saleItem.SaleItemDTO;
import dto.user.UserDTO;

public class SaleItemDAO extends BaseDAO{
	
	public int insertSaleItemInfo(SaleItemDTO saleItemDTO) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO SALE_INFO(ITEM_SALE_NAME, ITEM_SALE_PRICE, USER_ID, "
					+ "ITEM_SALE_CATEGORY, ITEM_SALE_TEXT, ITEM_SALE_IMG) "
					+ "VALUES(?, ?, ?, ?, ?, ?)");
			ps.setString(1, saleItemDTO.getItemSaleName());
			ps.setInt(2, saleItemDTO.getItemSalePrice());
			ps.setString(3, saleItemDTO.getUserId());
			ps.setString(4, saleItemDTO.getItemSaleCategory());
			ps.setString(5, saleItemDTO.getItemSaleText());
			ps.setString(6, saleItemDTO.getItemSaleImg());
			
			result = ps.executeUpdate();
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public SaleItemDTO getSaleItemInfo(int itemSaleId){
		SaleItemDTO saleItemDTO = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM SALE_INFO WHERE ITEM_SALE_ID = ?");
			ps.setInt(1, itemSaleId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				saleItemDTO = new SaleItemDTO();
				
				saleItemDTO.setItemSaleId(rs.getInt(1));
				saleItemDTO.setItemSaleName(rs.getString(2));
				saleItemDTO.setItemSalePrice(rs.getInt(3));
				saleItemDTO.setUserId(rs.getString(4));
				saleItemDTO.setBuyUserId(rs.getString(5));
				saleItemDTO.setItemSaleCategory(rs.getString(6));
				saleItemDTO.setItemSaleText(rs.getString(7));
				saleItemDTO.setItemSaleDate(rs.getTimestamp(8));
				saleItemDTO.setItemSaleImg(rs.getString(9));
				saleItemDTO.setItemSaleState(rs.getInt(10));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return saleItemDTO;
	}
	
	public List<Integer> IdSaleItemsAll() {
		List<Integer> idSaleItemsAll = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO");
			ResultSet rs = ps.executeQuery();
			idSaleItemsAll = new ArrayList<Integer>();
			while(rs.next()) {
				idSaleItemsAll.add(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idSaleItemsAll;
	}	
	
	public List<Integer> IdSaleItemsUser(String userId) {
		List<Integer> idSaleItemsUser = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE USER_ID = ?");
			ps1.setString(1, userId);
			ResultSet rs1 = ps1.executeQuery();
			idSaleItemsUser = new ArrayList<Integer>();
			while(rs1.next()) {
				idSaleItemsUser.add(rs1.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idSaleItemsUser;
	}

	public List<Integer> IdSaleItemsDeal(String userId) {
		List<Integer> idSaleItemsDeal = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps1 = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE BUY_USER_ID = ?");
			ps1.setString(1, userId);
			ResultSet rs1 = ps1.executeQuery();
			idSaleItemsDeal = new ArrayList<Integer>();
			while(rs1.next()) {
				idSaleItemsDeal.add(rs1.getInt(1));
			}
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE USER_ID = ? AND BUY_USER_ID IS NOT NULL");
			ps2.setString(1, userId);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				idSaleItemsDeal.add(rs2.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idSaleItemsDeal;
	}
	
	public int updateItemSaleStatus(SaleItemDTO sida, int updateDealStatus) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE SALE_INFO SET ITEM_SALE_STATES = ? WHERE ITEM_SALE_ID = ?");
			ps.setInt(1, updateDealStatus);
			ps.setInt(2, sida.getItemSaleId());
			result = ps.executeUpdate();
			
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}

	public int updateItemSaleStatus(SaleItemDTO sida, String buyUserId) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		/*購入者、出品者のDTOを読みだす*/
		UserDAO ua = new UserDAO(); 
		UserDTO saleUdt = ua.getUserInfo(sida.getUserId());
		UserDTO buyUdt = ua.getUserInfo(buyUserId);
		
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			/*商品のステータス更新*/
			PreparedStatement ps1 = conn.prepareStatement("UPDATE SALE_INFO SET ITEM_SALE_STATES = ? ,BUY_USER_ID = ? WHERE ITEM_SALE_ID = ?");
			ps1.setInt(1, 1);
			ps1.setString(2, buyUserId);
			ps1.setInt(3, sida.getItemSaleId());
			result1 = ps1.executeUpdate();
			
			/*商品購入後、購入金額を差し引いた購入者の金額に更新*/
			PreparedStatement ps2 = conn.prepareStatement("UPDATE ACCOUNT_INFO SET HOLD_POINT = ? WHERE USER_ID = ?");
			ps2.setInt(1, (buyUdt.getHoldPoint() - sida.getItemSalePrice()));
			ps2.setString(2, buyUserId);
			result2 = ps2.executeUpdate();
			
			/*商品購入後、購入金額を足した出品者の金額に更新*/
			PreparedStatement ps3 = conn.prepareStatement("UPDATE ACCOUNT_INFO SET HOLD_POINT = ? WHERE USER_ID = ?");
			ps3.setInt(1, (saleUdt.getHoldPoint() + sida.getItemSalePrice()));
			ps3.setString(2, saleUdt.getUserId());
			result3 = ps3.executeUpdate();
			
			if(result1 == 1 && result2 == 1 && result3 == 1) {
				result = 1;
			}
			
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public int updateItemSaleStatusInDealEnd(SaleItemDTO sida, int goodPBadP) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps1 = conn.prepareStatement("UPDATE SALE_INFO SET ITEM_SALE_STATES = ? WHERE ITEM_SALE_ID = ?");
			ps1.setInt(1, 5);
			ps1.setInt(2, sida.getItemSaleId());
			result1 = ps1.executeUpdate();
			
			UserDAO ua = new UserDAO(); 
			UserDTO saleUdt = ua.getUserInfo(sida.getUserId());
			UserDTO buyUdt = ua.getUserInfo(sida.getBuyUserId());
			
			PreparedStatement ps2 = conn.prepareStatement("UPDATE ACCOUNT_INFO SET TRADE_NUM = ? ,GOOD_NUM = ?, BAD_NUM = ? WHERE USER_ID = ?");
			ps2.setInt(1, saleUdt.getTradeNum() + 1);
			if(goodPBadP == 1) {
				ps2.setInt(2, saleUdt.getGoodNum() + 1);
				ps2.setInt(3, saleUdt.getBadNum());
			}else {
				ps2.setInt(2, saleUdt.getGoodNum());
				ps2.setInt(3, saleUdt.getBadNum() + 1);
			}
			ps2.setString(4, saleUdt.getUserId());
			result2 = ps2.executeUpdate();
			
			PreparedStatement ps3 = conn.prepareStatement("UPDATE ACCOUNT_INFO SET TRADE_NUM = ? WHERE USER_ID = ?");
			ps3.setInt(1, buyUdt.getTradeNum() + 1);
			ps3.setString(2, buyUdt.getUserId());
			result3 = ps3.executeUpdate();
			
			if(result1 == 1 && result2 == 1 && result3 == 1) {
				result = 1;
			}
			
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public List<Integer> IdSaleItemsSearch(int SearchColumn, String searchWord) {
		List<Integer> idSaleItemsSearch = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = null;
			ResultSet  rs =null;
			switch(SearchColumn) {
				case 0/*商品名から検索*/:
					ps = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE ITEM_SALE_NAME LIKE ?");
					ps.setString(1, searchWord);
					rs = ps.executeQuery();
					idSaleItemsSearch = new ArrayList<Integer>();
					while(rs.next()) {
						idSaleItemsSearch.add(rs.getInt(1));
					}
					break;
				case 1/*商品カテゴリーから検索*/:
					ps = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE ITEM_SALE_CATEGORY LIKE ?");
					ps.setString(1, searchWord);
					rs = ps.executeQuery();
					idSaleItemsSearch = new ArrayList<Integer>();
					while(rs.next()) {
						idSaleItemsSearch.add(rs.getInt(1));
					}
					break;
				case 2/*出品者から検索*/:
					ps = conn.prepareStatement("SELECT USER_ID FROM ACCOUNT_INFO WHERE NAME LIKE ?");
					ps.setString(1, searchWord);
					rs = ps.executeQuery();
					List<String> saleUserIdList = new ArrayList<String>();
					while(rs.next()) {
						saleUserIdList.add(rs.getString(1));
					}
					String sqlStr ="SELECT ITEM_SALE_ID FROM SALE_INFO WHERE USER_ID ";
					int cnt=0;
					while(cnt < saleUserIdList.size()) {
						if(cnt!= 0) {
							sqlStr = sqlStr + "OR USER_ID";
						}
						String str = saleUserIdList.get(cnt);
						sqlStr = sqlStr + "='" + str +"'";
						cnt++;
					}
					PreparedStatement ps2 = conn.prepareStatement(sqlStr);
					rs = ps2.executeQuery();
					idSaleItemsSearch = new ArrayList<Integer>();
					while(rs.next()) {
						idSaleItemsSearch.add(rs.getInt(1));
					}
					break;
				default/*上記以外の値を受けた場合はcase0を選択*/:
					ps = conn.prepareStatement("SELECT ITEM_SALE_ID FROM SALE_INFO WHERE ITEM_SALE_NAME LIKE ?");
					ps.setString(1, searchWord);
					rs = ps.executeQuery();
					idSaleItemsSearch = new ArrayList<Integer>();
					while(rs.next()) {
						idSaleItemsSearch.add(rs.getInt(1));
					}
					break;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idSaleItemsSearch;
	}	


}
