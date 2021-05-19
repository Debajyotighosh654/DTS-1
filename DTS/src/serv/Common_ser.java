package serv;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import bean.Com_ser_fileInfo;
import dao.Com_ser_dao;


@WebServlet("/Common_ser")
public class Common_ser extends HttpServlet {
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
    
    
    @Override
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
        System.out.println("in Common_ser and action ="+ action);
        
        HttpSession session= request.getSession(true);
        
        Com_ser_fileInfo obj = new Com_ser_fileInfo();
        Com_ser_dao df = new Com_ser_dao();
		int rs = 0;
		
		if (action.equals("upload")) {
			System.out.println("in upload if");
    		String DESTINATION_DIR_PATH = "C:/common_server";
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
	          String file_name = null;
	          long filesize = 0l;
	          
	          while (itr.hasNext()) {
	        	  FileItem item = (FileItem) itr.next();
			      System.out.println("Field Name = " + item.getFieldName() + ",Field Value = " + item.getString());
		          
			      if (item.isFormField()) {
			    	  if(item.getFieldName().equals("file_id")) {
			    		  obj.setFile_id(Integer.parseInt(item.getString()));
			    	  }
			    	  else if(item.getFieldName().equals("file_desc")) {
			    		  obj.setFile_desc(item.getString());
			    	  }
			    	  else if(item.getFieldName().equals("user_name")) {
			    		  obj.setUser_name(item.getString());
			    	  }
			      }
			      else {
			    	  System.out.println( "File Name = " + item.getName() + ", Content type = " + item.getContentType() + ", File Size = " + item.getSize());
			    	  
			    	  String fileName = "";
			    	  
			    	  if(item.getName()!="") {
			    		  if (item.getName().lastIndexOf("\\") != -1) {
			    			  fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().length());
			    		  }
			    		  else {
			    			  fileName = item.getName();
			    		  }
			    		  
			    		  File file = new File(destinationDir, fileName);
						  item.write(file);
						  
						  file_name= item.getName();
						  filesize= item.getSize();
			    	  }
			    	  else {
			    		  file_name="NA";
			    		  filesize=0;
			    	  }
			      }
	          }
	          obj.setFile_name(file_name);
	          obj.setFile_size(filesize);
	          rs = df.insertNewFile(obj);
	          
	          if(rs != 0) {
	        	  System.out.println("record added successfully in pms_common_server");
	        	  session.setAttribute("file_added", rs);
	          }
	          else {
	        	  System.out.println("last file didn't get addeed in pms_common_server...");
	          }
	          response.sendRedirect("Common_server.jsp");	
		}
		
		
		
		else if (action.equals("download")) {
			String pathName = "C:/common_server";
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
		
		
		else if (action.equals("remove")) {
			int i = 0;
			int file_id = Integer.parseInt(request.getParameter("file_id"));
			Com_ser_dao ob = new Com_ser_dao();
   		 	i = ob.deleteFile(file_id);
   		 	System.out.println("value of i = " + i);
   		 	
   		 
   		 	if(i != 0 ) {
   		 		System.out.println("file removed successfully in pms_common_server");
   		 	}
   		 	else {
   		 		System.out.println("last file didn't get removed in pms_common_server");	
   		 	}
   		 	
   		 	session.setAttribute("file_removed", i);
			RequestDispatcher rd= request.getRequestDispatcher("Common_server.jsp");
			rd.forward(request, response);
		}
		
		
    }

}
