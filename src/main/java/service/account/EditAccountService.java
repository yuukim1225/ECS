package service.account;

import dao.user.UserDAO;
import domain.UserInfo;
import dto.user.UserDTO;

public class EditAccountService {
	public boolean EntryUserConfilm(UserInfo userInfo) {
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.getUserInfo(userInfo.getUserId());
		
		if(userDTO == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean EntryUserDo(UserInfo userInfo) {
		UserDAO userDAO = new UserDAO();
		int result;
		
		UserDTO userDTO = new UserDTO(userInfo.getUserId(), userInfo.getPassword());
		userDTO.setName(userInfo.getName());
		userDTO.setAccountType(userInfo.getAccountType());
		userDTO.setBuyAddress(userInfo.getBuyAddress());
		userDTO.setSaleAddress(userInfo.getSaleAddress());
		
		result = userDAO.insertUserInfo(userDTO);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean EditUserDo(UserInfo userInfo) {
		UserDAO userDAO = new UserDAO();
		int result;
		
		UserDTO userDTO = new UserDTO(userInfo.getUserId(), userInfo.getPassword());
		userDTO.setName(userInfo.getName());
		userDTO.setAccountType(userInfo.getAccountType());
		userDTO.setBuyAddress(userInfo.getBuyAddress());
		userDTO.setSaleAddress(userInfo.getSaleAddress());
		
		result = userDAO.editUserInfo(userDTO);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean AddPayment(UserInfo userInfo, int addPayNum) {
		UserDAO userDAO = new UserDAO();
		int result = 0;
		
		UserDTO userDTO = new UserDTO(userInfo.getUserId(), userInfo.getPassword());
		userDTO.setHoldPoint(userInfo.getHoldPoint());
		result = userDAO.addPayment(userDTO, addPayNum);
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}
}
