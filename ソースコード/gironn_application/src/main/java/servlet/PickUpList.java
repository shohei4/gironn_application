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

import logic.PickUpLogic;
import model.GidaiItemModel;
import model.UserModel;

/**
 * Servlet implementation class PickUpList
 */
@WebServlet("/PickUpList")
public class PickUpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PickUpList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//セッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			//ピックアップしたものを入れるリスト
			List<GidaiItemModel> items = null;
			int userId = user.getId();
			//一覧表示
			PickUpLogic logicPick = new PickUpLogic();
			items = logicPick.findPickUpList(userId);
			
			//ピックアップjspへフォワード
			 request.setAttribute("items", items);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/pickUp.jsp");
				dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			}

}
