package serv;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Projects_dao;

import bean.PrjInfo;

/**
 * Servlet implementation class Prj_ser
 */
@WebServlet("/Prj_ser")
public class Prj_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prj_ser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		String op=request.getParameter("submit"); 
		
		
		int i=0;
		HttpSession session= request.getSession(true);
		if("add".equals(op)){
			String project_id= request.getParameter("project_id");
			String project_name= request.getParameter("project_name");
			String project_details= request.getParameter("project_details");
			String client_id= request.getParameter("client_id");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			
			int status= Integer.parseInt(request.getParameter("status"));
			String[] user_name= request.getParameterValues("user_name");
			System.out.println("Members caught in th add are=");
			for(int j=0;j<user_name.length;j++){
				System.out.println(user_name[j]);
			}
			System.out.println("Other memebers avught are");
			System.out.println(project_name);
			System.out.println(project_id);
			System.out.println(project_details);
			System.out.println(start_date);
			System.out.println(end_date);
			System.out.println(status);
			System.out.println(client_id);
			System.out.println("Entering main for loop");
				for(int j=0;j<user_name.length;j++){
						
						PrjInfo obj= new PrjInfo();
						obj.setProject_id(project_id);
						obj.setProject_name(project_name);
						obj.setProject_details(project_details);
						obj.setClient_id(client_id);
						obj.setStatus(status);
						obj.setStart_date(start_date);
						obj.setEnd_date(end_date);
						obj.setUser_name(user_name[j]);
						Projects_dao p= new Projects_dao();
						i=p.insertNewProject(obj);
						if(i!=0){
							
							
							    	System.out.println("record added successfully in pms_project");
							    	//session.setAttribute("user_added", j);
							    }
								else
							    {
									System.out.println("last record didn't get addeed in pms_project...");	
									
							    }
					}
					session.setAttribute("project_added", i);
					RequestDispatcher rd= request.getRequestDispatcher("User_project.jsp");
					rd.forward(request, response);
		}
		else if("editstep1".equals(op)){
			 List li=null;
			 List li2=null;
			String project_id= request.getParameter("project_id");
			Projects_dao p= new Projects_dao();
			li=p.getProjInfoEdit(project_id);
			li2=p.getProjTeamInfo(project_id);
			 session.setAttribute("editprjdata", li);
			 session.setAttribute("editprjteamdata", li2);
			 RequestDispatcher rd= request.getRequestDispatcher("Proj_edit.jsp");
			 rd.forward(request, response);

		}
	
		else if("confirm update basic info".equals(op)){
			String project_id= request.getParameter("project_id");
			String project_name= request.getParameter("project_name");
			String project_details= request.getParameter("project_details");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			
			int status= Integer.parseInt(request.getParameter("status"));
		
			PrjInfo obj= new PrjInfo();
			obj.setProject_id(project_id);
			obj.setProject_name(project_name);
			obj.setProject_details(project_details);
			obj.setStatus(status);
			obj.setStart_date(start_date);
			obj.setEnd_date(end_date);
			Projects_dao p= new Projects_dao();
			i=p.editProjectBasicInfo(obj);
			if(i!=0 )
		    {
		    	System.out.println("record edited successfully in pms_project");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last record didn't get edited in pms_project...");	
				
		    }
			session.setAttribute("prj_edited", i);
			RequestDispatcher rd= request.getRequestDispatcher("User_project.jsp");
			rd.forward(request, response);
		}	
		else if("remove member".equals(op)){
			System.out.println("in remove meme");
			int k=0;
			String user_name=request.getParameter("user_name");
			String project_id=request.getParameter("project_id");
			System.out.println("username caught is="+user_name);
			Projects_dao p= new Projects_dao();
			k=p.deleteProjectMemember(user_name,project_id);
			System.out.println("value of k="+k);
			if(k!=0 )
		    {
		    	System.out.println("user removed successfully in pms_project");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last user didn't get removed in pms_project...");	
				
		    }
			session.setAttribute("prjMem_removed", k);
			RequestDispatcher rd= request.getRequestDispatcher("User_project.jsp");
			rd.forward(request, response);
		}	
		else if("close".equals(op)){
			int k=0;
			String project_id=request.getParameter("project_id");
			Projects_dao p= new Projects_dao();
			k=p.closeProject(project_id);
			if(k!=0 )
		    {
		    	System.out.println("project closed successfully in pms_project to pms_close_project");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last project didn't get closed in pms_project...");	
				
		    }
			session.setAttribute("prj_closed", k);
			RequestDispatcher rd= request.getRequestDispatcher("User_project.jsp");
			rd.forward(request, response);
		}		
		
		else if("add members".equals(op)){
			String project_id= request.getParameter("project_id");
			String project_name= request.getParameter("project_name");
			String project_details= request.getParameter("project_details");
			String client_id= request.getParameter("client_id");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			
			int status= Integer.parseInt(request.getParameter("status"));
			String[] user_name= request.getParameterValues("user_name");
			
			System.out.println("Members caught in th add members are=");
			for(int j=0;j<user_name.length;j++){
				System.out.println(user_name[j]);
			}
			System.out.println("Other memebers avught are");
			System.out.println(project_name);
			System.out.println(project_id);
			System.out.println(project_details);
			System.out.println(start_date);
			System.out.println(end_date);
			System.out.println(status);
			System.out.println(client_id);
			System.out.println("Entering main for loop");
				for(int j=0;j<user_name.length;j++){
						
						PrjInfo obj= new PrjInfo();
						obj.setProject_id(project_id);
						obj.setProject_name(project_name);
						obj.setProject_details(project_details);
						obj.setClient_id(client_id);
						obj.setStatus(status);
						obj.setStart_date(start_date);
						obj.setEnd_date(end_date);
						obj.setUser_name(user_name[j]);
						Projects_dao p= new Projects_dao();
						i=p.insertNewProject(obj);
						if(i!=0){
							
							
							    	System.out.println("users added successfully in pms_project");
							    	//session.setAttribute("user_added", j);
							    	System.out.println(i+"th time added");
							    }
								else
							    {
									System.out.println("last users didn't get addeed in pms_project...");	
									
							    }
					}
					session.setAttribute("project_users_added", i);
					RequestDispatcher rd= request.getRequestDispatcher("User_project.jsp");
					rd.forward(request, response);
		}	
		else if("team".equals(op)){
			 List li=null;
			 List li2=null;
			String project_id= request.getParameter("project_id");
			Projects_dao p= new Projects_dao();
			li=p.getProjInfoEdit(project_id);
			li2=p.getProjTeamInfo(project_id);
			 session.setAttribute("editprjdata", li);
			 session.setAttribute("editprjteamdata", li2);
			 RequestDispatcher rd= request.getRequestDispatcher("Proj_edit.jsp");
			 rd.forward(request, response);

		}
		
		
		
	}

	 
	 
} 
