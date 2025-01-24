package logic;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GironnItemDAO;
import database.DBConnection;
import model.GironnItemModel;
/**
 * GironnItemのロジッククラス
 */
public class GironnItemLogic {
	
	/**
	 * GironnItemを全件取得
	 * 
	 * @param gidaiId
	 * @param userId
	 * @return　GironnItemModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<GironnItemModel> findAll(int gidaiId,int userId) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
					GironnItemDAO dao = new GironnItemDAO();
			
			return dao.findAll(conn,gidaiId,userId);
		}
	}
	
	/**
	 * GidaiItemをキーワードで検索
	 * 
	 * @param keyWord
	 * @param gidaiId
	 * @param userId
	 * @return　GironnItemModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<GironnItemModel> findByKeyWord(String keyWord,int gidaiId,int userId)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
					GironnItemDAO dao = new GironnItemDAO();
					
			return dao.findByKeyWord(conn, keyWord, gidaiId, userId);
		}
	}
	
	/**
	 * GironnItemを１件追加
	 * 
	 * @param model　GironnItemModel
	 * @return　結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean create(GironnItemModel model)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			GironnItemDAO dao = new GironnItemDAO();
			
			return dao.create(conn,model);
		}
	}
	
	/**
	 * GironnItemを１件更新
	 * 
	 * @param model　GironnItemModel
	 * @param id　コメントid
	 * @return　結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean update(GironnItemModel model,int id)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			GironnItemDAO dao = new GironnItemDAO();
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
			GironnItemDAO dao = new GironnItemDAO();
			return dao.delete(conn, id, isDeleted);
		}
	}
}
