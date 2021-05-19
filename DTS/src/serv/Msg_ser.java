package serv;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.MsgInfo;
import dao.Messages_dao;


@WebServlet("/Msg_ser")
public class Msg_ser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static final String TMP_DIR_PATH = "c:\\tmp";
    private File tmpDir;
    private File destinationDir;
    DataInputStream dis = null;
    BufferedInputStream bis = null;
    FileInputStream fis = null;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tmpDir = new File(TMP_DIR_PATH);
        if (!tmpDir.isDirectory()) {
            tmpDir.mkdir();
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
    	String action = request.getParameter("action");
        System.out.println("in Msg_ser and action = " + action);
        
        HttpSession session = request.getSession(true);
        
        MsgInfo obj = new MsgInfo();
        Messages_dao df = new Messages_dao();
		int rs = 0;
		
		if (action.equals("upload")) {
			System.out.println("in upload if");
			
    		String DESTINATION_DIR_PATH = "C:/msg";
    		String realPath = DESTINATION_DIR_PATH;
    		System.out.println("Real Path " + realPath);
    		
    		destinationDir = new File(realPath);
    		
    		if (!destinationDir.isDirectory()) {
        		destinationDir.mkdir();
        	}
    		
    		response.setContentType("text/html");
    		
    		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            fileItemFactory.setSizeThreshold(1 * 1024 * 1024); //1 MB
            fileItemFactory.setRepository(tmpDir);
            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            
            List items = uploadHandler.parseRequest(request);
            
	          Iterator itr = items.iterator();
	          String file_name =null;
	          long filesize = 0l;
	          
	          while (itr.hasNext()) {
	        	  FileItem item = (FileItem) itr.next();
			      System.out.println("Field Name = " + item.getFieldName() + ",Field Value = " + item.getString());
		          
			      if (item.isFormField()) {
			    	  if(item.getFieldName().equals("to_user")) {
			    		  obj.setTo_user(item.getString());
			    	  }
			    	  else if(item.getFieldName().equals("from_user")) {
			    		  obj.setFrom_user(item.getString());
			    	  }
			    	  else if(item.getFieldName().equals("msgtitle")) {
			    		  obj.setMsg_title(item.getString());
			    	  }
			    	  else if(item.getFieldName().equals("msgarea")) {
			    		  obj.setMsg_desc(item.getString());
			    	  }
			      }
			      else {
			    	  System.out.println("File Name = " + item.getName() + ", Content type = " + item.getContentType() + ", File Size = " + item.getSize());
			    	  String fileName = "";
			    	  
			    	  if(item.getName()!="") {
			    		  if (item.getName().lastIndexOf("\\") != -1) {      		    			  
			    			  fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().length());					         
			    		  } 					    
			    		  else  {						            
			    			  fileName = item.getName();         
			    		  }
						    
			    		  File file = new File(destinationDir, fileName);
			    		  item.write(file);
						                 
			    		  file_name = item.getName();						    
			    		  filesize = item.getSize();
			    	  }   
			    	  else {					    
			    		  file_name="NA";
			    		  filesize=0;						    
			    	  }
			      }
	          }
	          
	          obj.setAttach_file_name(file_name);
	          obj.setAttach_file_size(filesize);
	          rs = df.insertNewMsg(obj);
	          
	          if(rs != 0) {
	        	  System.out.println("record added successfully");
	        	  session.setAttribute("msg_sent", rs);
	          }
	          else {
	        	  System.out.println("last record didn't get addeed...");
	          }
	          
	          response.sendRedirect("User_msg.jsp");
		}
		
		
		
		else if (action.equals("download")) {
			String pathName = "C:/msg";
	        String docName = request.getParameter("docName");
	        String docSize = request.getParameter("docSize");
	        File file = new File(pathName+"\\"+docName);
            fis = new FileInputStream(file);
            
            byte[] buf = new byte[Integer.valueOf(docSize)];
            int offset = 0;
            int numRead = 0;
            
            while ((offset < buf.length)&& ((numRead = fis.read(buf, offset, buf.length - offset)) >= 0)) {
            	offset += numRead;
            }
            fis.close();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + docName+"");
            ServletOutputStream outputStream =  response.getOutputStream();
            outputStream.write(buf);
            outputStream.flush();
            outputStream.close();
		}   	
    }
    
    
    public String getServletInfo() {
        return "Short description";
	    }

}
