package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GironnItemLogic;

/**
 * Servlet implementation class GironnDelete
 */
@WebServlet("/GironnDelete")
public class GironnDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GironnDelete() {
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

		int id = Integer.parseInt(request.getParameter("id"));
		//削除フラグのnullチェック
		int isDeleted = 0;
		if (request.getParameter("isDeleted") != null) {
			isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
		}

		try {
			GironnItemLogic gironnlogic = new GironnItemLogic();

			//議論コメント削除
			if (!gironnlogic.delete(id, isDeleted)) {
				// エラーがあったときは、エラーページへ
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}

			//コメント一覧表示に使用するため
			int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));

			//コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
			response.sendRedirect("GironnList?gidaiId=" + gidaiId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
