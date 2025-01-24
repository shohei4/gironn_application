package database;

import java.sql.Connection;
import java.sql.DriverManager;//JDBCを管理するためのクラス
import java.sql.SQLException;

import settings.DatabaseSettings;


/**
 *  データベースコネクション（接続）クラス
 * AutoCloseableについては、APIのドキュメントの「インタフェースAutoCloseable」の項を参照。
 * @see https://docs.oracle.com/javase/jp/8/docs/api/java/lang/AutoCloseable.html
 */
public class DBConnection implements AutoCloseable{
	private Connection connection;
	
	public DBConnection() throws ClassNotFoundException,SQLException{
		//JDBCドライバの読み込み
		Class.forName(DatabaseSettings.DRIVER_NAME);
		//データベースコネクションをフィールドに保存
		this.connection = DriverManager.getConnection(DatabaseSettings.JDBC_URL,DatabaseSettings.DB_USER,
				DatabaseSettings.DB_PASS);
	}
	//データベースコネクションのインスタンスを返却(ゲッターのような働き）
	public Connection getInstance() throws SQLException,ClassNotFoundException{
		//データベースコネクションを返却
				return this.connection;
	}
	//データベースコネクションを閉じる
		public void close() {
			try {
				this.connection.close();
			}catch(Exception e) {
				//何もしない
			}
		}
}

