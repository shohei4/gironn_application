package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import validationUtil.Validation;
import validationUtil.ValidationUtil;

/**
 * 議題名のバリデーションクラス
 */
public class GidaiValidation extends Validation{
	
	/**
	 * コンストラクタ
	 * @param request　リクエストオブジェクト
	 */
	public GidaiValidation(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * バリデーションチェック
	 * 
	 * @return バリデーションエラーのMap<項目名, エラーメッセージ>
	 */
	public Map<String, String> validate(){
		//議題名バリデーション
		if( !ValidationUtil.isMaxLength(this.request.getParameter("gidaiName"),50)) {
			this.errors.put("gidaiName", String.format(MessageSettings.MSG_LENGTH_LONG, "議題名",50));
		}
		
		if(!ValidationUtil.isMinLength(this.request.getParameter("gidaiName"), 1)) {
			this.errors.put("gidaiName", String.format(MessageSettings.MSG_LENGTH_SHORT, "議題名",1));
		}
		
		return errors;
	}

}
