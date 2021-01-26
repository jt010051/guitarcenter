

import Products.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowProductsServlet extends HttpServlet {

	  
	    public void doGet(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");

	        PrintWriter out = response.getWriter();
	        out.println("<form action='' method='POST'>");
	        out.println("<label>Enter Product ID: <input type='text' name='product-id'></input></label>");
	        out.println("<input type='submit'>Get Details</input>");
	        out.println("</form>");
	    }

	    public void doPost(HttpServletRequest request,
	          HttpServletResponse response) throws ServletException, IOException {
	        PrintWriter out = response.getWriter();

	        String productId = request.getParameter("product-id");
	        
	        try  {
	        	long id = Long.parseLong(productId);
		        
		        String SELECT_ALL_SQL = "SELECT * FROM product where id = " +id;
		        String name;
		        boolean avail;
	        	Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"
	        			+ "guitarcenter?createDatabaseIfNotExist=true", "root", "Adminis3");
	        	Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
	            if (id<=10 && id>0) {
	            while (rs.next()) {
	                 id = rs.getLong("id");
	                 name = rs.getString("name");
	                 avail = rs.getBoolean("available");
	               out.print("id: " +id +"\nname: " +name +"\nAvailable= ");
	               if(avail == true) {
	            	   out.print("yes");
	               }
	               else {
	            	   out.print("no");
	               }
	            }
	          
	            }
	            else {
	            	out.println("Invalid id");
	            }
	        } catch (SQLException e ) {
	            System.out.println("unable to run query");
	            e.printStackTrace();
	        }
	        
	        catch (NumberFormatException n) {
	        	out.println("Invalid Id");
	        }
	        }
	    
}
