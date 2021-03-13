package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	
	private List<UserAccount> userAccounts;
	
	public Classroom(){
		setUserAcounts(new ArrayList<UserAccount>());
	}

	public List<UserAccount> getUserAcounts() {
		return userAccounts;
	}

	public void setUserAcounts(List<UserAccount> userAcounts) {
		this.userAccounts = userAcounts;
	}	
	
	public void addUserAccount(String username, String password, String profileImage,int genderIndex,String career, String birthday, int browserIndex) {
	
		this.userAccounts.add(new UserAccount(username, password, profileImage, genderIndex, career, birthday, browserIndex));
	
	}
	
}
