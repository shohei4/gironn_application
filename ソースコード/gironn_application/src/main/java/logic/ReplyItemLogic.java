package logic;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ReplyDAO;
import database.DBConnection;
import model.ReplyItemModel;

public class ReplyItemLogic {
	
	/**
	 * ReplyItemを１件追加する
	 * 
	 * @param model ReplyItemModel
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean create(ReplyItemModel model) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			ReplyDAO dao = new ReplyDAO();
			
			return dao.create(conn,model);
		}
	}
	
	/**
	 * ReplyItemを1件更新
	 * 
	 * @param model ReplyItemModel
	 * @param id 議題Id
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean update(ReplyItemModel model,int id)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			ReplyDAO dao = new ReplyDAO();
			return dao.update(conn,model,id);
		}
	}
	
	/**
	 * GironnItemを１件削除
	 * 
	 * @param id　コメントid
	 * @param isDeleted
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(int id,int isDeleted)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			ReplyDAO dao = new ReplyDAO();
			return dao.delete(conn, id, isDeleted);
		}
	}

}
