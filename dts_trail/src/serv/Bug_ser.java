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

import dao.Bug_dao;
import bean.BugInfo;

/**
 * Servlet implementation class Bug_ser
 */
@WebServlet("/Bug_ser")
public class Bug_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bug_ser() {
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
		String op=request.getParameter("submitb"); 
		
		
		
		HttpSession session= request.getSession(true);
		if("route".equals(op)){
			String project_id= request.getParameter("selected_prj_id");
			session.setAttribute("sel_prj", project_id);
			RequestDispatcher rd= request.getRequestDispatcher("Bug_Home.jsp");
			rd.forward(request, response);
		}
		else if("add".equals(op)){
			int i=0;
			int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			String bug_title= request.getParameter("bug_title");
			String bug_desc= request.getParameter("bug_desc");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			String[] bug_to= request.getParameterValues("bug_to");
			String bug_from= request.getParameter("bug_from");
			String project_id= request.getParameter("project_id");
			int status= Integer.parseInt(request.getParameter("status"));
			String priority= request.getParameter("priority");
			
			System.out.println("Members to caught in th add are=");
			for(int j=0;j<bug_to.length;j++){
				System.out.println(bug_to[j]);
			}
			System.out.println("Other memebers avught are");
			System.out.println(bug_title);
			System.out.println(bug_id);
			System.out.println(bug_desc);
			System.out.println(start_date);
			System.out.println(end_date);
			System.out.println(bug_from);
			System.out.println(project_id);
			System.out.println(status);
			System.out.println(priority);
			

			System.out.println("Entering main for loop");
				for(int j=0;j<bug_to.length;j++){
						
						BugInfo obj= new BugInfo();
						obj.setBug_id(bug_id);
						obj.setBug_title(bug_title);
						obj.setBug_desc(bug_desc);
						obj.setStart_date(start_date);
						obj.setEnd_date(end_date);
						obj.setBug_to(bug_to[j]);
						obj.setBug_from(bug_from);
						obj.setProject_id(project_id);
						obj.setStatus(status);
						obj.setPriority(priority);
						Bug_dao p= new Bug_dao();
						i=p.insertNewBug(obj);
						if(i!=0){
							
							
							    	System.out.println("record added successfully in dts_Bug");
							    	//session.setAttribute("user_added", j);
							    }
								else
							    {
									System.out.println("last record didn't get addeed in dts_Bug...");	
									
							    }
					}
					session.setAttribute("bug_added", i);
					RequestDispatcher rd= request.getRequestDispatcher("Bug_Home.jsp");
					rd.forward(request, response);
		}
		else if("editstep1".equals(op)){
			int i=0;
			 List li=null;
			 List li2=null;
			 int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			 Bug_dao p= new Bug_dao();
			 li=p.getBugInfoEdit(bug_id);
			 li2=p.getBugTeamInfo(bug_id);
			 session.setAttribute("editBugdata", li);
			 session.setAttribute("editBugteamdata", li2);
			 RequestDispatcher rd= request.getRequestDispatcher("Bug_details.jsp");
			 rd.forward(request, response);

		}
	
		else if("confirm update basic info".equals(op)){
			int i=0;
			int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			String bug_title= request.getParameter("bug_title");
			String bug_desc= request.getParameter("bug_desc");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			int status= Integer.parseInt(request.getParameter("status"));
		
			BugInfo obj= new BugInfo();
			obj.setBug_id(bug_id);
			obj.setBug_title(bug_title);
			obj.setBug_desc(bug_desc);
			obj.setStart_date(start_date);
			obj.setEnd_date(end_date);
			obj.setStatus(status);
			Bug_dao p= new Bug_dao();
			i=p.editBugBasicInfo(obj);
			if(i!=0 )
		    {
		    	System.out.println("record edited successfully in dts_Bug");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last record didn't get edited in dts_Bug...");	
				
		    }
			session.setAttribute("bug_edited", i);
			RequestDispatcher rd= request.getRequestDispatcher("Bug_Home.jsp");
			rd.forward(request, response);
		}	
		else if("remove member".equals(op)){
			int i=0;
			System.out.println("in remove meme");
			int k=0;
			String bug_to=request.getParameter("bug_to");
			int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			System.out.println("username caught is="+bug_to);
			Bug_dao p= new Bug_dao();
			k=p.deleteBugMemember(bug_to,bug_id);
			System.out.println("value of k="+k);
			if(k!=0 )
		    {
		    	System.out.println("user removed successfully in dts_Bug");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last user didn't get removed in dts_Bug...");	
				
		    }
			session.setAttribute("bugMem_removed", k);
			RequestDispatcher rd= request.getRequestDispatcher("Bug_Home.jsp");
			rd.forward(request, response);
		}	
	
		else if("add members".equals(op)){
			int i=0;
			int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			String bug_title= request.getParameter("bug_title");
			String bug_desc= request.getParameter("bug_desc");
			String start_date= request.getParameter("start_date");
			String end_date= request.getParameter("end_date");
			String[] bug_to= request.getParameterValues("bug_to");
			String bug_from= request.getParameter("bug_from");
			String project_id= request.getParameter("project_id");
			int status= Integer.parseInt(request.getParameter("status"));
			String priority= request.getParameter("priority");
			
			System.out.println("Members to caught in th add are=");
			for(int j=0;j<bug_to.length;j++){
				System.out.println(bug_to[j]);
			}
			System.out.println("Other memebers avught are");
			System.out.println(bug_title);
			System.out.println(bug_id);
			System.out.println(bug_desc);
			System.out.println(start_date);
			System.out.println(end_date);
			System.out.println(bug_from);
			System.out.println(project_id);
			System.out.println(status);
			System.out.println(priority);
			

			System.out.println("Entering main for loop");
				for(int j=0;j<bug_to.length;j++){
						
						BugInfo obj= new BugInfo();
						obj.setBug_id(bug_id);
						obj.setBug_title(bug_title);
						obj.setBug_desc(bug_desc);
						obj.setStart_date(start_date);
						obj.setEnd_date(end_date);
						obj.setBug_to(bug_to[j]);
						obj.setBug_from(bug_from);
						obj.setProject_id(project_id);
						obj.setStatus(status);
						obj.setPriority(priority);
						Bug_dao p= new Bug_dao();
						i=p.insertNewBug(obj);
						System.out.println("value of i="+i);
						if(i==0){
							
							
							    	System.out.println("record added successfully in dts_Bug");
							    	//session.setAttribute("user_added", j);
							    }
								else
							    {
									System.out.println("last record didn't get addeed in dts_Bug...");	
									
							    }
					}
					session.setAttribute("bug_users_added", i);
					session.setAttribute("bug_duplicate_users_added", i);
					RequestDispatcher rd= request.getRequestDispatcher("Bug_Home.jsp");
					rd.forward(request, response);
		}	
		else if("team".equals(op)){
			 List li=null;
			 List li2=null;
				int bug_id= Integer.parseInt(request.getParameter("bug_id"));
			Bug_dao p= new Bug_dao();
			 li=p.getBugInfoEdit(bug_id);
			 li2=p.getBugTeamInfo(bug_id);
			 session.setAttribute("editBugdata", li);
			 session.setAttribute("editBugteamdata", li2);
			 RequestDispatcher rd= request.getRequestDispatcher("Bug_details.jsp");
			 rd.forward(request, response);

		}
		
		
		
	}

	 
	 
} 
