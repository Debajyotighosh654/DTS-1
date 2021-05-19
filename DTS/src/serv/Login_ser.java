package serv;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginInfo;
import dao.Login_dao;


@WebServlet("/Login_ser")
public class Login_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("hello");
		String user_name = request.getParameter("user_name");
		String user_pass = request.getParameter("user_pass");
		
		HttpSession s = request.getSession();
		LoginInfo obj = new LoginInfo();
		obj.setPassword(user_pass);
		obj.setUser_name(user_name);
		String status = null;
		Login_dao d = new Login_dao();
		
		try {
			status = d.doselect(obj);
			
			if(status.equals("Problem in DataBase") || status.equals("Wrong UserName") || status.equals("Wrong Password")) {
				s.setAttribute("status", status);
				
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}
			else {
				s.setAttribute("user_logged", user_name);
				s.setAttribute("user_role", status);
				System.out.println ("in login_ser and username="+user_name);

				RequestDispatcher rd = request.getRequestDispatcher("Desktop_ser");
				rd.forward(request, response);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
