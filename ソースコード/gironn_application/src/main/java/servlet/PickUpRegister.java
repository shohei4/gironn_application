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
 * Servlet implementation class PickUpRegister
 */
@WebServlet("/PickUpRegister")
public class PickUpRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PickUpRegister() {
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
		//ユーザー情報をセッションスコープから取得
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		//hiddenタイプのgidai_idを受け取る
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		try {
			//モデルに保存
			PickUpModel pickUp = new PickUpModel();

			pickUp.setPickUpUser(user.getId());
			pickUp.setGidaiId(gidaiId);

			//logicの実行
			PickUpLogic logic = new PickUpLogic();
			logic.create(pickUp);

			//セッションスコープに保存
			session.setAttribute("pickUp", pickUp);

			//メインサーブレットにリダイレクト
			response.sendRedirect("Main");

			return;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
