package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;
import settings.DatabaseSettings;

public class UserDAO {
	/**
	 * ユーザー情報を全件取得
	 * 
	 * @param connection
	 * @return UserModelのArrayList
	 */

	public List<UserModel> findAll(Connection connection) {
		List<UserModel> list = new ArrayList<UserModel>();

		try {
			//SQL文の設定
			String sql = "SELECT * FROM users ORDER BY id";
			//SQLを実行する
			try (PreparedStatement stmt = connection.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					UserModel model = new UserModel();

					model.setId(rs.getInt("id"));
					model.setEmail(rs.getString("email"));
					model.setPassword(rs.getString("password"));
					model.setName(rs.getString("name"));
					model.setCreatedAt(rs.getTimestamp("created_at"));
					model.setUpdatedAt(rs.getTimestamp("updated_at"));
					//レコードをリストに追加する
					list.add(model);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
		return list;
	}

	/**
	 * ユーザー情報を１件追加
	 * 
	 * @param connection
	 * @param model UserModel
	 * @return 実行結果 1:成功、その他:エラーコード
	 */
	public int create(Connection connection, UserModel model) {
		try {
			String sql = "INSERT INTO users(email,password,name) values (?,?,?)";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				//パラメータの設定
				stmt.setString(1, model.getEmail());
				stmt.setString(2, model.getPassword());
				stmt.setString(3, model.getName());

				//SQL実行
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return e.getErrorCode();
		}
		return DatabaseSettings.DB_EXECUTION_SUCCESS;
	}

	/**
	 * 指定のアドレスとパスワードを持つユーザーを1件検索
	 * 
	 * @param connection
	 * @param email
	 * @param password
	 * @return UserModel
	 */

	public UserModel findOne(Connection connection, String email, String password) {
		// レコードを格納するUserModelのインスタンスを生成する。
		UserModel model = new UserModel();
		try {
			String sql = "SELECT * FROM users where email=? and password=?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, email);
				stmt.setString(2, password);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						model.setId(rs.getInt("id"));
						model.setEmail(rs.getString("email"));
						model.setPassword(rs.getString("password"));
						model.setName(rs.getString("name"));
						model.setCreatedAt(rs.getTimestamp("created_at"));
						model.setUpdatedAt(rs.getTimestamp("updated_at"));
					} else {
						model = null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return model;
	}

	/**
	 * 指定ユーザーIdのユーザーを1件更新
	 * 
	 * @param connection
	 * @param model UserModel
	 * @return 実行結果 1:成功、その他:エラーコード
	 */
	public int update(Connection connection, UserModel model) {
		try {
			String sql = "UPDATE users SET email=?,name=?,password=? WHERE id=?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, model.getEmail());
				stmt.setString(2, model.getName());
				stmt.setString(3, model.getPassword());
				stmt.setInt(4, model.getId());

				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return e.getErrorCode();
		}
		return DatabaseSettings.DB_EXECUTION_SUCCESS;
	}

}