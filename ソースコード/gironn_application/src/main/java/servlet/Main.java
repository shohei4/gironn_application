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

import logic.GidaiItemLogic;
import model.GidaiItemModel;
import model.UserModel;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			GidaiItemLogic logic = new GidaiItemLogic();
			
			List<GidaiItemModel> items = null;
			//セションスコープからユーザーモデルを取得
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			int userId = user.getId();
			String userName = user.getName();
			
			//キーワード検索
			if(request.getParameter("key") != null) {
			items = logic.findByKeyWord(request.getParameter("key"),userId,userName);
			request.setAttribute("key",request.getParameter("key"));
		} else {
			//議題リストの表示
			items = logic.findAll(userId,userName);
			}

			//議題モデルをセッションスコープに保存
			session.setAttribute("items", items);
			
			//メイン画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
			
			return;
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			
			return;

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		

	}

}
