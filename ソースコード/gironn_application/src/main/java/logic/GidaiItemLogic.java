package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GidaiItemDAO;
import database.DBConnection;
import model.GidaiItemModel;

/**
 * GrionnItemのロジッククラス
 */
public class GidaiItemLogic {
	
	/**
	 * GidaiItemを全件取得
	 * 
	 * @param userId
	 * @param userName
	 * @return GironnItemModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<GidaiItemModel> findAll(int userId,String userName) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
					GidaiItemDAO dao = new GidaiItemDAO();
			
			return dao.findAll(conn,userId,userName);
		}
	}
	
	/**
	 * GidaiItemをキーワードで検索
	 * 
	 * @param keyWord
	 * @param userId
	 * @param userName
	 * @return GironnItemModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<GidaiItemModel> findByKeyWord(String keyWord,int userId,String userName) throws ClassNotFoundException,SQLException{
		try (DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			GidaiItemDAO dao = new GidaiItemDAO();
				
				return dao.findByKeyWord(conn,keyWord,userId,userName);
		}
	}
	
	/**
	 * GidaiItemを１件追加
	 * 
	 * @param model　GidaiItemModel
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean create(GidaiItemModel model) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			GidaiItemDAO dao = new GidaiItemDAO();
			
			return dao.create(conn,model);
		}
	}
	
	/**
	 * GidaiItemを１件削除
	 * 
	 * @param id　議題Id
	 * @return 結果（true:成功、false:失敗）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(int id)throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			GidaiItemDAO dao = new GidaiItemDAO();
			
			return dao.delete(conn,id);
		}
	}
}