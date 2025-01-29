package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GironnItemModel implements Serializable {
	private int id;
	private int userId;
	private String userName;
	private int gidaiId;
	private String gidaiName;
	private Date registrationDate;
	private String comment;
	private int isDeleted;
	private int favorite;
	private int favoriteCount;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private List<ReplyItemModel> replyItems = new ArrayList<ReplyItemModel>();

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

	public int getGidaiId() {
		return this.gidaiId;
	}

	public void setGidaiId(int gidaiId) {
		this.gidaiId = gidaiId;
	}

	public String getGidaiName() {
		return this.gidaiName;
	}

	public void setGidaiName(String gidaiName) {
		this.gidaiName = gidaiName;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getFavorite() {
		return this.favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getFavoriteCount() {
		return this.favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
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

	public List<ReplyItemModel> getReplyItems() {
		return this.replyItems;
	}

	public void setReplyItems(List<ReplyItemModel> replyItems) {
		this.replyItems = replyItems;
	}
}
