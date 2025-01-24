package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ReplyItemLogic;
import model.ReplyItemModel;
import settings.PageSettings;
import validation.ReplyValidation;

/**
 * Servlet implementation class ReplyUpdate
 */
@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		request.setAttribute("gidaiId", gidaiId);
		//議論コメント編集画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/replyUpdate.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			int id = Integer.parseInt(request.getParameter("id"));
			String comment = request.getParameter("comment");
			 
			 try {
				 //バリデーションチェック
				 ReplyValidation validate = new ReplyValidation(request);
				 Map<String,String> errors = validate.validate();
				 
				//バリデーションエラーがあった場合
					if(validate.hasErrors()) {
						request.setAttribute("errors", errors);
						//JSPのinputタグのvalue値の表示に使うためにリクエストパラメータをMapに保存する。
						Map<String,String> replyItem = new HashMap<String,String>();
						replyItem.put("comment", comment);
						request.setAttribute("comment",comment);
					
						// フォワードして終了する。
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/replyUpdate.jsp");
						dispatcher.forward(request, response);

						return;
					}
					
				ReplyItemModel replyItem = new ReplyItemModel();
				replyItem.setComment(comment);
				
				ReplyItemLogic logic = new ReplyItemLogic();
				
				//返信コメント更新
				if(!logic.update(replyItem,id)) {
					// エラーがあったときは、編集画面へフォワードする
					request.setAttribute("replyItem", replyItem);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/replyUpdate.jsp");
					dispatcher.forward(request, response);
				}
				
				//コメント一覧表示に使用するため
				int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
			
				//コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
				response.sendRedirect("GironnList?gidaiId="+gidaiId);
			 
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();

			// エラーページへフォワードする。
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}
	}

}
		
	



