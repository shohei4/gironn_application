package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import settings.PageSettings;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "LoginCheckFilter")
public class LoginCheckFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public LoginCheckFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//ServletRequestクラスオブジェクトではgetSession()メソッド、getContextPath()メソッドが使えないので、
		//HttpServletRequestクラスオブジェクトにキャストする
		HttpServletRequest req = (HttpServletRequest) request;

		// ServletResponseクラスオブジェクトではsendRedirect()メソッドが使えないので、
		// HttpServletResponseクラスオブジェクトにキャストする。
		HttpServletResponse res = (HttpServletResponse) response;

		// セッションスコープを取得する。なければ作成する。
		// 引数ture：セッションが開始されてない場合、新しいセッションを返す。
		HttpSession session = req.getSession(true);

		if (session.getAttribute("user") == null) {
			//ログインページへリダイレクトする
			res.sendRedirect(req.getContextPath() + PageSettings.LOGIN_SERVLET);

			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
