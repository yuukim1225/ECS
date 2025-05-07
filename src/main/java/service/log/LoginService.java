package service.log;

import dao.user.UserDAO;
import domain.UserInfo;
import dto.user.UserDTO;

public class LoginService {
	public UserInfo LoginCheck(String userId, String password) {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.getUserInfo(userId);
		if(userDTO != null && password.equals(userDTO.getPassword())) {
			UserInfo userInfo = new UserInfo(userDTO.getUserId(), userDTO.getPassword());
			userInfo.setName(userDTO.getName());
			userInfo.setRegistDate(userDTO.getRegistDate());
			userInfo.setAccountType(userDTO.getAccountType());
			userInfo.setTradeNum(userDTO.getTradeNum());
			userInfo.setGoodNum(userDTO.getGoodNum());
			userInfo.setBadNum(userDTO.getBadNum());
			userInfo.setHoldPoint(userDTO.getHoldPoint());
			userInfo.setBuyAddress(userDTO.getBuyAddress());
			userInfo.setSaleAddress(userDTO.getSaleAddress());
			
			return userInfo;
		}
		return null;
	}

}
