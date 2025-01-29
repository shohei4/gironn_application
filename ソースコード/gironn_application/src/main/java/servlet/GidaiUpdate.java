package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GidaiItemLogic;
import settings.PageSettings;
import validationUtil.ValidationUtil;

/**
 * Servlet implementation class GidaiUpdate
 */
@WebServlet("/GidaiUpdate")
public class GidaiUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GidaiUpdate() {
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
		try {
			// idがint型でないときは、無条件でMainページ送りにする。
			if (!ValidationUtil.isInteger(request.getParameter("id"))) {
				response.sendRedirect(request.getContextPath() + "/Main");

				return;
			}
			int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));

			GidaiItemLogic logic = new GidaiItemLogic();
			//削除が失敗した場合メインjspにフォワード
			if (!logic.delete(gidaiId)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);

				return;
			}
			//Mainへリダイレクト
			response.sendRedirect(request.getContextPath() + "/Main");

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
