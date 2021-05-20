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

/**
 * Servlet implementation class Msg_ser
 */
@WebServlet("/Msg_ser")
public class Msg_ser extends HttpServlet {
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
	 
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        String action = request.getParameter("action");
	        System.out.println("in Msg_ser and action ="+ action);
	        
	        HttpSession session= request.getSession(true);
		//	Connect con= new Connect();
		//	Connection cn=con.connect();
	        MsgInfo obj= new MsgInfo();
	        Messages_dao df= new Messages_dao();
			int rs=0;
	        try {
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
			        try {
			            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			            fileItemFactory.setSizeThreshold(1 * 1024 * 1024); //1 MB
			            fileItemFactory.setRepository(tmpDir);
			            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
			          
				        try {
						          List items = uploadHandler.parseRequest(request);
								                 
						          Iterator itr = items.iterator();
						          String file_name=null;
						          long filesize= 0l;
					              while (itr.hasNext())
					              {
									      FileItem item = (FileItem) itr.next();
									      System.out.println("Field Name = " + item.getFieldName() + ",Field Value = " + item.getString());
								          if (item.isFormField()) 
								          {
								        	  if(item.getFieldName().equals("to_user")){
							                    	
							                    	//System.out.println("in student_id");
							                    	obj.setTo_user(item.getString());
							                    	//session.setAttribute("student_id", item.getString());
							                    }
                                             else if(item.getFieldName().equals("from_user")){
							                    	
							                    	//System.out.println("in asg_name");
							                    	//session.setAttribute("asg_name", item.getString());
                                          	   obj.setFrom_user(item.getString());
							                    } 
                                           else if(item.getFieldName().equals("msgtitle")){
							                    	//System.out.println("in batch_id");
							                    	//session.setAttribute("batch_id", item.getString());
							                    	obj.setMsg_title(item.getString());
							                    }
                                           else if(item.getFieldName().equals("msgarea")){
							                    	//System.out.println("in batch_id");
							                    	//session.setAttribute("batch_id", item.getString());
							                    	obj.setMsg_desc(item.getString());
                                             
								          
								          }
								          }     
									     else 
									     { 
												    System.out.println(
										            "File Name = " + item.getName()
										            + ", Content type = " + item.getContentType()
					                                + ", File Size = " + item.getSize());
										
												    String fileName = "";
												    
												    if(item.getName()!=""){
												    
												    if (item.getName().lastIndexOf("\\") != -1)
												     {
												             fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().length());
											         } 
												     else 
												     {
												             fileName = item.getName();
										             }
												    
												    File file = new File(destinationDir, fileName);
												    item.write(file);
												                   
												    file_name= item.getName();
												    filesize= item.getSize();
												    }
												    else{
												    	
												    	file_name="NA";
												    	filesize=0;
												    }
										   }
					              	}
					          //  System.out.println("AFter While before calling DAO");
						       // session.setAttribute("file_name", file_name);
						      //  session.setAttribute("filesize", filesize);	    
						        
						     //   rs=Asg_dao.add(cn, session);
					          
					            obj.setAttch_file_name(file_name);
					            obj.setAttch_file_size(filesize);
					            rs=df.insertNewMsg(obj);                 
							    if(rs!=0)
							    {
							    	System.out.println("record added successfully");
							    	session.setAttribute("msg_sent", rs);
							    }
								else
							    {
									System.out.println("last record didn't get addeed...");	
							    }
								                   
								response.sendRedirect("User_msg.jsp");
					              }
						      
		        		catch (Exception e) {
					                
					      }
			           } catch (Exception e) {
			               System.out.println(e);
			           }
	        	} else if (action.equals("download")) {
				        String pathName = "C:/msg";
				        String docName = request.getParameter("docName");
				        String docSize = request.getParameter("docSize");
				        File file = new File(pathName+"\\"+docName);
		                fis = new FileInputStream(file);
		                /*
		                 * A FileInputStream obtains input bytes from a file in a file system. What files are available depends on the host environment.
							FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, 
							consider using FileReader
		                 * FileInputStream(File file)
	          Creates a FileInputStream by opening a connection to an actual file, the file named by the File object file in the file system.
	          
		                 */
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
		                /*
		                 * Provides an output stream for sending binary data to the client. A ServletOutputStream object is normally 
		                 * retrieved via the ServletResponse.getOutputStream() method.
							This is an abstract class that the servlet container implements. Subclasses of this class must 
							implement the java.io.OutputStream.write(int) method
							void 	flush()
					          Flushes this output stream and forces any buffered output bytes to be written out.
					       void 	write(byte[] b)
					          Writes b.length bytes from the specified byte array to this output stream.
					           void 	close()
	          				Closes this output stream and releases any system resources associated with this stream.
		                 */
	        	}
			} 
	        finally {
		        
	        }
		}
		 
	    @Override

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
	 
	    @Override
	    public String getServletInfo() {
	        return "Short description";
		    }

	}


