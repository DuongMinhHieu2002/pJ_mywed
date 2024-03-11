package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.Connection;

import conn.ConnectionUtils;
/**
 * Servlet Filter implementation class connectionFilter

 */
@WebFilter(urlPatterns = { "/*" })
public class connectionFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public connectionFilter() {
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
	private List<String> listUrls = new ArrayList<>(Arrays.asList("/productList", "/home","/createProduct1","/editProduct"));
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 String url = httpRequest.getRequestURI();
		 String contextPath = httpRequest.getContextPath();
		 String relativeUrl = url.substring(contextPath.length());
		 System.out.println(relativeUrl);
	     if (listUrls.contains(relativeUrl)) {
	    	 Connection con=null;
				String errorString=null;
				try {
				 con=(Connection) ConnectionUtils.getConnection();
				}
				catch(SQLException e) {
					errorString=e.getMessage();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				request.setAttribute("errorString",errorString);
				request.setAttribute("connection",con);
				chain.doFilter(request, response);
				 System.out.println("urlPatterns Ok");
	     }else {
	            System.out.println("urlPatterns not Ok");         
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
	            dispatcher.forward(request, response);
	        }
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
