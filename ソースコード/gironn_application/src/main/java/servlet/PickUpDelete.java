package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.PickUpLogic;
import model.PickUpModel;
import model.UserModel;

/**
 * Servlet implementation class PickUpDelete
 */
@WebServlet("/PickUpDelete")
public class PickUpDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PickUpDelete() {
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

		//ピックアップしたユーザー情報をセッションから取得
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		//モデルに保存
		PickUpModel model = new PickUpModel();
		model.setGidaiId(gidaiId);
		model.setPickUpUser(user.getId());

		//logic実行
		PickUpLogic logic = new PickUpLogic();
		try {
			logic.delete(model);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//ピックアップリスト一覧へリダイレクト
		response.sendRedirect("/gironn_application1/PickUpList");

	}

}
