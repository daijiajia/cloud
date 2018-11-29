<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
	html{
	height: 100%;
	}
	body{
		height: 100%;
	}
		#title{
			height: 15%;
	    	background-color: #458fce;
	    	text-align: center;
			}
		a{
			font-size: 40px;
			color: #fff;
		}
		#up{
		 	margin-top:5% ;
		 	width:50%;
		 	margin-left: 5%;
			height: 100%;

			background-color: #458fce;
		}
		#name{
			height: 4%;
	    	background-color: #FFFF37;
	    	text-align: center;
		}
		#table1{
		height: 100%;
		}
		#file{
		margin-left: 20%;
		margin-top: 30%;	
		}
		#upload{
		margin-left: 20%;	
		}
		#path{
		width: 95%;
		}
	#all{
	width: 45%;
	margin-top: 5%;
	background-color: #FFFF37;
	}
	#submit{
		margin-left: 20%;
	}
	</style>
</head>
<body >
	<div id="title" >
		<a><%=session.getAttribute("user") %></a>
	</div>
  <div id="all" style="float: left;">
	<h2>your file</h2>
			<a href="/windserver/goup?filename=<%=session.getAttribute("filepath")%>&user=<%=session.getAttribute("user")%>">
			<input type="button" name="up" value="go up">
			</a>
			<input type="input" id="folder" />
			<input type="button" id="folder_button" value="add a new folder" />
 	<table width="315" border="0">
 	  <tr>
		 <c:forEach items="${sessionScope.filename}" var="t" >
	 	<div id="name">
		 <tr>		 
			<td><c:out value="${t}" /></td>
			<td align="center">
			<a href="/windserver/download?filename=${t}&new=no&user=<%=session.getAttribute("user")%>">
			<input type="button" name="download" value="download it">
			</a>
			<a href="/windserver/delete?filename=${t}&user=<%=session.getAttribute("user")%>">
			<input type="button" name="delete" value="delete it">
			</a>
			</td>
		 </tr>
		 </div>
	 </c:forEach>
 </tr>
 </table>
</div>
	<div id="up" style="float: left;">
	 <form method="post" action="/windserver/doupdate" enctype="multipart/form-data">
	 <table name="table1" border="0">
	   <tr>
	   <td><input id="file" type="file" name="file1" ></td>
	   </tr>
	   	<tr>
		<input type="input" name="path" id="path" value="<%=session.getAttribute("filepath")%>" />
		</tr>
		<tr>
	    <td><input id="upload" type="submit" name="Submit" id="submit" value="upload"></td>
	   </tr>
 	</table >
 	 </form>
   	</div>
<script type="text/javascript">
var folder_name= document.getElementById("folder");
var btn= document.getElementById("folder_button");
btn.onclick=function(){		
		alert(folder_name.value);
		var myName="<%=session.getAttribute("filepath")%>"; 
		var user="<%=session.getAttribute("user")%>"; 
		window.location.href="/windserver/download?filename="+myName+"&new="+folder_name.value+"&user="+user;	
		alert(myName);
	}

	
</script>
</body>
</html>