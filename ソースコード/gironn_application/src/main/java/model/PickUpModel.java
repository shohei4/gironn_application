package model;

import java.io.Serializable;

public class PickUpModel implements Serializable{
	private int gidaiId;
	private int pickUpUser;
	private String userName;
	
	
	
	public int getGidaiId() {
		return this.gidaiId;
	}
	public void setGidaiId(int gidaiId) {
		this.gidaiId = gidaiId;
	}
	
	public int getPickUpUser() {
		return this.pickUpUser;
	}
	public void setPickUpUser(int pickUpUser) {
		this.pickUpUser = pickUpUser;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
}