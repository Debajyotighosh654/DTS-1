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
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Pragma" content="no-cache">
 <meta http-equiv="Cache-Control" content="no-cache">
 <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />


<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/interface/User_dao.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="css/jquery.ketchup.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ketchup.all.min.js"></script>
<script type="text/javascript">


window.onload=initPage;

function initPage(){
	//alert("in it");
	document.getElementById("role_name").onchange= showNewId1;
	// check password
	

	
// Ketchup Validation Code Starts
	
	$.ketchup.validation('alphabetsOnly', 'Alphabets only', function(form, el, value) {
		var regexLetter =/^[a-zA-Z()]+$/;
		if(regexLetter.test(value)) {
		    return true;
		  }
		else
			false;
		}, function(form, el) {
		  //initial callback, this is optional
		});
	$.ketchup.validation('matchPass', 'Retype the Same password', function(form, el, value) {
		if(document.getElementById("passr").value==document.getElementById("pass").value) {
		    return true;
		  }
		else
			false;
		}, function(form, el) {
		  //initial callback, this is optional
		});
	

	$.ketchup.validation('mysqlDate', 'format yyyy-mm-dd', function(form, el, value) {
		  if(value!="") {
		    return true;
		  } else {
		    return false;
		  }
		}, function(form, el) {
		  //initial callback, this is optional
		});
	$.ketchup.validation('otherThanNone', 'Select other than "{arg1}"', function(form, el, value, word1) {
		  if(value!=word1) {
		    return true;
		  } else {
		    return false;
		  }
		}, function(form, el) {
		  //initial callback, this is optional
		});
	$('#form1').ketchup();
	
}
function showNewId1(){
	
	//alert(document.getElementById("role_name").value);
	User_dao.getNewId(document.getElementById("role_name").value,showNewId2);
	
}
function showNewId2(newId){
	//alert("showNewId2");
	document.getElementById("user_name").value=newId;
	document.getElementById("login_id").value=newId;
}


</script>


<SCRIPT LANGUAGE = "JavaScript">
<!-- HTML comment to placate non JavaScript capable browsers
/* 		Typing Text Area Page Scroller
This script was created to provide another alternative to those erratic and 
hard to read sideways scrolling text and status bar displays.  This script 
uses TEXTAREA to create the effect of text scrolling up the page.

This source code is released to the public domain with the provision that 
the copyright information remains in the source code.
Copyright (c) by: Robert N Bovara  All Rights Reserved.
*/
function AraVob() {
}
var ScreenLine = new AraVob();
ScreenLine[1]  = "Once upon a time";
ScreenLine[2]  = "A really bored guy called George";
ScreenLine[3]  = "Decided to create a web site called JavaScript Kit";
ScreenLine[4] = "Since he had no life";
ScreenLine[5] = "He put in many hard hours of work";
ScreenLine[6] = "Hoping that it will help webmasters just a bit in creating web pages";
ScreenLine[7] = "Whether or not it has really helped";
ScreenLine[8] = "Is anyone's guess";
ScreenLine[9]  = "But since George has no life";
ScreenLine[10]  = "He may very well continue to work on JavaScript Kit";
ScreenLine[11]  = "For as long as he is physically able";
ScreenLine[12] = "And mentally stable";
ScreenLine[13] = "The end";
/*
To change or add lines, just replace values of or add to ScreenLine[n]
above.   Use \" in the message text for quotes and \t for tabs.
*/
var msgNum = 1;          // set to first message to display
var msgCnt = 13;         // set to number of last ScreenLine to display.
var typeSpeed = 50;      // the rate in milliseconds to scroll to top (higher number is slower)
var lineDelay = 2000     // the delay time at end of line. (unless the line is a single space)
var pagLen = 7;          // number of lines per page (usually the number of rows in the TEXTAREA)   
var delay = typeSpeed;     
var timerPS = null;
var linPntr = 0;
var tally = 1;
var msg = " ";
var outMsg = "";
var i = 0;
var cr="\r\n"
if (window.document) {
 var cr="\n"
}
else {
  ScreenLine[11]="    You will need to upgrade your browser."
}
if (window.document) {
  ScreenLine[10]="Your version of Opera show's a moving scroll bar button when you"
  ScreenLine[12]="  but the frame may not appear to be scrolling."+cr
}
// set up ScreenLines for display
for (x = msgCnt; 1 <= x; x--) {
  ScreenLine[x+pagLen] = ScreenLine[x] + cr;
}
for (x = 1; x <= (pagLen); x++) {
  ScreenLine[x] =  " " +cr;
}
 msgCnt +=  pagLen;
 msg = ScreenLine[1];
// end setup

function DisplayScroll() {
 if (msgNum < pagLen) {
  delay = typeSpeed;
 }
 else {
  delay = lineDelay;
 }
 ChangeMsg();
 outMsg += msg;
 self.document.forms[0].elements[0].value = outMsg;
  timerPS = setTimeout("DisplayScroll()",delay);
}

function ChangeMsg() {
 msgNum++;
 if (msgCnt < msgNum) {
   msgNum = 1;
 }
 if (pagLen <= tally) {
   chgPage();
 }
 tally++;
 msg = ScreenLine[msgNum];
}

function chgPage() {
 if (msgNum < pagLen) {
   linPntr = msgCnt - pagLen + msgNum + 1;
 }
 else {
   linPntr = msgNum - (pagLen - 1);
 }

 outMsg =   ScreenLine[linPntr];
 for (p = 1; p < (pagLen - 1); p++) {
   linPntr++;
   if (msgCnt < linPntr) {
     linPntr = 1;
   }
   outMsg += ScreenLine[linPntr];
 }
  
}

function quitDisplay() {
  self.document.forms[0].elements[0].value = "Scroll a Page for yourself today!";
}
// -->
</SCRIPT>

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
#st_table{
	float: right;
}
#msg_confrm{
	color: orange;
	text-align: center;
}
.right{
text-align: right;
}
.left{
text-align: left;
}
.center{
text-align: center;
}

</style>
 
<body>
<!-- start header -->
 
<div id="logo">
	<img src="images/dts_logo.png" id="logo_img">
	<h1><a href="#">DTS</a></h1>
	<p>defects simplified</p>
	<div id="login_info">welcome, <%=session.getAttribute("user_logged") %><a href="PostLogout.jsp"> logout </a></div>
</div>

<div id="menu">
	<ul id="main">
		<li><a href="main.jsp">Homepage</a></li>
		<li><a href="User_project.jsp">Projects</a></li>
		<li><a href="Sol_Home.jsp">Solutions</a></li>
		<li><a href="Bug_Home.jsp">Defects</a></li>
		<li><a href="User_msg.jsp">Messages</a></li>
		<li class="current_page_item"><a href="User_user.jsp">Users</a></li>
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
		
	
<h2>Add a User</h2>
		 <c:set var="f" value="${sessionScope.user_added}"></c:set>
		<c:if test="${f==1 && sessionScope.user_added!=null }">
			<p id="msg_confrm">User has been added</p>
			<c:set var="f" value="0"></c:set>
			<c:set var="user_added" value="0" scope="session"></c:set>
		</c:if>
		 <c:set var="f1" value="${sessionScope.user_edited}"></c:set>
		<c:if test="${f1==1 && sessionScope.user_edited!=null}">
			<p id="msg_confrm">User has been edited</p>
			<c:set var="f1" value="0"></c:set>
			<c:set var="user_edited" value="0" scope="session"></c:set>
		</c:if>
		 <c:set var="f2" value="${sessionScope.user_deleted}"></c:set>
		<c:if test="${f2==1 && sessionScope.user_deleted!=null}">
			<p id="msg_confrm">User has been deleted</p>
			<c:set var="f2" value="0"></c:set>
			<c:set var="user_deleted" value="0" scope="session"></c:set>
		</c:if>
<form action="User_ser" method="post" id="form1">
<input type="hidden" name="user_name" id="user_name">
<table>
									<tr>
										<td class="right"><label>Choose Role:</label></td>
										<td class="left">
						   					<select name="role_name" id="role_name" data-validate="validate(otherThanNone(none))">
									    		<option selected="selected" value="none">---Select ROLE---</option>
									     		<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
												<sql:query var="qry" dataSource="${dataSource}">SELECT role_name FROM dts_role</sql:query>
										      		<c:forEach var="row" items="${qry.rows}">
					   							      		<option value="${row.role_name}"><c:out value="${row.role_name}"/></option>	
										      		</c:forEach>
										      
											</select>
										</td>
									</tr>								
									<tr>
						   				<td class="right"><label>Login ID:</label></td>
						   				<td class="left"><input type="text" name="login_id" id="login_id" disabled="disabled"></td>
									</tr>
									<tr>
						   				<td class="right"><label>Password:</label></td>
						   				<td class="left"><input type="text" name="pass" id="pass" data-validate="validate(required, minlength(5))"></td>
									</tr>
									<tr>
						   				<td class="right"><label>Retype Password:</label></td>
						   				<td class="left"><input type="text" name="passr" id="passr" data-validate="validate(required, minlength(5), matchPass)"></td>
									</tr>

									<tr>
						   				<td class="right"><label>First Name:</label></td>
						   				<td class="left"><input type="text" name="first_name"  data-validate="validate(required, alphabetsOnly)" ></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Middle Name:</label></td>
						   				<td class="left"><input type="text" name="middle_name"  data-validate="validate(required, alphabetsOnly)" ></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Last Name:</label></td>
						   				<td class="left"><input type="text" name="last_name"  data-validate="validate(required, alphabetsOnly)" ></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Email:</label></td>
						   				<td class="left"><input type="text" name="email" data-validate="validate(required, email)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Phone:</label></td>
						   				<td class="left"><input type="text" name="phone_no" data-validate="validate(required, digits, minlength(10))"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Alternate Phone:</label></td>
						   				<td class="left"><input type="text" name="alternate_no" data-validate="validate(required, digits, minlength(10))"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Emergency Phone:</label></td>
						   				<td class="left"><input type="text" name="emergency_no" data-validate="validate(required, digits, minlength(10))"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Permanent Resd Address:</label></td>
						   				<td class="left"><input type="text" name="perm_resd_add" data-validate="validate(required)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Current Resd Address:</label></td>
						   				<td class="left"><input type="text" name="curr_resd_add" data-validate="validate(required)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Permanent Work Location:</label></td>
						   				<td class="left"><input type="text" name="perm_wrk_add" data-validate="validate(required)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Current Work Location:</label></td>
						   				<td class="left"><input type="text" name="curr_wrk_add" data-validate="validate(required)"></td>
					   				</tr>
					   				<tr>
					   					<td></td>
					   					<td><input type="submit" value="add" name="submit" ></td>
					   				</tr>
									
</table>
</FORM>

	
<br><br>
		</c:if>	
		
		
<h2>All Users</h2>
					
					<h3>Admin</h3>
					<table id="adm_table" border="1">
					
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">SELECT user_name FROM dts_login where role_name='ADMIN'</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.user_name}"/></td>	
					      		<c:if test="${sessionScope.main_admin==1 }">
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=editstep1">Edit</a></td>
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=delete">Delete</a></td>
					      		</c:if>	
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
		      		<h3>Clients</h3>
					<table id="cl_table" border="1">
					
					
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">SELECT user_name FROM dts_login where role_name='CL' order by user_name</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.user_name}"/></td>
					      		<c:if test="${sessionScope.main_admin==1 }">
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=editstep1">Edit</a></td>
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=delete">Delete</a></td>
					      		</c:if>	
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>
		      		<br>
		      		<h3>Employees</h3>
					<table id="emp_table" border="1">
					
					<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
					<sql:query var="qry" dataSource="${dataSource}">SELECT user_name FROM dts_login where role_name not in ('ADMIN', 'CL')order by user_name</sql:query>
					      		
					      		<c:forEach var="row" items="${qry.rows}">
					      		<tr>
					      		<td><c:out value="${row.user_name}"/></td>	
					      		<c:if test="${sessionScope.main_admin==1 }">
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=editstep1">Edit</a></td>
					      		<td><a href="<%=request.getContextPath()%>/User_ser?user_name=${row.user_name }&submit=delete">Delete</a></td>
					      		</c:if>	
					      		</tr>
					    		</c:forEach>
										      		
		      		
		      		</table>

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
