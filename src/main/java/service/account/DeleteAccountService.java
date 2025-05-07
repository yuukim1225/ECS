package service.account;

import dao.user.UserDAO;
import domain.UserInfo;
import dto.user.UserDTO;

public class DeleteAccountService {
	public boolean userDeleteDo(UserInfo ui) {
		int result;
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(ui.getUserId(), ui.getPassword());
		result = userDAO.deleteUserInfo(userDTO);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
