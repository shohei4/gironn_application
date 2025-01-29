package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GidaiItemModel;

public class GidaiItemDAO {

	/**
	 * GidaiItemを全件取得
	 * 
	 * @param connection データベースコネクションのインスタンス
	 * @param userId
	 * @param userName
	 * @return GidaiアイテムモデルのArrayList
	 */
	public List<GidaiItemModel> findAll(Connection connection, int userId, String userName) {
		List<GidaiItemModel> list = new ArrayList<GidaiItemModel>();
		try {
			//SQL文の設定
			String sql = "SELECT DISTINCT "
					+ "gidai.id,gidai.user_id,users.name,registration_date,gidai_name,"
					+ "genre,gidai.created_at,gidai.updated_at,"
					+ "(SELECT COUNT(user_id) FROM pickUp WHERE gidai.id=pickUp.gidai_id AND user_id=?) AS pickUp "
					+ "FROM gidai "
					+ "LEFT JOIN pickUp "
					+ "ON pickUp.gidai_id = gidai.id "
					+ "LEFT JOIN users "
					+ "ON gidai.user_id = users.id "
					+ "ORDER BY registration_date";

			//SQLの実行準備
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, userId);

				//SQLを実行する
				try (ResultSet rs = stmt.executeQuery()) {

					//SQLの実行結果をArrayListに格納する
					while (rs.next()) {
						GidaiItemModel model = new GidaiItemModel();

						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setUserName(rs.getString("name"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setGidaiName(rs.getString("gidai_name"));
						model.setGenre(rs.getInt("genre"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));
						model.setPickUp(rs.getInt("pickUp"));

						//ArrayListにレコードを追加する
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
	 * Gidaiアイテムをキーワードで検索
	 * 
	 * @param connection
	 * @param keyWord
	 * @param userId
	 * @param userName
	 * @return GidaiItemのArrayList
	 */
	public List<GidaiItemModel> findByKeyWord(Connection connection, String keyWord, int userId, String userName) {
		List<GidaiItemModel> list = new ArrayList<GidaiItemModel>();

		try {
			String sql = "SELECT "
					+ "gidai.id,gidai.user_id,users.name,registration_date,gidai_name,"
					+ "genre,gidai.created_at,gidai.updated_at,"
					+ "(SELECT COUNT(user_id) FROM pickUp WHERE gidai.id=pickUp.gidai_id AND user_id=?) AS pickUp "
					+ "FROM gidai "
					+ "LEFT JOIN pickUp "
					+ "ON pickUp.gidai_id = gidai.id "
					+ "LEFT JOIN users "
					+ "ON gidai.user_id = users.id "
					+ "WHERE gidai_name LIKE ? "
					+ "ORDER BY registration_date";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, userId);
				stmt.setString(2, "%" + keyWord + "%");

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						GidaiItemModel model = new GidaiItemModel();

						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setGidaiName(rs.getString("gidai_name"));
						model.setGenre(rs.getInt("genre"));
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
	 * GidaiItemをジャンル検索
	 * @param connection
	 * @param genre
	 * @param userId
	 * @param userName
	 * @return GidaiItemModelのArrayList
	 */
	public List<GidaiItemModel> findByGenre(Connection connection, int genre, int userId, String userName) {

		List<GidaiItemModel> list = new ArrayList<GidaiItemModel>();

		try {

			String sql = "SELECT DISTINCT "
					+ "gidai.id,gidai.user_id,users.name,registration_date,gidai_name,"
					+ "genre,gidai.created_at,gidai.updated_at,"
					+ "(SELECT COUNT(user_id) FROM pickUp WHERE gidai.id=pickUp.gidai_id AND user_id=?) AS pickUp "
					+ "FROM gidai "
					+ "LEFT JOIN pickUp "
					+ "ON pickUp.gidai_id = gidai.id "
					+ "LEFT JOIN users "
					+ "ON gidai.user_id = users.id "
					+ "WHERE genre=? "
					+ "ORDER BY registration_date";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, userId);
				stmt.setInt(2, genre);

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {
						GidaiItemModel model = new GidaiItemModel();

						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setGidaiName(rs.getString("gidai_name"));
						model.setGenre(rs.getInt("genre"));
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
	 * GidaiItemをキーワードとジャンルで検索
	 * @param connection
	 * @param keyWord
	 * @param genre
	 * @param userId
	 * @param userName
	 * @return GidaiItemModelのArrayList
	 */
	public List<GidaiItemModel> serch(Connection connection, String keyWord, int genre, int userId, String userName) {

		List<GidaiItemModel> list = new ArrayList<GidaiItemModel>();

		try {

			String sql = "SELECT "
					+ "gidai.id,gidai.user_id,users.name,registration_date,gidai_name,"
					+ "genre,gidai.created_at,gidai.updated_at,"
					+ "(SELECT COUNT(user_id) FROM pickUp WHERE gidai.id=pickUp.gidai_id AND user_id=?) AS pickUp "
					+ "FROM gidai "
					+ "LEFT JOIN pickUp "
					+ "ON pickUp.gidai_id = gidai.id "
					+ "LEFT JOIN users "
					+ "ON gidai.user_id = users.id "
					+ "WHERE gidai_name LIKE ? "
					+ "AND genre=? "
					+ "ORDER BY registration_date";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {

				stmt.setInt(1, userId);
				stmt.setString(2, "%" + keyWord + "%");
				stmt.setInt(3, genre);

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {
						GidaiItemModel model = new GidaiItemModel();

						model.setId(rs.getInt("id"));
						model.setUserId(rs.getInt("user_id"));
						model.setRegistrationDate(rs.getDate("registration_date"));
						model.setGidaiName(rs.getString("gidai_name"));
						model.setGenre(rs.getInt("genre"));
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
	 * Gidiaアイテムを1件追加
	 * 
	 * @param connection
	 * @param model　GidaiItemModel
	 * @return 
	 */
	public boolean create(Connection connection, GidaiItemModel model) {
		try {
			String sql = "INSERT INTO gidai("
					+ "user_id,"
					+ "registration_date,"
					+ "gidai_name,"
					+ "genre "
					+ ") VALUES ("
					+ "?,"
					+ "?,"
					+ "?,"
					+ "? "
					+ ")";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, model.getUserId());
				stmt.setDate(2, model.getRegistrationDate());
				stmt.setString(3, model.getGidaiName());
				stmt.setInt(4, model.getGenre());

				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param connection
	 * @param id 議題Id
	 * @return 結果（true:成功、false:失敗）
	 */
	public boolean delete(Connection connection, int id) {
		try {
			String sql = "delete FROM gidai "
					+ "WHERE id = ?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, id);

				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

}