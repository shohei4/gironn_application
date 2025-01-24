package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GironnItemModel;
import model.ReplyItemModel;

public class GironnItemDAO {
	/**
	 * gionnアイテムを全件取得
	 * 
	 * @param connection
	 * @param gidaiId
	 * @param userId
	 * @return GironnItemModelのArrayList
	 */
	public List<GironnItemModel> findAll(Connection connection,int gidaiId,int userId){
		List<GironnItemModel> list = new ArrayList<GironnItemModel>();
		try {
			String sql = "SELECT gironn.id,"
					+ "gironn.user_id,"
					+ "users.name,"
					+ "gidai_id,"
					+ "gironn.registration_date,"
					+ "gironn.comment,"
					//jsp側のボタン表示の条件分岐に使用する、favoriteテーブル内のユーザーIdの数をfavoriteとする
					+ "(SELECT COUNT(user_id) FROM favorite where comment_id=gironn.id AND user_id=?) AS favorite,"
					+ "gironn.is_deleted,"
					+ "gironn.created_at,"
					+ "gironn.updated_at,"
					//いいね数をfavoriteCountとする
					+ "(SELECT COUNT(*) FROM favorite WHERE comment_id=gironn.id) AS favoriteCount "
					+ "FROM gironn "
					+ "JOIN gidai ON gironn.gidai_id = gidai.id "
					+ "LEFT JOIN users ON gironn.user_id = users.id "
					+ "WHERE gironn.gidai_id=? "
					+ "AND gironn.is_deleted=0 "
					+ "ORDER BY registration_date";
			try(PreparedStatement stmt = connection.prepareStatement(sql)){
				stmt.setInt(1, userId);
				stmt.setInt(2,gidaiId);
				
				try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					GironnItemModel model = new GironnItemModel();
					model.setId(rs.getInt("id"));
					model.setUserId(rs.getInt("user_id"));
					model.setUserName(rs.getString("name"));
					model.setGidaiId(rs.getInt("gidai_id"));
					model.setRegistrationDate(rs.getDate("registration_date"));
					model.setComment(rs.getString("comment"));
					model.setFavorite(rs.getInt("favorite"));
					model.setIsDeleted(rs.getInt("is_deleted"));
					model.setFavoriteCount(rs.getInt("favoriteCount"));
					model.setCreatedAt(rs.getTimestamp("created_at"));
					model.setUpdatedAt(rs.getTimestamp("updated_at"));
					
					
					List<ReplyItemModel> replyItems = new ArrayList<ReplyItemModel>();
					ReplyDAO rdao = new ReplyDAO();
					
					
					replyItems = rdao.findAll(connection,rs.getInt("id"));
					model.setReplyItems(replyItems);
					
					list.add(model);
				}
				}
			
			}
		}catch(SQLException e){
			e.printStackTrace();
			
			return null;
		}
		return list;
	}
	
	/**
	 * Gironnアイテムをキーワードで検索
	 * 
	 * @param connection
	 * @param keyWord
	 * @param gidaiId
	 * @param userId
	 * @return GironnItemModelのArrayList
	 */
	public List<GironnItemModel> findByKeyWord(Connection connection,String keyWord,int gidaiId,int userId){
		List<GironnItemModel> list = new ArrayList<GironnItemModel>();
		try {
			String sql = "SELECT gironn.id,"
					+ "gironn.user_id,"
					+ "users.name,"
					+ "gidai_id,"
					+ "gironn.registration_date,"
					+ "comment,"
					//jsp側のボタン表示の条件分岐に使用する、favoriteテーブル内のユーザーIdの数をfavoriteとする
					+ "(SELECT COUNT(user_id) FROM favorite where comment_id=gironn.id AND user_id=?) AS favorite,"
					+ "gironn.is_deleted,"
					+ "gironn.created_at,"
					+ "gironn.updated_at,"
					//いいね数をfavoriteCountとする
					+ "(SELECT COUNT(*) FROM favorite WHERE comment_id=gironn.id) AS favoriteCount "
					+ "FROM gironn "
					+ "JOIN gidai ON gironn.gidai_id = gidai.id "
					+ "LEFT JOIN users ON gironn.user_id = users.id "
					+ "WHERE gironn.gidai_id=? "
					+ "AND gironn.is_deleted=0 "
					+ "AND gironn.comment LIKE ? "
					+ "ORDER BY registration_date";
			try(PreparedStatement stmt = connection.prepareStatement(sql)){
				stmt.setInt(1,userId);
				stmt.setInt(2,gidaiId);
				stmt.setString(3,"%" + keyWord + "%");
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						GironnItemModel model = new GironnItemModel();
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setUserName(rs.getString("name"));
						model.setGidaiId(rs.getInt("gidai_id"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setComment(rs.getString("comment"));
						model.setFavorite(rs.getInt("favorite"));
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setFavoriteCount(rs.getInt("favoriteCount"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));
						

						List<ReplyItemModel> replyItems = new ArrayList<ReplyItemModel>();
						ReplyDAO rdao = new ReplyDAO();
						
						
						replyItems = rdao.findAll(connection,rs.getInt("id"));
						model.setReplyItems(replyItems);
						
						list.add(model);
						
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		return list;
	}
	

	/**
	 * gironnアイテムを１件追加
	 * 
	 * @param connection
	 * @param model GironnItemModel
	 * @return 結果（true:成功、false:失敗）
	 */
		public boolean create(Connection connection , GironnItemModel model) {
			try {
				String sql= "INSERT INTO gironn("
						+ "user_id,"
						+ "registration_date,"
						+ "gidai_id,"
						+ "comment"
						+ ") VALUES ("
						+ "?,"
						+ "?,"
						+ "?,"
						+ "?"
						+ ") ";
				try (PreparedStatement stmt = connection.prepareStatement(sql)){
					stmt.setInt(1, model.getUserId());
					stmt.setDate(2, model.getRegistrationDate());
					stmt.setInt(3, model.getGidaiId());
					stmt.setString(4, model.getComment());
					

					stmt.executeUpdate();
				}
						
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		/**
		 * GironnItemを１件更新
		 * 
		 * @param connection
		 * @param model　GironnItemModel
		 * @param id コメントid
		 * @return 結果（true:成功、false:失敗）
		 */
		public boolean update(Connection connection , GironnItemModel model,int id) {
			
			try{
				String sql = "UPDATE gironn SET "
							+ "comment=?,"
							+ "is_deleted=? "
							+ "where id=?"
							;
				try (PreparedStatement stmt = connection.prepareStatement(sql)) {
					stmt.setString(1,model.getComment());
					stmt.setInt(2,model.getIsDeleted());
					stmt.setInt(3, id);
					stmt.executeUpdate();
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * GironnItemを１件削除
		 * 
		 * @param connection
		 * @param id　コメントid
		 * @param isDeleted
		 * @return 結果（true:成功、false:失敗）
		 */
		public boolean delete(Connection connection ,int id,int isDeleted) {
			try {
				String sql = "UPDATE gironn SET "
						+ "is_deleted=? "
						+ "where id=?"
						;
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1,isDeleted);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
		
}
