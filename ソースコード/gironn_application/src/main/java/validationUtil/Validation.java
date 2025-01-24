package validationUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


//バリデーション基底クラス（抽象クラス）
public abstract class Validation {
	
	//リクエストオブジェクト protected は同一パッケージまたはサブクラスからは参照可能
	protected HttpServletRequest request;
	
	//エラーが発生した項目名とエラーの内容を格納するMAP
	protected Map<String,String> errors;
	
	//コンストラクタ
	
	public Validation(HttpServletRequest request) {
		this.request = request;
		this.errors = new HashMap<String,String>();
	}
	
	//バリデーションの有無を判定
	public boolean hasErrors() {
		//sizeメソッドは要素数を返す
		if (this.errors.size() > 0) {
			return true;
		}
		return false;
	}
	
	//バリデーション実行
	public abstract Map<String,String> validate();
}
