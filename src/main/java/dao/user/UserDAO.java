package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BaseDAO;
import dao.TransactionManager;
import dto.user.UserDTO;

public class UserDAO extends BaseDAO{
	/*
	 DBからユーザーIDをもとに該当するカラム情報を全て読み出し、UserDTOオブジェクトを戻り値とするメソッド。
	*/
	public UserDTO getUserInfo(String userId){
		UserDTO userDTO = null;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ACCOUNT_INFO WHERE USER_ID = ?");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				userDTO = new UserDTO();
				
				userDTO.setId(rs.getInt(1));
				userDTO.setUserId(rs.getString(2));
				userDTO.setName(rs.getString(3));
				userDTO.setPassword(rs.getString(4));
				userDTO.setRegistDate(rs.getTimestamp(5));
				userDTO.setAccountType(rs.getInt(6));
				userDTO.setTradeNum(rs.getInt(7));
				userDTO.setGoodNum(rs.getInt(8));
				userDTO.setBadNum(rs.getInt(9));
				userDTO.setHoldPoint(rs.getInt(10));
				userDTO.setBuyAddress(rs.getString(11));
				userDTO.setSaleAddress(rs.getString(12));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return userDTO;
	}
	/* 新規のユーザ情報をDBへInsertするメソッド */
	public int insertUserInfo(UserDTO userDTO) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ACCOUNT_INFO(USER_ID, NAME, PASSWORD, "
					+ "ACCOUNT_TYPE, BUY_ADDRESS, SALE_ADDRESS) "
					+ "VALUES(?, ?, ?, ?, ?, ?)");
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getName());
			ps.setString(3, userDTO.getPassword());
			ps.setInt(4, userDTO.getAccountType());
			ps.setString(5, userDTO.getBuyAddress());
			ps.setString(6, userDTO.getSaleAddress());
			
			result = ps.executeUpdate();
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public int deleteUserInfo(UserDTO userDTO) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ACCOUNT_INFO WHERE user_Id = ?");
			ps.setString(1, userDTO.getUserId());
			result = ps.executeUpdate();
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public int editUserInfo(UserDTO userDTO) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ACCOUNT_INFO SET NAME = ?, PASSWORD = ?, ACCOUNT_TYPE = ?, BUY_ADDRESS =?, SALE_ADDRESS = ? WHERE USER_ID = ?");
			ps.setString(1, userDTO.getName());
			ps.setString(2, userDTO.getPassword());
			ps.setInt(3, userDTO.getAccountType());
			ps.setString(4, userDTO.getBuyAddress());
			ps.setString(5, userDTO.getSaleAddress());
			ps.setString(6, userDTO.getUserId());
			
			result = ps.executeUpdate();
			tm.commit();
		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	public int addPayment(UserDTO userDTO, int addPayNum) {
		int result = 0;
		Connection conn = getConnection();
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE ACCOUNT_INFO SET HOLD_POINT = ? WHERE USER_ID = ?");
			ps.setInt(1, userDTO.getHoldPoint() + addPayNum);
			ps.setString(2, userDTO.getUserId());
			result = ps.executeUpdate();
			tm.commit();

		}catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
}
