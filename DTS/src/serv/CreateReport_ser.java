package serv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.DocumentException;

import dao.Report_dao;


@WebServlet("/CreateReport_ser")
public class CreateReport_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Report_dao o = new Report_dao();
		try {
			o.execRep();
		} catch (FileNotFoundException | ClassNotFoundException | DocumentException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession s= request.getSession();
		s.setAttribute("report_added", 1);
		response.sendRedirect("Reports.jsp");
	}

}
