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

import logic.ReplyItemLogic;
import model.ReplyItemModel;
import model.UserModel;
import validation.ReplyValidation;

/**
 * Servlet implementation class ReplyRegister
 */
@WebServlet("/ReplyRegister")
public class ReplyRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		request.getParameter("commentId");
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String comment = request.getParameter("comment");
		//今日の日付を取得する
		Date today = new Date();
		request.setAttribute("today", today);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(today);
		java.sql.Date registrationDate = java.sql.Date.valueOf(formattedDate);

		try {
			//バリデーションチェック
			ReplyValidation validate = new ReplyValidation(request);
			Map<String, String> errors = validate.validate();
			//バリデーションエラーがあった場合
			if (validate.hasErrors()) {
				request.setAttribute("errors", errors);
				//jspで表示するためにhashmapに格納
				Map<String, String> replyItem = new HashMap<String, String>();
				replyItem.put("comment", comment);
				request.setAttribute("replyIetm", replyItem);
				// フォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gironnList.jsp");
				dispatcher.forward(request, response);

				return;
			}
			//ユーザー情報、リクエストパラメーターをモデルにセット
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");

			//ReplyItemModelに保存
			ReplyItemModel replyItem = new ReplyItemModel();
			replyItem.setUserId(user.getId());
			replyItem.setUserName(user.getName());
			replyItem.setCommentId(commentId);
			replyItem.setComment(comment);
			replyItem.setRegistrationDate(registrationDate);

			//リクエストスコープに保存
			request.setAttribute("replyItem", replyItem);

			//logicの実行
			ReplyItemLogic logic = new ReplyItemLogic();
			logic.create(replyItem);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		////コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
		response.sendRedirect("GironnList?gidaiId=" + gidaiId);
	}

}
