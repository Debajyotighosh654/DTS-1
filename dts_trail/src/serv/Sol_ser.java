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

import dao.Sol_dao;
import bean.SolInfo;

/**
 * Servlet implementation class Sol_ser
 */
@WebServlet("/Sol_ser")
public class Sol_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sol_ser() {
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
	
		if("add".equals(op)){
			int i=0;
			int sol_id= Integer.parseInt(request.getParameter("sol_id"));
			String sol_title= request.getParameter("sol_title");
			String sol_desc= request.getParameter("sol_desc");
			String sol_date= request.getParameter("sol_date");
			int status= Integer.parseInt(request.getParameter("status"));
			long bug_id= Integer.parseInt(request.getParameter("bug_id"));
			
			System.out.println("Members to caught in th add are=");
			System.out.println(sol_title);
			System.out.println(sol_id);
			System.out.println(sol_desc);
			System.out.println(sol_date);
			System.out.println(bug_id);
			System.out.println(status);
			
			

			SolInfo obj= new SolInfo();
			obj.setSol_id(sol_id);
			obj.setSol_title(sol_title);
			obj.setSol_desc(sol_desc);
			obj.setSol_date(sol_date);
			obj.setBug_id(bug_id);
			obj.setStatus(status);
			Sol_dao p= new Sol_dao();
			i=p.insertNewSol(obj);
			if(i!=0){
				
				
				    	System.out.println("record added successfully in dts_Sol");
				    	//session.setAttribute("user_added", j);
				    }
					else
				    {
						System.out.println("last record didn't get addeed in dts_Sol...");	
						
				    }
		
		session.setAttribute("sol_added", i);
		RequestDispatcher rd= request.getRequestDispatcher("Sol_Home.jsp");
		rd.forward(request, response);
		}
		else if("editstep1".equals(op)){
			int i=0;
			 List li=null;
			 List li2=null;
			 int sol_id= Integer.parseInt(request.getParameter("sol_id"));
			 Sol_dao p= new Sol_dao();
			 li=p.getSolInfoEdit(sol_id);
			 session.setAttribute("editSoldata", li);
			 RequestDispatcher rd= request.getRequestDispatcher("Sol_details.jsp");
			 rd.forward(request, response);

		}
	
		else if("confirm update basic info".equals(op)){
			int i=0;
			int sol_id= Integer.parseInt(request.getParameter("sol_id"));
			String sol_title= request.getParameter("sol_title");
			String sol_desc= request.getParameter("sol_desc");
			String sol_date= request.getParameter("sol_date");
			int status= Integer.parseInt(request.getParameter("status"));
		
			SolInfo obj= new SolInfo();
			obj.setSol_id(sol_id);
			obj.setSol_title(sol_title);
			obj.setSol_desc(sol_desc);
			obj.setSol_date(sol_date);
			obj.setStatus(status);
			Sol_dao p= new Sol_dao();
			i=p.editSolBasicInfo(obj);
			if(i!=0 )
		    {
		    	System.out.println("record edited successfully in dts_Sol");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last record didn't get edited in dts_Sol...");	
				
		    }
			session.setAttribute("sol_edited", i);
			RequestDispatcher rd= request.getRequestDispatcher("Sol_Home.jsp");
			rd.forward(request, response);
		}	
		else if("remove".equals(op)){
			int i=0;
			System.out.println("in remove sol");
			int k=0;
			int sol_id= Integer.parseInt(request.getParameter("sol_id"));
			System.out.println("sol caught is="+sol_id);
			Sol_dao p= new Sol_dao();
			k=p.deleteSol(sol_id);
			System.out.println("value of k="+k);
			if(k!=0 )
		    {
		    	System.out.println("user removed successfully in dts_Sol");
		    	//session.setAttribute("user_added", j);
		    }
			else
		    {
				System.out.println("last user didn't get removed in dts_Sol...");	
				
		    }
			session.setAttribute("sol_removed", k);
			RequestDispatcher rd= request.getRequestDispatcher("Sol_Home.jsp");
			rd.forward(request, response);
		}	
	}
	 
} 
