package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.UserLogic;
import model.UserModel;
import settings.DatabaseSettings;
import settings.MessageSettings;
import settings.PageSettings;
import validation.UserValidation;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ユーザー更新ページへフォワードする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータ
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		try {
			// バリデーションチェックを行う。
			UserValidation validate = new UserValidation(request);
			Map<String, String> errors = validate.validate();

			//バリデーションエラーがあったとき
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);
				Map<String, String> user = new HashMap<String, String>();
				user.put("email", email);
				user.put("password", password);
				user.put("name", name);
				request.setAttribute("user", user);

				// ユーザー更新ページへフォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// ユーザーIDをセッションから取得する。
			HttpSession session = request.getSession();
			UserModel userModel = (UserModel) session.getAttribute("user");
			int id = userModel.getId();

			// リクエストパラメータをユーザーモデルに設定する。
			UserModel user = new UserModel();
			user.setId(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setName(name);

			// ユーザーを更新する。
			UserLogic logic;
			logic = new UserLogic();
			int ret = logic.update(user);

			switch (ret) {
			case DatabaseSettings.DB_EXECUTION_SUCCESS:
				// データベース操作成功のとき、ログアウトさせ、ログインページへリダイレクトして終了する。
				session.removeAttribute("user");
				response.sendRedirect(request.getContextPath() + "/Login");
				return;
			case DatabaseSettings.DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME:
				// ユニークKEYが重複（メールアドレスが重複）しいているとき、エラーメッセージをリクエストスコープに保存する。
				request.setAttribute("db_error", String.format(MessageSettings.MSG_ER_DUP_KEYNAME, user.getEmail()));
				break;
			default:
				// その他エラーのとき、エラーメッセージをリクエストスコープに保存する。
				request.setAttribute("db_error", MessageSettings.MSG_ERROR_OCCURRED);
				break;
			}

			request.setAttribute("user", user);

			// ユーザー更新ページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);

			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}
}
