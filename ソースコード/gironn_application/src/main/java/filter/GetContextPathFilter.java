package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "GetContextPathFilter") // フィルタを実行するURLは/WEB-INF/web.xmlで指定する。
/**
 * コンテキストパスを取得するためのフィルター
 */
public class GetContextPathFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// コンテキストパスを取得し、JSPで${root_path}でアクセスできるようにする。
				request.setAttribute("root_path", ((HttpServletRequest) request).getContextPath());
				
				chain.doFilter(request,response);
	}
	
}
