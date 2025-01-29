package logic;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FavoriteItemDAO;
import database.DBConnection;
import model.FavoriteItemModel;

/**
 * FavoriteItemロジッククラス
 */
public class FavoriteItemLogic {
	/**
	 * テーブルのレコード数を取得する
	 *  
	 * @param commentId
	 * @return　int型のfavoriteCount
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int count(int commentId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			FavoriteItemDAO dao = new FavoriteItemDAO();
			return dao.count(conn, commentId);
		}
	}

	/**
	 * favoriteItemを１件追加する
	 * 
	 * @param model　FavoriteItemModel
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean create(FavoriteItemModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			FavoriteItemDAO dao = new FavoriteItemDAO();
			return dao.create(conn, model);
		}
	}

	/**
	 * favorietItemを1件削除
	 * 
	 * @param model FavoriteItemModel
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(FavoriteItemModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			FavoriteItemDAO dao = new FavoriteItemDAO();
			return dao.delete(conn, model);
		}
	}
}
