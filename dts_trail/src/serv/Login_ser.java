package serv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Login_dao;

import bean.LoginInfo;

/**
 * Servlet implementation class Login_ser
 */
@WebServlet("/Login_ser")
public class Login_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		String user_name= request.getParameter("user_name");
		String user_pass= request.getParameter("user_pass");
		
		HttpSession s= request.getSession();
		LoginInfo obj= new LoginInfo();
		obj.setPassword(user_pass);
		obj.setUser_name(user_name);
		String status=null;
		Login_dao d= new Login_dao();
		try {
			status=d.doSelect(obj);
			if(status.equals("problem in database")||status.equals("wrong username")||status.equals("wrong password")){
				s.setAttribute("status", status);
				RequestDispatcher rd= request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}
			else{
				s.setAttribute("user_logged", user_name);
				s.setAttribute("user_role", status);
				System.out.println("in login_ser and username="+user_name);
				RequestDispatcher rd= request.getRequestDispatcher("Desktop_ser");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
