package conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public ConnectionUtils() {
		// TODO Auto-generated constructor stub
	}
	public static Connection getConnection() 
            throws ClassNotFoundException, SQLException {
			String hostName = "localhost";
		    String dbName = "mywebapp";
		    String userName = "root";
		    String password = "";
		    Class.forName("com.mysql.jdbc.Driver");		    
		    // Cấu trúc URL Connection dành cho MySQL		   
		    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
		    String connectionURL1 = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?useSSL=true";
		    System.out.println("URL: "+connectionURL1);
		    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		    System.out.println("ket noi ok");
		    return conn;
    
  }
   
  public static void closeQuietly(Connection conn) {
      try {
          conn.close();
      } catch (Exception e) {
      }
  }

  public static void rollbackQuietly(Connection conn) {
      try {
          conn.rollback();
      } catch (Exception e) {
      }
  }
  public static void main(String args[]) {
		
		try {

			Connection con=(Connection) ConnectionUtils.getConnection();
			System.out.println(con);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

}
 
}
 	
