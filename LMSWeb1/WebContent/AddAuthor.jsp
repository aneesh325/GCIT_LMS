    <%@include file="welcome.html" %>
		<em>Enter Author Details</em>
		<form class="form-group" action="addAuthor" method="post">

				<label for="labelauthorName">Enter Author Name:</label> <input
					type="text" name="authorName" class="form-control"
					placeholder="Enter Author Name" />

			<button type="submit" class="btn btn-success"
				value="Add Author" />Add Author</button>
			<button type="button" id="cancel" class="btn btn-danger"
				 onClick="cancel();">Cancel</button>
		</form>
	</div>
</div>
