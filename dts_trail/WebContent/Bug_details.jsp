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
<script type="text/javascript" src="dwr/interface/Bug_dao.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/jquery.ketchup.css" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ketchup.all.min.js"></script>
<script type="text/javascript">

window.onload=initPage;
function initPage(){
	
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
	$('#form2').ketchup();
	
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
	<p>Defects simplified</p>
	<div id="login_info">welcome, <%=session.getAttribute("user_logged") %><a href="PostLogout.jsp"> logout </a></div>
</div>

<div id="menu">
	<ul id="main">
		
		<li class="current_page_item"><a href="Bug_details.jsp">Defect Details</a></li>
		<li><a href="Bug_Home.jsp">Defect HomePage</a></li>
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
		
<c:choose>		
	<c:when test="${sessionScope.admin_yes==1 }">
		
	
<h2>Edit a Defect</h2>
		 <c:set var="f" value="${sessionScope.bug_edited}"></c:set>
		<c:if test="${f==1 }">
			<p id="msg_confrm">Defect has been edited</p>
			<c:set var="f" value="0"></c:set>
		</c:if>
<form action="Bug_ser" method="post" id="form1">

<input type="hidden" name="bug_id" id="bug_id" value="${sessionScope.editBugdata[0] }">
<input type="hidden" name="project_id" id="project_id" value="${sessionScope.editBugdata[5] }">
<table>
									<tr>
						   				<td class="right"><label>Defect ID:</label></td>
						   				<td class="left"><input type="text" name="bug_id1" id="bug_id1" disabled="disabled"  value="${sessionScope.editBugdata[0] }"></td>
									</tr>
									<tr>
						   				<td class="right"><label>Defect Title:</label></td>
						   				<td class="left"><input type="text" name="bug_title" id="bug_title" value="${sessionScope.editBugdata[1] }" data-validate="validate(required)"></td>
									</tr>
									<tr>
						   				<td class="right"><label>Defect Details:</label></td>
						   				<td class="left"><input type="text" name="bug_desc" value="${sessionScope.editBugdata[2] }" data-validate="validate(required)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Start Date:</label></td>
						   				<td class="left"><input type="text" name="start_date" value="${sessionScope.editBugdata[3] }" data-validate="validate(required, mysqlDate)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>End Date:</label></td>
						   				<td class="left"><input type="text" name="end_date" value="${sessionScope.editBugdata[4] }" data-validate="validate(required, mysqlDate)"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Project ID:</label></td>
						   				<td class="left"><input type="text" name="project_id1" value="${sessionScope.editBugdata[5] }" disabled="disabled"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Status:</label></td>
						   				<td class="left"><input type="text" name="status" value="${sessionScope.editBugdata[6] }" data-validate="validate(required, minlength(1))"></td>
					   				</tr>
					   				<tr>
						   				<td class="right"><label>Prority:</label></td>
						   				<td class="left"><input type="text" name="priority" value="${sessionScope.editBugdata[7] }" disabled="disabled"></td>
					   				</tr>
					   				<tr>
					   					<td></td>
					   					<td><input type="submit" value="confirm update basic info" name="submitb" ></td>
					   				</tr>
									
</table>
</FORM>
<br>
<h2>Defect Team</h2>
	<table>
		<tr>
			<th>Defect ID: <c:out value="${sessionScope.editBugdata[0] }"></c:out></th>
		</tr>
		<tr>
			<th>Project ID: <c:out value="${sessionScope.editBugdata[5] }"></c:out></th>
		</tr>
		
	</table>
	<table border="1">
		<tr>
 		<th>User Name</th>
 		<th>Action</th>
 		</tr>
	
    	<c:forEach items="${sessionScope.editBugteamdata}" var="currentName" varStatus="status">
              <tr>
                  <td><c:out value="${currentName}" /></td>
				  <td><a href="<%=request.getContextPath()%>/Bug_ser?bug_to=${currentName }&submitb=remove member&bug_id=${sessionScope.editBugdata[0] }">Remove</a></td>                  
               </tr>
  		  </c:forEach>
    </table>
    
<h2>Add Members to the Defect</h2>
<form action="Bug_ser" method="post" id="form2">
	<table border="1">
	<tr>
		<td class="right"><label>Choose bug members:</label></td>
		<td class="left">
 				<select name="bug_to" id="bug_to" multiple="multiple" data-validate="validate(otherThanNone(none))">
	    		<option value="none" selected="selected">---Select USER---</option>
	     		<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/dts" user="root" password="root" scope="session"/>
				<sql:query var="qry" dataSource="${dataSource}">select user_name from dts_project where project_id =(select distinct project_id from dts_bug where bug_id=${sessionScope.editBugdata[0]})  </sql:query>
		      		<c:forEach var="row" items="${qry.rows}">
							      		<option value="${row.user_name}"><c:out value="${row.user_name}"/></option>	
		      		</c:forEach>
		      
			</select>
		</td>
	</tr>	
	<tr>
		<td class="right">
		</td>
		<td class="center"><input type="submit" name="submitb" value="add members">	
		</td>
	</tr>
    </table>
	
	<input type="hidden" name="bug_id"  value="${sessionScope.editBugdata[0] }">

	<input type="hidden" name="bug_title" value="${sessionScope.editBugdata[1] }">
	<input type="hidden" name="bug_desc" value="${sessionScope.editBugdata[2] }">
	<input type="hidden" name="start_date"  value="${sessionScope.editBugdata[3] }">
	<input type="hidden" name="end_date"  value="${sessionScope.editBugdata[4] }">
	<input type="hidden" name="project_id"  value="${sessionScope.editBugdata[5] }">
	<input type="hidden" name="status"  value="${sessionScope.editBugdata[6] }">
	<input type="hidden" name="priority"  value="${sessionScope.editBugdata[7] }">
	<input type="hidden" name="bug_from" id="bug_from" value="${sessionScope.user_logged }">	
	
	</form>
<br><br>
	</c:when>
		<c:otherwise>
				<br>
	<h2>Defect Team</h2>
	<table>
		<tr>
			<th>Defect ID: <c:out value="${sessionScope.editBugdata[0] }"></c:out></th>
		</tr>
		<tr>
			<th>Project ID: <c:out value="${sessionScope.editBugdata[5] }"></c:out></th>
		</tr>
		
	</table>
	
	<table border="1">
		
		<tr>
 		<th>Defect Members:</th>
 		</tr>
	
    	<c:forEach items="${sessionScope.editBugteamdata}" var="currentName" varStatus="status">
              <tr>
              <td><c:out value="${currentName}" /></td>
  		  </c:forEach>
    </table>
		
		</c:otherwise>	
		</c:choose>
	</div>
	<!-- end content -->
	<!-- start sidebars -->
	
	
	<!-- end sidebars -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
</div>
<div id="footer">
	<p>&copy; All Rights Reserved.</p>
</div>
</body>
</html>
