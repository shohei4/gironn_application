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

import logic.GironnItemLogic;
import model.GironnItemModel;
import model.UserModel;
import validation.GironnValidation;

/**
 * Servlet implementation class GironnRegister
 */
@WebServlet("/GironnRegister")
public class GironnRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GironnRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment = request.getParameter("comment");
		//クエリストリングパラメーターでgidaiIdの値をリダイレクト先に渡すためjspから取得
		
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		//今日の日付を取得する
				Date today = new Date();
				request.setAttribute("today", today);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = simpleDateFormat.format(today);
		        java.sql.Date registrationDate = java.sql.Date.valueOf(formattedDate);
		try {
			//バリデーションチェック
			GironnValidation validate = new GironnValidation(request);
			Map<String,String> errors = validate.validate();
			//バリデーションエラーがあった場合
			if(validate.hasErrors()) {
				request.setAttribute("errors", errors);
				//JSPのinputタグのvalue値の表示に使うためにリクエストパラメータをMapに保存する。
				Map<String,String> gironnItem = new HashMap<String,String>();
				gironnItem.put("comment", comment);
				request.setAttribute("gironnItem",gironnItem);
				request.setAttribute("gidaiId", gidaiId);
				// フォワードして終了する。
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gironnList.jsp");
				dispatcher.forward(request, response);

				return;
			}
			//セションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			UserModel user = (UserModel)session.getAttribute("user");
			//議論モデルに保存
			GironnItemModel gironnItem = new GironnItemModel();
			gironnItem.setUserId(user.getId());
			gironnItem.setUserName(user.getName());
			gironnItem.setRegistrationDate(registrationDate);
			gironnItem.setGidaiId(gidaiId);
			gironnItem.setComment(comment);
			//リクエストスコープに議論も出るを保存
			request.setAttribute("gironnItem", gironnItem);
			//logicの実行
			GironnItemLogic logic = new GironnItemLogic();
			logic.create(gironnItem);
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		//コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
		response.sendRedirect("GironnList?gidaiId="+gidaiId);
	}

}
