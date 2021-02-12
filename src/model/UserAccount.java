package model;

import javafx.scene.image.Image;

public class UserAccount {
	private String username;
	private String password;
	private Image profileImage;
	private String gender;
	private String career;
	private String birthday;
	private String favoriteBrowser;
	
	public UserAccount(String username, String password, Image profileImage,int genderIndex, int careerIndex, String birthday, int browserIndex) {
		this.username = username;
		this.password = password;
		this.profileImage = profileImage;
		this.gender = String.valueOf(Gender.values()[genderIndex]);
		this.career = String.valueOf(Career.values()[careerIndex]);
		this.birthday = birthday;
		this.favoriteBrowser = String.valueOf(Browser.values()[browserIndex]);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Image getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Image profileImage) {
		this.profileImage = profileImage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFavoriteBrouser() {
		return favoriteBrowser;
	}

	public void setFavoriteBrouser(String favoriteBrouser) {
		this.favoriteBrowser = favoriteBrouser;
	}
	
}
