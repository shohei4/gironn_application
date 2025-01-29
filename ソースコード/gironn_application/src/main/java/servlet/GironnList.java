package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GironnItemLogic;
import model.GironnItemModel;
import model.UserModel;

/**
 * Servlet implementation class GironnMain
 */
@WebServlet("/GironnList")
public class GironnList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GironnList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		int userId = user.getId();

		try {
			//logicの実行
			GironnItemLogic logic = new GironnItemLogic();
			List<GironnItemModel> items = null;
			//一覧表示のためのフラグに使用する変数topの宣言
			String top = "top";
			//キーワード検索か全体表示かの条件分岐
			if (request.getParameter("flag") != null && top.equals(request.getParameter("flag"))) {
				//議論リスト全体表示
				items = logic.findAll(gidaiId, userId);
				//セッションスコープからキーワード削除
				session.removeAttribute("key");
			} else if (request.getParameter("key") != null) {
				//セッションスコープからキーワード取得
				session.setAttribute("key", request.getParameter("key"));
				//キーワード検索
				items = logic.findByKeyWord(request.getParameter("key"), gidaiId, userId);
			} else if (session.getAttribute("key") != null) {
				//セッションスコープからキーワード取得
				String key = (String) session.getAttribute("key");
				//キーワード検索
				items = logic.findByKeyWord(key, gidaiId, userId);
			} else {
				//議論リスト全体表示
				items = logic.findAll(gidaiId, userId);
			}
			//セションスコープに議論モデルを保存
			session.setAttribute("items", items);

			request.setAttribute("gidaiId", gidaiId);
			//コメント一覧表示画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/gironnList.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
