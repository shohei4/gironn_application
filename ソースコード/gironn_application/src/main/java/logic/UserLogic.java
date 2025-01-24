package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserDAO;
import database.DBConnection;
import model.UserModel;

public class UserLogic {
	
	/**
	 * ユーザーの情報の全件取得
	 * 
	 * @return UserModelのArrayList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<UserModel> findAll() throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
					UserDAO dao = new UserDAO();
			
			return dao.findAll(conn);
		}
	}
	
	/**
	 * ユーザーの情報を1件追加
	 * 
	 * @param model UserModel
	 * @return 実行結果 1:成功、その他:エラーコード
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int create(UserModel model) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.create(conn,model);
		}
	}
	
	/**
	 * ユーザー情報を１件検索
	 * 
	 * @param email
	 * @param password
	 * @return　UserModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserModel find(String email,String password) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.findOne(conn, email, password);
		}
	}
	
	/**
	 * ユーザー情報を１件更新
	 * 
	 * @param model UserModel
	 * @return 実行結果 1:成功、その他:エラーコード
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update(UserModel model) throws ClassNotFoundException,SQLException{
		try(DBConnection db = new DBConnection()){
			Connection conn = db.getInstance();
			UserDAO dao = new UserDAO();
			
			return dao.update(conn, model);
		}
	}
}