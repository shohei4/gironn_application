package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FavoriteItemModel;

public class FavoriteItemDAO {

	/**
	 * favoriteテーブル内のレコード件数を取得
	 * 
	 * @param Connection connection データベースコネクションインスタンス
	 * @retun int型のレコード数
	 */
	public int count(Connection connection, int commentId) {
		//countの結果を格納するint型のfavoriteCountを生成
		int favoriteCount = 0;
		try {
			//SQL文の設定をする(countの結果をfavoriteCountとする)
			String sql = "SELECT COUNT(comment_id) AS favoriteCount FROM favorite where comment_id=?";

			//SQLを実行する準備をする
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, commentId);
				//SQLを実行する
				try (ResultSet rs = stmt.executeQuery()) {
					rs.next();
					favoriteCount = rs.getInt("favoriteCount");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favoriteCount;
	}

	/**
	 * Favoriteアイテムを1件追加する
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * @param_model FavoriteItemModel
	 * @return 結果（true:成功、false:失敗） 
	 */
	public boolean create(Connection connection, FavoriteItemModel model) {
		try {
			//SQL文の設定
			String sql = "INSERT INTO favorite("
					+ "comment_id,"
					+ "user_id) "
					+ "VALUES ("
					+ "?,"
					+ "?"
					+ ")";
			//SQLの実行準備
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				//パラメーターを設定する
				stmt.setInt(1, model.getCommentId());
				stmt.setInt(2, model.getUserId());

				//SQLの実行
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Favoriteアイテムを1件削除する
	 * 
	 * @param Connection connection データベースコネクションのインスタンス
	 * @param_model FavoriteItemModel
	 * @return 結果（true:成功、false:失敗） 
	 */
	public boolean delete(Connection connection, FavoriteItemModel model) {
		try {
			//SQL文の設定
			String sql = "DELETE FROM favorite WHERE comment_id=? AND user_id=?";

			//SQLの実行準備
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {

				//パラメータを設定
				stmt.setInt(1, model.getCommentId());
				stmt.setInt(2, model.getUserId());

				//SQLの実行
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
