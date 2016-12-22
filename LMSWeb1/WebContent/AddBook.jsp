<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@include file="welcome.html"%>

<%
	AdminService aservice = new AdminService();
	List<Author> authors =  aservice.readAllAuthorsbyPagenum(1); // adminService.readAllAuthors();
	List<Publisher> publishers = aservice.readAllPublishers(); //adminService.readAllPublisher();
	List<Genre> genres = aservice.readAllGenre(); 
%>


<h2>Enter Book Details</h2>
<hr />
<form class="form-group" action="addBook" method="post">

	<div class="form-group">
		<label for="title">Enter Book Title:</label> <input type="text"
			name="bookTitle" class="form-control" />
	</div>
	<div class="form-group">
		<label for="selectAuthor">Select Author:</label> <select multiple
			class="form-control" name="selectAuthor">

			<%
						for (Author a : authors) {
							
					%>
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
			<%
						}
					%>
		</select>
	</div>


	<label for="selectPublisher">Select Publisher:</label> <select
		class="form-control" name="selectPublisher">
		<%
						for (Publisher p : publishers) {
					%>
		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
		<%
						}
					%>
	</select>
	<div class="form-group">
		<label for="selectGenre">Select Genre:</label> <select multiple
			class="form-control" name="selectGenre">
			<%
						for (Genre g : genres) {
					%>
			<option name="genre_id" value="<%=g.getGenreid()%>"><%= g.getGenre_name() %></option>
			<%
						}
					%>
		</select>
	</div>

	</div>
	<input class="btn btn-sm btn-default"
		style="margin: 5px; width: 100px;" type="submit" value="Add Book" />
	<button type="button" id="cancel" class="btn btn-sm btn-default"
		style="margin: 5px; padding: 3px; width: 100px;" onClick="cancel();">Cancel</button>

</form>
</div>
<div class="col-sm-4"></div>

</div>
