package serv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Bug_dao;
import dao.Messages_dao;
import dao.Projects_dao;
import dao.Sol_dao;

/**
 * Servlet implementation class Desktop_ser
 */
@WebServlet("/Desktop_ser")
public class Desktop_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Desktop_ser() {
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
		HttpSession s= request.getSession();
		System.out.println(s.getAttribute("user_logged"));
		String db_username=(String) s.getAttribute("user_logged");
		System.out.println(s.getAttribute("user_role"));
		String db_rolename=(String) s.getAttribute("user_role");
		
		List li_prj=null;
		List li_msg=null;
		List li_sol=null;
		List li_bug=null;
		List li_sol_all=null;
		List li_bug_all=null;
		List li_msg_all=null;
		List li_prj_sts=null;
		int admin_yes=0;
		int client_yes=0;
		int admin_yes_all_rights=0;
		Projects_dao prj= new Projects_dao();
		Messages_dao msg=new Messages_dao();
		Sol_dao sol= new Sol_dao();
		Bug_dao bug= new Bug_dao();
		if(db_rolename.equals("ADMIN")){
			
			
			try {
				li_prj=prj.retriveAllProjectDataAdmin(db_username);
				li_msg=msg.retriveNewMessages(db_username);
				li_sol_all=sol.retriveAllSol(db_username);
				li_bug_all=bug.retriveAllBug(db_username);
				admin_yes=1;
				admin_yes_all_rights=1;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		if(db_rolename.equals("CL")){try {
			li_prj=prj.retriveProjectList(db_username);
			li_sol=sol.retriveSol(db_username);
			li_bug=bug.retriveBug(db_username);
			admin_yes=0;
			client_yes=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg=msg.retriveNewMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg_all=msg.retriveAllMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	}
		if(db_rolename.equals("SE")){try {
			li_prj=prj.retriveProjectList(db_username);
			li_msg=msg.retriveNewMessages(db_username);
			li_msg_all=msg.retriveAllMessages(db_username);
			//li_prj_sts=prj.retriveProjectSts(db_username);
			li_sol=sol.retriveSol(db_username);
			li_bug=bug.retriveBug(db_username);
			admin_yes=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(db_rolename.equals("SSE")){try {
			li_prj=prj.retriveProjectList(db_username);
			li_sol=sol.retriveSol(db_username);
			li_bug=bug.retriveBug(db_username);
			admin_yes=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg=msg.retriveNewMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg_all=msg.retriveAllMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   }
		if(db_rolename.equals("DA")){  }
		if(db_rolename.equals("SDA")){try {
			li_prj=prj.retriveProjectList(db_username);
			li_sol=sol.retriveSol(db_username);
			li_bug=bug.retriveBug(db_username);
			admin_yes=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg=msg.retriveNewMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg_all=msg.retriveAllMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  }
		if(db_rolename.equals("TL")){try {
			li_prj=prj.retriveProjectList(db_username);
			li_sol=sol.retriveSol(db_username);
			li_bug=bug.retriveBug(db_username);
			admin_yes=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg=msg.retriveNewMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			li_msg_all=msg.retriveAllMessages(db_username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		if(db_rolename.equals("PM")){ }
		if(db_rolename.equals("PPM")){ }
		if(db_rolename.equals("DM")){ }
		if(db_rolename.equals("HRM")){ }
		
		s.setAttribute("project_list", li_prj);
		s.setAttribute("newmsg_list", li_msg);
		s.setAttribute("allmsg_list", li_msg_all);
		s.setAttribute("admin_yes", admin_yes);
		s.setAttribute("client_yes", client_yes);
		s.setAttribute("main_admin", admin_yes_all_rights);
		s.setAttribute("allbug_list", li_bug_all);
		s.setAttribute("allsol_list", li_sol_all);
		s.setAttribute("bug_list", li_bug);
		s.setAttribute("sol_list", li_sol);
		//s.setAttribute("project_sts_list", li_prj_sts);
		RequestDispatcher rd= request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}
}