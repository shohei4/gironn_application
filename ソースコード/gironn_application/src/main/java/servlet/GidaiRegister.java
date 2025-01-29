package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GidaiItemLogic;
import model.GidaiItemModel;
import model.UserModel;
import validation.GidaiValidation;

/**
 * Servlet implementation class GidaiRegister
 */
@WebServlet("/GidaiRegister")
public class GidaiRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GidaiRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**sssss
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//議題名とジャンルのリクエストパラメーターを取得
		String gidaiName = request.getParameter("gidaiName");
		int genre = Integer.parseInt(request.getParameter("genre"));
		//今日の日付を取得する
		Date today = new Date();
		request.setAttribute("today", today);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(today);
		java.sql.Date registrationDate = java.sql.Date.valueOf(formattedDate);

		try {
			//バリデーションチェック
			GidaiValidation validate = new GidaiValidation(request);
			Map<String, String> errors = validate.validate();
			//バリデーションエラーがあった場合
			if (validate.hasErrors()) {
				//JSPのinputタグのvalue値の表示に使うためにリクエストパラメータをMapに保存する。
				request.setAttribute("errors", errors);

				Map<String, String> gidaiItem = new HashMap<String, String>();
				gidaiItem.put("gidaiName", gidaiName);
				request.setAttribute("gidaiItem", gidaiItem);

				// 登録ページへフォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);

				return;
			}
			//セッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			//議題モデルに保存
			GidaiItemModel gidaiItem = new GidaiItemModel();
			gidaiItem.setUserId(user.getId());
			gidaiItem.setUserName(user.getName());
			gidaiItem.setGidaiName(gidaiName);
			gidaiItem.setGenre(genre);
			gidaiItem.setRegistrationDate(registrationDate);
			//議題モデルをリクエストスコープに保存する
			request.setAttribute("gidaiItem", gidaiItem);
			//議題テーブルに保存
			GidaiItemLogic logic = new GidaiItemLogic();
			logic.create(gidaiItem);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//Mainサーブレットへリダイレクト
		response.sendRedirect("Main");
	}

}
