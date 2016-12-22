<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="welcome.html"%>
<%
	AdminService aservice = new AdminService();
	int count = aservice.getAuthorsCount();
	int pages = 1;
	if (count % 5 > 0) {
		pages = (count / 5) + 1;
	} else {
		pages = (count / 5);
	}
	List<Author> aList = new ArrayList<Author>();
	if (request.getAttribute("aList") != null) {
		aList = (List<Author>) request.getAttribute("aList");
	} else {

		//aList = aservice.readAllAuthorsbyPagenum(1);	
		aList = aservice.readAllAuthors(1, null);
	}
%>
<script type="text/javascript">
	function searchAuthors() {

		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$("#authorsTable").html(data);
		});
	}
</script>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AuthorList</title>
</head>
<body>
	<h1>GCIT Library Management</h1>
	<form action="searchAuthors">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Author name"
				aria-descibedby="basic-addon1" name="searchString" id="searchString" onkeypress="searchAuthors()">
		</div>
	</form>


	<nav aria-label="Page navigation">
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<%
			for (int i = 1; i <= pages; i++) {
		%>
		<li><a href="pageAuthors?pagenum=<%=i%>"><%=i%></a></li>
		<%
			}
		%>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
	</nav>

	<div class="col-md-6">
		<table class="table table-striped" id="authorsTable">
			<thead>
				<tr>
					<th>#</th>
					<th>AuthorName</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Author a : aList) {
				%>
				<tr>
					<td><%=aList.indexOf(a) + 1%></td>
					<td><%=a.getAuthorName()%></td>
					<td><button type="button" class="btn btn-warning"
							onClick="javascript:location.href='EditAuthor.jsp?authorId=<%=a.getAuthorId()%>'">Update</button></td>


					<td><button type="button" class="btn btn-danger"
							onClick="javascript:location.href='DelAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>


				</tr>
				<%
					}
				%>
				<td><button type="button" class="btn btn-success"
						onClick="javascript:location.href='AddAuthor.jsp'">Add</button>
					<button type="button" class="btn btn-success"
						onClick="location.href='Index.jsp';">Cancel</button> <br /></td>

			</tbody>
		</table>
	</div>
	</div>
</body>
</html>