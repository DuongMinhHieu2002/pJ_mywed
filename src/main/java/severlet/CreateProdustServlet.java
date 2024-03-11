package severlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import Beans.Product;
import DAO.DBUtils;
import conn.ConnectionUtils;

/**
 * Servlet implementation class CreateProdustServlet
 */
@WebServlet("/createProduct1")
public class CreateProdustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProdustServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispastcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/CreateProductView.jsp");
		dispastcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con=null;
		String errorString=null;
//		try {
//		 con= (Connection) ConnectionUtils.getConnection();
//		}
//		catch(SQLException e) {
//			errorString=e.getMessage();
//		}
//		catch(ClassNotFoundException e1) {
//			errorString=e1.getMessage();
//		}
		con =(Connection) request.getAttribute("connection");
		errorString =(String) request.getAttribute("errorString");
		String code=(String)request.getParameter("code");
		String name=(String)request.getParameter("name");
		String priceStr=(String)request.getParameter("price");		
		float price=0;
		try {
			price=Float.parseFloat(priceStr);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			errorString=e.getMessage();
		}
		Product product=new Product(code,name,price);
		String regex="\\w";
		if(code==null||code.matches(regex)) {
			errorString="Product Code invalid";
		}
		if(errorString==null) {
			try {
				DBUtils.insertProduct(con, product);
			}catch(SQLException e) {
				e.printStackTrace();
				errorString=e.getMessage();
			}
		}
		//luu thong tin vao requets truoc khi forward sang view
		request.setAttribute("errorString",errorString);
		request.setAttribute("product",product);
		//neu co loi forward sang trang "createProductView.jsp neu khong chuyen huong sang productList
		if(errorString!=null) {
			RequestDispatcher dispatcher=request.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/productList");
		}
	}

}
