<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Description
Description: A three-column, fixed-width blog design.
Version    : 1.0
Released   : 20071004

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>DTS</title>
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<style>
#logo_img{
	float: left;
}
#login_info{
	margin-top: 70px;
	float: right;
	
	border-bottom-style: none;
}
#login_info a{
	border-bottom-width:thin;
	border-bottom-style: groove;
}
$st_table{
	float: right;
}
}
</style>
 
<body>
<!-- start header -->
 
<div id="logo">
	<img src="images/dts_logo.png" id="logo_img">
	<h1><a href="#">DTS</a></h1>
	<p>defects simplified</p>
	<div id="login_info">welcome, <%=session.getAttribute("user_logged") %><a href="Home.jsp" onclick="history.go(1);"> logout </a></div>
</div>

<div id="menu">
	<ul id="main">
		<li class="current_page_item"><a href="main.jsp">Homepage</a></li>
		<li><a href="User_project.jsp">Projects</a></li>
		<li><a href="Sol_Home.jsp">Solutions</a></li>
		<li><a href="Bug_Home.jsp">Defects</a></li>
		<li><a href="User_msg.jsp">Messages</a></li>
		<li><a href="User_user.jsp">Users</a></li>
		<li><a href="File.jsp">Files</a></li>
		<li><a href="Common_server.jsp">Common Server</a></li>
		<li><a href="Reports.jsp">Reports</a></li>
	</ul>
	
</div>
<!-- end header -->

<div id="wrapper">
<!-- start page -->
<div id="page">

	<!-- start content -->
	<div id="content">
	<%--<c:out value="${sessionScope.user_logged}"/>
	  	<%=session.getAttribute("user_logged") %>
		<%=session.getAttribute("user_role") %>  --%>
	
		<c:if test="${sessionScope.main_admin==1 }">		
		<h2>Project Information</h2>
	<table border="1">
	<tr>
 		<th>Project Id</th>
 		<th>Project Name</th>
 		<th>Details</th>
 		<th>Start Date</th>
 		<th>End Date</th>
 		<th>Completion Status(%)</th>
 	</tr>
    	<c:forEach items="${sessionScope.project_list}" var="currentName" varStatus="status" begin="0" step="6">
            
            
              <tr>
             		
              
			   		 <c:forEach items="${sessionScope.project_list}" var="currentName1" varStatus="status1" begin="${status.index}" end="${status.index+5}">   
	               
	              				<td><c:out value="${currentName1}" /></td>
	             
	        		</c:forEach>
        	
        		
        	 
    		  </tr>
  		 
  		  </c:forEach>
        
 </table>
 
    
      </c:if>
      <c:if test="${sessionScope.client_yes==1 }">		
		<h2>Project Information</h2>
	<table border="1">
	<tr>
 		<th>Project Id</th>
 		<th>Project Name</th>
 		<th>Details</th>
 		<th>Start Date</th>
 		<th>End Date</th>
 		<th>Completion Status(%)</th>
 	</tr>
    	<c:forEach items="${sessionScope.project_list}" var="currentName" varStatus="status" begin="0" step="6">
            
            
              <tr>
             		
              
			   		 <c:forEach items="${sessionScope.project_list}" var="currentName1" varStatus="status1" begin="${status.index}" end="${status.index+5}">   
	               
	              				<td><c:out value="${currentName1}" /></td>
	             
	        		</c:forEach>
        	
        		
        	 
    		  </tr>
  		 
  		  </c:forEach>
        
 </table>
 
    
      </c:if>
       
  <br><br>

<h2>New Messages</h2>
<table border="1">
	
 	<tr>
 		<th>Title</th>
 		<th>Description</th>
 		<th>Attachment</th>
 		<th>size</th>
 		<th>From</th>
 		<th>Time</th>
 	</tr>
 	
 	<c:forEach items="${sessionScope.newmsg_list}" var="currentName" varStatus="status" begin="0" step="6">
            
              <tr>
		   		 <c:forEach items="${sessionScope.newmsg_list}" var="currentName1" varStatus="status1" begin="${status.index}" end="${status.index+5}">   
               
              				<td><c:out value="${currentName1}" /></td>
             
        		</c:forEach>
       
    		  </tr>
    
         </c:forEach>
        
     
        

</table>
<c:if test="${sessionScope.main_admin!=1 }">	
<br>
					<h2>High Priority Defects</h2>
		      		<table id="user_table" border="1">
					<tr>
						<th>Defect ID</th>
						<th>Defect Name</th>
						<th>Defect Description</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Defect From</th>
						<th>Project Id</th>
						<th>Status</th>
						<th>Priority</th>
						<th>Details</th>
					</tr>
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, bug_from, project_id, status, priority from dts_bug where priority='high' and project_id in(select project_id from dts_project where user_name='${sessionScope.user_logged }')</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.bug_id}"/></td>
					      		<td><c:out value="${row.bug_title}"/></td>
					      		<td><c:out value="${row.bug_desc}"/></td>
					      		<td><c:out value="${row.start_date}"/></td>
					      		<td><c:out value="${row.end_date}"/></td>
					      		<td><c:out value="${row.bug_from}"/></td>
					      		<td><c:out value="${row.project_id}"/></td>
					      		<td><c:out value="${row.status}"/></td>
					      		<td><c:out value="${row.priority}"/></td>
					     		<td><a href="<%=request.getContextPath()%>/Bug_ser?bug_id=${row.bug_id }&submitb=team">Details</a></td>
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
					<h2>New Defects</h2>
					<table id="adm_table" border="1">
					<tr>
						<th>Defect ID</th>
						<th>Defect Name</th>
						<th>Defect Description</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Defect From</th>
						<th>Project Id</th>
						<th>Status</th>
						<th>Priority</th>
						<th>Details</th>
					</tr>
					<%
					java.util.Date dt = new java.util.Date();
					dt.setHours(0);
					dt.setMinutes(0);
					dt.setSeconds(0);
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String currentDay = sdf.format(dt);
					System.out.println(currentDay);
				
					%>
					<c:set var="todayTimeoo" value="<%=currentDay %>" scope="session"></c:set>
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, bug_from, project_id, status, priority from dts_bug where start_date>'${sessionScope.todayTimeoo }' and  project_id in(select project_id from dts_project where user_name='${sessionScope.user_logged }')</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.bug_id}"/></td>
					      		<td><c:out value="${row.bug_title}"/></td>
					      		<td><c:out value="${row.bug_desc}"/></td>
					      		<td><c:out value="${row.start_date}"/></td>
					      		<td><c:out value="${row.end_date}"/></td>
					      		<td><c:out value="${row.bug_from}"/></td>
					      		<td><c:out value="${row.project_id}"/></td>
					      		<td><c:out value="${row.status}"/></td>
					      		<td><c:out value="${row.priority}"/></td>
					     		<td><a href="<%=request.getContextPath()%>/Bug_ser?bug_id=${row.bug_id }&submitb=editstep1">Details</a></td>
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
		      	<h2>Unsolved Defects</h2>
		      		<table id="user_table" border="1">
					<tr>
						<th>Defect ID</th>
						<th>Defect Name</th>
						<th>Defect Description</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Defect From</th>
						<th>Project Id</th>
						<th>Status</th>
						<th>Priority</th>
						<th>Details</th>
					</tr>
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, bug_from, project_id, status, priority from dts_bug where status=0 and project_id in(select project_id from dts_project where user_name='${sessionScope.user_logged }')</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.bug_id}"/></td>
					      		<td><c:out value="${row.bug_title}"/></td>
					      		<td><c:out value="${row.bug_desc}"/></td>
					      		<td><c:out value="${row.start_date}"/></td>
					      		<td><c:out value="${row.end_date}"/></td>
					      		<td><c:out value="${row.bug_from}"/></td>
					      		<td><c:out value="${row.project_id}"/></td>
					      		<td><c:out value="${row.status}"/></td>
					      		<td><c:out value="${row.priority}"/></td>
					     		<td><a href="<%=request.getContextPath()%>/Bug_ser?bug_id=${row.bug_id }&submitb=team">Details</a></td>
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
		      <h2>Solved Defects</h2>
		      		<table id="user_table" border="1">
					<tr>
						<th>Defect ID</th>
						<th>Defect Name</th>
						<th>Defect Description</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Defect From</th>
						<th>Project Id</th>
						<th>Status</th>
						<th>Priority</th>
						<th>Details</th>
					</tr>
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">select DISTINCT bug_id, bug_title, bug_desc, start_date, end_date, bug_from, project_id, status, priority from dts_bug where status=1 and project_id in(select project_id from dts_project where user_name='${sessionScope.user_logged }')</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.bug_id}"/></td>
					      		<td><c:out value="${row.bug_title}"/></td>
					      		<td><c:out value="${row.bug_desc}"/></td>
					      		<td><c:out value="${row.start_date}"/></td>
					      		<td><c:out value="${row.end_date}"/></td>
					      		<td><c:out value="${row.bug_from}"/></td>
					      		<td><c:out value="${row.project_id}"/></td>
					      		<td><c:out value="${row.status}"/></td>
					      		<td><c:out value="${row.priority}"/></td>
					     		<td><a href="<%=request.getContextPath()%>/Bug_ser?bug_id=${row.bug_id }&submitb=team">Details</a></td>
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
		      		</c:if>
	</div>
	<!-- end content -->
	<!-- start sidebars -->
	
	
	<!-- end sidebars -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
</div>
<div id="footer">
	<p>&copy;All Rights Reserved.</p>
</div>
</body>
</html>
