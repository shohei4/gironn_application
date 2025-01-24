package validationUtil;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;

import settings.SecuritySettings;

public class ValidationUtil {
	
	//文字列が指定数以下であるかの判定
	public static final boolean isMaxLength(String str,int length) {
		return str.length() <= length;
	}
	
	//文字列が指定数以上であるかの判定
	public static final boolean isMinLength(String str, int length) {
		return str.length() >= length;
	}
	
	//文字列がEmail形式として正しいかどうかの判定
	public static final boolean isEmail(String email) {
		//インスタンスの作成
		EmailValidator validator = EmailValidator.getInstance();
		
		return validator.isValid(email);
	}
	
	//文字列がパスワード（半角英数大文字小文字数字を取り混ぜて8文字以上20文字以下）として正しいかどうかの判定
	public static final boolean isPassword(String password) {
		RegexValidator regex = new RegexValidator(SecuritySettings.PASSWORD_REGEXP_STRING);
		return regex.isValid(password);
	}
	
	//文字列が日付形式（yyyy-MM-dd)かどうかの判定
	public static final boolean isDate(String value) {
		DateValidator date = DateValidator.getInstance();
		return date.isValid(value,"yyyy-MM-dd");
	}
	
	//文字列がint型になっていないかの判定
	public static final boolean isInteger(String value) {
		IntegerValidator integer = IntegerValidator.getInstance();
		return integer.isValid(value);
	}
	
	//文字列がfloat型になっていないか判定
	public static final boolean isFloata(String value) {
		FloatValidator object = FloatValidator.getInstance();
		return  object.isValid(value);
	}
	
	
}
