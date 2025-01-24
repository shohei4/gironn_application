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

import logic.GironnItemLogic;
import model.GironnItemModel;
import validation.GironnValidation;

/**
 * Servlet implementation class GiornnUpdate
 */
@WebServlet("/GironnUpdate")
public class GironnUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GironnUpdate() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gironnUpdate.jsp");
		dispatcher.forward(request, response);
		return;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//コメント一覧表示に使用するため
		int gidaiId = Integer.parseInt(request.getParameter("gidaiId"));
		int id = Integer.parseInt(request.getParameter("id"));
		String comment = request.getParameter("comment");
		 
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
					request.setAttribute("comment",comment);
					
					request.setAttribute("id", id);
					request.setAttribute("gidaiId", gidaiId);
					// フォワードして終了する。
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gironnUpdate.jsp");
					dispatcher.forward(request, response);

					return;
				}
				
			GironnItemModel gironnItem = new GironnItemModel();
			gironnItem.setComment(comment);
			
			GironnItemLogic logic = new GironnItemLogic();
			
			//議論コメント更新
			if(!logic.update(gironnItem,id)) {
				// エラーがあったときは、編集画面へフォワードする
				request.setAttribute("gironnItem", gironnItem);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gironnUpdate.jsp");
				dispatcher.forward(request, response);
			}
			
			
		
			//コメント一覧へリダイレクト（URLパラメータgidaiIdを設定）
			response.sendRedirect("GironnList?gidaiId="+gidaiId);
		 }catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
			}
		
	}

}
