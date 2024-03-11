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

import DAO.DBUtils;
import conn.ConnectionUtils;

/**
 * Servlet implementation class delServlet
 */
@WebServlet("/deleteProduct")
public class delServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		String errorString=null;
		try {
		 con=(Connection) ConnectionUtils.getConnection();
		}
		catch(SQLException e) {
			errorString=e.getMessage();
		}
		catch(ClassNotFoundException e1) {
			errorString=e1.getMessage();
		}
		String code=request.getParameter("code");
		
		try {
			DBUtils.deleteProduct(con, code);
		}catch(SQLException e) {
			e.printStackTrace();
			errorString=e.getMessage();
		}
		//neu co loi  forward quay lai trang productView
		if(errorString!=null) {
			request.setAttribute("errorString",errorString);
			RequestDispatcher dispatcher=request.getServletContext().getRequestDispatcher("/WEB-INF/views/delProductView.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			//neu khong co loi redirec lai trang ProduceListView.jsp
			response.sendRedirect(request.getContextPath()+"/productList");
			
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
