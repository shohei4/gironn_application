package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class GidaiItemModel implements Serializable {
	private int id;
	private int userId;
	private String userName;
	private Date registrationDate;
	private String gidaiName;
	private int genre;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int pickUp;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getGidaiName() {
		return this.gidaiName;
	}

	public void setGidaiName(String gidaiName) {
		this.gidaiName = gidaiName;
	}

	public int getGenre() {
		return this.genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPickUp() {
		return this.pickUp;
	}

	public void setPickUp(int pickUp) {
		this.pickUp = pickUp;
	}

}
