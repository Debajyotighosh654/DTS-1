package serv;

import java.io.IOException;
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
import dao.User_dao;

import bean.LoginInfo;
import bean.UserInfo;

/**
 * Servlet implementation class User_ser
 */
@WebServlet("/User_ser")
public class User_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_ser() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String op=request.getParameter("submit");
		
		System.out.println("in serv User_user");
		String user_name=request.getParameter("user_name");
		String role_name=request.getParameter("role_name");
		String first_name=request.getParameter("first_name");
		String middle_name=request.getParameter("middle_name");
		String last_name=request.getParameter("last_name");
		String email=request.getParameter("email");
		String phone_no=request.getParameter("phone_no");
		String alternate_no=request.getParameter("alternate_no");
		String emergency_no=request.getParameter("emergency_no");
		String perm_resd_add=request.getParameter("perm_resd_add");
		String curr_resd_add=request.getParameter("curr_resd_add");
		String perm_wrk_add=request.getParameter("perm_wrk_add");
		String curr_wrk_add=request.getParameter("curr_wrk_add");
		String pass=request.getParameter("pass");
		
		//login obj 
		
	
		 HttpSession session= request.getSession(true);
		 if("add".equals(op)){
				LoginInfo loginObj= new LoginInfo();
				loginObj.setUser_name(user_name);
				loginObj.setPassword(pass);
				loginObj.setRole_name(role_name);
				
				//user obj
				UserInfo userObj= new UserInfo();
				userObj.setUser_name(user_name);
				userObj.setFirst_name(first_name);
				userObj.setMiddle_name(middle_name);
				userObj.setLast_name(last_name);
				userObj.setEmail(email);
				userObj.setPhone_no(phone_no);
				userObj.setAlternate_no(alternate_no);
				userObj.setEmergency_no(emergency_no);
				userObj.setPerm_resd_add(perm_resd_add);
				userObj.setCurr_resd_add(curr_resd_add);
				userObj.setPerm_wrk_add(perm_wrk_add);
				userObj.setCurr_wrk_add(curr_wrk_add);
				// login table 
					int j=0;
					int i=0;
					Login_dao l= new Login_dao();
					i=l.doInsert(loginObj);
					//msg table
					User_dao u= new User_dao();
					j=u.insertNewUser(userObj);
					 if(j!=0 )
					    {
					    	System.out.println("record added successfully in pms_emp_details");
					    	//session.setAttribute("user_added", j);
					    }
						else
					    {
							System.out.println("last record didn't get addeed in pms_emp_details...");	
							
					    }
					 if(i!=0)
					    {
					    	System.out.println("record added successfully in pms_login");
					    	
					    }
						else
					    {
							System.out.println("last record didn't get addeed in pms_login...");	
					    }
					 session.setAttribute("user_added", i);
						RequestDispatcher rd= request.getRequestDispatcher("User_user.jsp");
						rd.forward(request, response);
				
			
		 }
		 else if("editstep1".equals(op)){
			 int i=0;
			 List li=null;
			 User_dao u= new User_dao();
			 li=u.getUserInfoEdit(user_name);
			 System.out.println("username got in ser is="+user_name);
		//	 String li1[]=null;
		//	 Iterator it= li.listIterator();
		//	 while(it.hasNext()){
		//		 li1[i]=(String) it.next();
		//		 i++;
		//	 }
			 session.setAttribute("editdata", li);
			 RequestDispatcher rd= request.getRequestDispatcher("User_edit.jsp");
			 rd.forward(request, response);
			 
		 }
		 else if("confirm edit".equals(op)){
			 
			 LoginInfo loginObj= new LoginInfo();
			 loginObj.setUser_name(user_name);
			 loginObj.setPassword(pass);
			 
			 
			 UserInfo userObj= new UserInfo();
			userObj.setUser_name(user_name);
			userObj.setFirst_name(first_name);
			userObj.setMiddle_name(middle_name);
			userObj.setLast_name(last_name);
			userObj.setEmail(email);
			userObj.setPhone_no(phone_no);
			userObj.setAlternate_no(alternate_no);
			userObj.setEmergency_no(emergency_no);
			userObj.setPerm_resd_add(perm_resd_add);
			userObj.setCurr_resd_add(curr_resd_add);
			userObj.setPerm_wrk_add(perm_wrk_add);
			userObj.setCurr_wrk_add(curr_wrk_add);
			
			
			// login table 
			int j=0;
			int i=0;
			Login_dao l= new Login_dao();
			i=l.doUpdate(loginObj);
			//msg table
			User_dao u= new User_dao();
			j=u.updateUser(userObj);
			 if(j!=0 )
			    {
			    	System.out.println("record edited successfully in pms_emp_details");
			    	//session.setAttribute("user_added", j);
			    }
				else
			    {
					System.out.println("last record didn't get edited in pms_emp_details...");	
					
			    }
			 if(i!=0)
			    {
			    	System.out.println("record edited successfully in pms_login");
			    	
			    }
				else
			    {
					System.out.println("last record didn't get edited in pms_login...");	
			    }
			session.setAttribute("user_edited", i);
			RequestDispatcher rd= request.getRequestDispatcher("User_user.jsp");
			rd.forward(request, response);
		
			 
		 }
		 else if("delete".equals(op)){
			 
			
			 int j=0;
				int i=0;
				User_dao u= new User_dao();
				j=u.deleteUser(user_name);
				
				Login_dao l= new Login_dao();
				i=l.doDelete(user_name);
				//msg table
				
				 if(j!=0 )
				    {
				    	System.out.println("record deleted successfully in pms_emp_details");
				    	//session.setAttribute("user_added", j);
				    }
					else
				    {
						System.out.println("last record didn't get deleted in pms_emp_details...");	
						
				    }
				 if(i!=0)
				    {
				    	System.out.println("record deleted successfully in pms_login");
				    	
				    }
					else
				    {
						System.out.println("last record didn't get deleted in pms_login...");	
				    }
				session.setAttribute("user_deleted", i);
				RequestDispatcher rd= request.getRequestDispatcher("User_user.jsp");
				rd.forward(request, response);
			
			 
		 }
    }
	

}
