package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ReplyItemModel;

public class ReplyDAO {
	/**
	 * ReplyItemを全件取得
	 * 
	 * @param connection
	 * @param commentId
	 * @return　ReplyItemのArrayList
	 */
	public List<ReplyItemModel> findAll(Connection connection, int commentId) {
		List<ReplyItemModel> list = new ArrayList<ReplyItemModel>();
		try {
			String sql = "SELECT reply.id,"
					+ "reply.user_id,"
					+ "users.name,"
					+ "comment_id,"
					+ "reply.registration_date,"
					+ "reply.comment,"
					+ "reply.is_deleted,"
					+ "reply.created_at,"
					+ "reply.updated_at "
					+ "FROM reply "
					+ "JOIN gironn ON reply.comment_id = gironn.id "
					+ "LEFT JOIN users ON reply.user_id = users.id "
					+ "WHERE reply.comment_id=? "
					+ "AND reply.is_deleted=0 "
					+ "ORDER BY registration_date";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, commentId);

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						ReplyItemModel model = new ReplyItemModel();
						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setUserName(rs.getString("name"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setComment(rs.getString("comment"));
						//favoriteに入れる値はfavoriteテーブルにgironnテーブルと共通のid,userIdがあるときに変化する
						model.setIsDeleted(rs.getInt("is_deleted"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));

						list.add(model);
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
		return list;
	}

	/**
	 * ReplyItemを１件追加
	 * 
	 * 
	 * @param connection
	 * @param model
	 * @return　結果（true:成功、false:失敗）
	 */
	public boolean create(Connection connection, ReplyItemModel model) {
		try {
			String sql = "INSERT INTO reply ("
					+ "user_id,"
					+ "comment_id,"
					+ "registration_date,"
					+ "comment) VALUES ("
					+ "?,"
					+ "?,"
					+ "?,"
					+ "? "
					+ ")";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, model.getUserId());
				stmt.setInt(2, model.getCommentId());
				stmt.setDate(3, model.getRegistrationDate());
				stmt.setString(4, model.getComment());

				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * ReplyItemを１件更新
	 * 
	 * @param connection
	 * @param model
	 * @param id　コメントid
	 * @return 結果（true:成功、false:失敗）
	 */
	public boolean update(Connection connection, ReplyItemModel model, int id) {

		try {
			String sql = "UPDATE reply SET "
					+ "comment=?,"
					+ "is_deleted=? "
					+ "where id=?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, model.getComment());
				stmt.setInt(2, model.getIsDeleted());
				stmt.setInt(3, id);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * ReplyItemを１件削除
	 * 
	 * @param connection
	 * @param id　commentId
	 * @param isDeleted
	 * @return　結果（true:成功、false:失敗）
	 */
	public boolean delete(Connection connection, int id, int isDeleted) {
		try {
			String sql = "UPDATE reply SET "
					+ "is_deleted=? "
					+ "where id=?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, isDeleted);
				stmt.setInt(2, id);

				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
