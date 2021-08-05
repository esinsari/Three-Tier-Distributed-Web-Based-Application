 /*
 * Name.............: Esin Sari
 * Course...........: CNT 4714 – Fall 2021 – Project Three
 * Assignment title.: A Three-Tier Distributed Web-Based Application
 * Date.............: August 1, 2021 
 * Class...........: Enterprise Computing
*/

package cnt4714_project3;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/this_WebServer")
public class WebServer extends HttpServlet {

	private DBConnection connection;
	private RunQuery query;
	private Statement statement;
	
	public void init( ServletConfig config ) throws ServletException {
		
		super.init(config);
		
		String databaseURL = "jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC";
		
		connection = new DBConnection(databaseURL, "root", "password");
		
		try {
			connection.establishConnection();
			statement = connection.getConnection().createStatement();
			query = new RunQuery(connection.getConnection(), statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnavailableException(e.getMessage());
		}
	} 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String sqlCommands = request.getParameter("sqlCommands ");
		String sqlCommandsLowerCase = sqlCommands.toLowerCase();
		String result = null;

		if (sqlCommandsLowerCase.contains("select")) {

			try {
				result = query.runQuery(sqlCommands); 
			
			} catch (SQLException e) {
				result = "<span>" + e.getMessage() + "</span>";

				e.printStackTrace();
			}
		}
		else {
			try {
				result = query.update(sqlCommands, statement);
				
			}catch(SQLException e) {
				result = "<span>" + e.getMessage() + "</span>";

				e.printStackTrace();
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		session.setAttribute("sqlCommands", sqlCommands );
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String sqlCommands  = request.getParameter("sqlCommands");
		String sqlCommandsLowerCase = sqlCommands.toLowerCase();
		String result = null;
		
		if (sqlCommandsLowerCase.contains("select")) {

			try {
				result = query.runQuery(sqlCommands); 
			
			} catch (SQLException e) {
				result = "<span>" + e.getMessage() + "</span>";

				e.printStackTrace();
			}
		}
		else { 
			try {
				result = query.update(sqlCommands, statement);
				
			}catch(SQLException e) {
				result = "<span>" + e.getMessage() + "</span>";

				e.printStackTrace();
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		session.setAttribute("sqlCommands", sqlCommands);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
    }	
}
