package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.FavoriteItemLogic;
import model.FavoriteItemModel;
import model.UserModel;

/**
 * Servlet implementation class GironnFavorite
 */
@WebServlet("/GironnFavorite")
public class GironnFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GironnFavorite() {
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

		//処理を分岐させるための変数flagの定義とnullチェック
		int flag = 0;
		if (request.getParameter("flag") != null) {
			flag = Integer.parseInt(request.getParameter("flag"));
		}

		try {
			FavoriteItemModel favoriteItem = new FavoriteItemModel();

			FavoriteItemLogic logic = new FavoriteItemLogic();

			favoriteItem.setCommentId(Integer.parseInt(request.getParameter("id")));

			//ユーザー情報をセッションスコープから取得
			HttpSession session = request.getSession();
			UserModel user = (UserModel) session.getAttribute("user");
			favoriteItem.setUserId(user.getId());

			//favoriteテーブルへの登録と削除の分岐
			if (flag == 1) {

				//favoriteテーブルに保存
				logic.create(favoriteItem);

			} else if (flag == 2) {

				//favoriteテーブルから削除
				logic.delete(favoriteItem);
			}

			//コメント一覧表示に使用するため
			int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));

			//コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
			response.sendRedirect("GironnList?gidaiId=" + gidaiId);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
