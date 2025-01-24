package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GidaiItemModel;
import model.PickUpModel;

public class PickUpDAO {
	/**
	 * Gidaiアイテムを１件追加
	 * 
	 * @param connection
	 * @param model　PickUpModel
	 * @return 結果（true:成功、false:失敗）
	 */
	public boolean create(Connection connection, PickUpModel model) {
		try {
			String sql = "INSERT INTO pickUp("
					+ "gidai_id,"
					+ "user_id "
					+ ") VALUES ("
					+ "?,"
					+ "?"
					+ ") ";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, model.getGidaiId());
				stmt.setInt(2, model.getPickUpUser());

				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * PickUpテーブル内のGidaiアイテムを全件取得
	 * 
	 * @param connection
	 * @param userId
	 * @return GidaiItemModelのArrayList
	 */
	public List<GidaiItemModel> findPickUpList(Connection connection, int userId) {
		List<GidaiItemModel> list = new ArrayList<GidaiItemModel>();
		try {
			String sql = "SELECT "
					+ "gidai.id,gidai.user_id,users.name,registration_date,gidai_name,"
					+ "genre,gidai.created_at,gidai.updated_at,"
					//jsp側でボタン表示の条件分岐に使用するため、pickUpテーブル内のユーザーIdをpickUp_userとする
					+ "pickUp.user_id AS pickUp_user "
					+ "FROM gidai "
					+ "JOIN users "
					+ "ON gidai.user_id = users.id "
					+ "LEFT OUTER JOIN pickUp "
					+ "ON pickUp.gidai_id = gidai.id "
					+ "where  pickUp.user_id=? "
					+ "ORDER BY registration_date";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {

				stmt.setInt(1, userId);

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						GidaiItemModel model1 = new GidaiItemModel();

						model1.setId(rs.getInt("id"));
						model1.setUserId(rs.getInt("user_id"));
						model1.setUserName(rs.getString("name"));
						model1.setRegistrationDate(rs.getDate("registration_date"));
						model1.setGidaiName(rs.getString("gidai_name"));
						model1.setGenre(rs.getInt("genre"));
						model1.setCreatedAt(rs.getTimestamp("created_at"));
						model1.setUpdatedAt(rs.getTimestamp("updated_at"));
						//jsp側でボタン表示の条件分岐に使用する
						model1.setPickUp(rs.getInt("pickUp_user"));

						list.add(model1);
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
	 * pickUpMを１件削除
	 * 
	 * @param connection
	 * @param model PickUpModel
	 * @return 結果（true:成功、false:失敗）
	 */
	public boolean delete(Connection connection,PickUpModel model) {
		
		try {
			String sql = "DELETE FROM pickUp WHERE gidai_id=? AND user_id=? ";
			
			try (PreparedStatement stmt = connection.prepareStatement(sql)){
				stmt.setInt(1,model.getGidaiId());
				stmt.setInt(2,model.getPickUpUser());
				
				stmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
