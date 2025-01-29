package validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import settings.MessageSettings;
import validationUtil.Validation;
import validationUtil.ValidationUtil;

/**
 * 議論コメントのヴァリデーションクラス
 */
public class GironnValidation extends Validation {

	/**
	 * コンストラクタ
	 * @param request　リクエストオブジェクト
	 */
	public GironnValidation(HttpServletRequest request) {
		super(request);
	}

	/**
	 * バリデーションチェック
	 * 
	 * @return バリデーションエラーのMap<項目名, エラーメッセージ>
	 */
	public Map<String, String> validate() {
		//コメントバリデーション
		if (!ValidationUtil.isMaxLength(this.request.getParameter("comment"), 256)) {
			this.errors.put("comment", String.format(MessageSettings.MSG_LENGTH_LONG, "コメント", 256));
		}

		if (!ValidationUtil.isMinLength(this.request.getParameter("comment"), 1)) {
			this.errors.put("comment", String.format(MessageSettings.MSG_LENGTH_SHORT, "コメント", 1));
		}

		return errors;
	}

}
