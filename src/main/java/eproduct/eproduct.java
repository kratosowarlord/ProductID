package eproduct;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class eproduct
 */
public class eproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public eproduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:8080/products";
		String uname="root";
		String pass="root123";
		
		response.setContentType("text/html");
		
		int ID = Integer.parseInt(request.getParameter("ID"));
	
		PrintWriter out = response.getWriter();
		
		String query="select * from product where ID=?";
//		out.print("<h1>Retrieved The Required Data  </h1>");
//		out.print("<table border='5'><tr> <th>ID</th><th>NAME</th></tr>");
//	    out.print("<table border='5'><tr> <th></th><th>LAPTOP</th></tr>");
		try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection dbCon = DriverManager.getConnection(url, uname, pass);
	      PreparedStatement st=  dbCon.prepareStatement(query);
	      
	      st.setInt(1, ID);
	      
	      ResultSet rs =st.executeQuery();
	      
	      while(rs.next()) {
	    	  
	    	  out.print("<tr><td>");
	    	  out.println(rs.getInt(1));
	    	  out.print("</td>");
	    	  out.print("<td>");
	    	  out.print(rs.getString(2));
	    	  out.print("</td>");
	    	  out.print("<td>");
	    	  out.print(rs.getString(3));
	    	  out.print("</td>");
	    	  out.print("</tr>");
	    
	    	  
			}
	      
		}
		catch(Exception e){
			
			System.out.println("Some Issue : "+ e.getMessage());
			
			
		}
		
		out.print("</table>");
		
	}

}