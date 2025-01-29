package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.PickUpDAO;
import database.DBConnection;
import model.GidaiItemModel;
import model.PickUpModel;

public class PickUpLogic {

	/**
	 *　GidaiItemの要素PickUpModelを１件追加する
	 * 
	 * @param model PickUpModel
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean create(PickUpModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			PickUpDAO dao = new PickUpDAO();

			return dao.create(conn, model);
		}
	}

	/**
	 * PickUpテーブルにあるGidaiItemを全件取得
	 * 
	 * @param userId
	 * @return GidaiItemModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<GidaiItemModel> findPickUpList(int userId) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			PickUpDAO dao = new PickUpDAO();

			return dao.findPickUpList(conn, userId);
		}
	}

	/**
	 * pickUpを１件削除
	 * 
	 * @param model 
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(PickUpModel model) throws ClassNotFoundException, SQLException {
		try (DBConnection db = new DBConnection()) {
			Connection conn = db.getInstance();
			PickUpDAO dao = new PickUpDAO();

			return dao.delete(conn, model);
		}
	}
}