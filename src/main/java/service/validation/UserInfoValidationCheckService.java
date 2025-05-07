package service.validation;

import java.util.ArrayList;
import java.util.List;

public class UserInfoValidationCheckService {
	
	private List<String> errorMsgList;
	
	public UserInfoValidationCheckService() {
		this.errorMsgList = new ArrayList<>();
	}
	
	public boolean hasErrorMsg() {
		if(this.errorMsgList.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	public void isBlank(String textName, String text) {
		if(text == null || text.isEmpty()) {
			this.errorMsgList.add(textName + "が入力されていません");
		}
	}
	public void length(String textName, String text, int min, int max) {
		if(text == null || text.length() < min || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + min + "文字以上、" + max + "文字以下で入力してください");
		}
	}
	
	public void length(String textName, String text, int max) {
		if(text == null || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + max + "文字以内で入力してください");			
		}
	}
	
	public void addErrorMsg(String msg) {
		errorMsgList.add(msg);
	}
	
	public List<String>getErrorMsgList(){
		return errorMsgList;
	}

}
