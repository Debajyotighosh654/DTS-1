package dao;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ucon.Uconnection;

public class Report_dao {
	Uconnection mycon= new Uconnection();
	Connection cn=null;
		public void  getTempConnection(){
			
			cn=mycon.open();
			
		}
	
		private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			      Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			      Font.BOLD);

	    public void execRep()throws Exception
	    {
	    	
	       Document document=new Document();
	       PdfWriter.getInstance(document,new FileOutputStream("c:/DTSreports/DTSreport.pdf"));
	       document.open();
	       System.out.println("open");
	       
	       addMetaData(document);
		   addTitlePage(document);
		   
	       PdfPTable table=new PdfPTable(8);
	       table.addCell("Defect Id");
	       table.addCell("Defect Name");
	       table.addCell("Defect Details");
	       table.addCell("Start Date");
	       table.addCell("End Date");
	       table.addCell("Project ID");
	       table.addCell("Status");
	       table.addCell("Priority");
	    //   document.add(table);
	       Statement st=null;
			ResultSet rs=null;
	       	getTempConnection();
			st= cn.createStatement();
		
			String query_prj_alldata="select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, project_id, status, priority from dts_bug";
			rs=st.executeQuery(query_prj_alldata);
		
	       while(rs.next())
	       {
	       table.addCell(rs.getString("bug_id"));
	       table.addCell(rs.getString("bug_title"));
	       table.addCell(rs.getString("bug_desc"));
	       table.addCell(rs.getString("start_date"));
	       table.addCell(rs.getString("end_date"));
	       table.addCell(rs.getString("project_id"));
	       table.addCell(rs.getString("status"));
	       table.addCell(rs.getString("priority"));
	  
	    
	       }
	       document.add(table);
	       addTitlePage2(document);
	       // generate table 2
	       
	       PdfPTable table1=new PdfPTable(4);
	       table1.addCell("Employee First Name");
	       table1.addCell("Employee Middle Name");
	       table1.addCell("Employee Last Name");
	       table1.addCell("Employee Email");
	  
	   
	       	getTempConnection();
			st= cn.createStatement();
		
			String qry_collectUserData="select first_name, middle_name, last_name, email from dts_emp_details";
			rs=st.executeQuery(qry_collectUserData);
		
	       while(rs.next())
	       {
	       table1.addCell(rs.getString("first_name"));
	       table1.addCell(rs.getString("middle_name"));
	       table1.addCell(rs.getString("last_name"));
	       table1.addCell(rs.getString("email"));
	   
	    
	       }
	       document.add(table1);
	       
	       
	       
	       document.close();

	      
	    }
	    private void addTitlePage2(Document document) {
			// TODO Auto-generated method stub
	    	   Paragraph preface = new Paragraph();
		 	    // We add one empty line
		 	    addEmptyLine(preface, 1);
		 	    // Lets write a big header
		 	    preface = new Paragraph("DTS Yearly Employee Report", catFont);
		 	    preface.setAlignment(Element.ALIGN_CENTER);
		 	    preface.add(new Paragraph("Date: " + new Date(),smallBold));
		        preface.setAlignment(Element.ALIGN_CENTER);
		        addEmptyLine(preface, 1);
		 	    try {
					document.add(preface);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		public void addMetaData(Document document)
	 	  {
	 	    document.addTitle("DTS stats");
	 	    document.addSubject("Report");
	 	    document.addKeywords("Defects, Defects");
	 	    document.addAuthor("admin@DTS");
	 	    document.addCreator("admin@DTS");
	 	  }

	 	  public void addTitlePage(Document document)throws DocumentException
	 	  {
	 	    Paragraph preface = new Paragraph();
	 	    // We add one empty line
	 	    addEmptyLine(preface, 1);
	 	    // Lets write a big header
	 	    preface = new Paragraph("DTS Yearly Defect Report", catFont);
	 	    preface.setAlignment(Element.ALIGN_CENTER);
	 	    preface.add(new Paragraph("Date: " + new Date(),smallBold));
	        preface.setAlignment(Element.ALIGN_CENTER);
	        addEmptyLine(preface, 1);
	 	    document.add(preface);
	 	  }
	 	 public  void addEmptyLine(Paragraph paragraph, int number) 
	 	 {
	 	    for (int i = 0; i < number; i++) 
	 	    {
	 	      paragraph.add(new Paragraph(" "));
	 	    }
	     }
}
