package model;

public class FavoriteItemModel {
	private int commentId;
	private int userId;
	private int favoriteCount;
	
	public int getCommentId() {
		return this.commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getFavoriteCout() {
		return this.favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
}
