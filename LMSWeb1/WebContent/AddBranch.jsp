    <%@include file="welcome.html" %>
		<em>Enter Author Details</em>
		<form class="form-group" action="addBranch" method="post">

				<label for="labelbranchName">Enter Branch Name:</label> <input
					type="text" name="branchName" class="form-control"
					placeholder="Enter Branch Name" />

				<label for="labelbranchAddress">Enter Branch Address:</label> <input
					type="text" name="branchAddress" class="form-control"
					placeholder="Enter Branch Address" />
		
			<button type="submit" class="btn btn-success"
				value="Add Branch" />Add Branch</button>
			<button type="button" id="cancel" class="btn btn-danger"
				 onClick="cancel();">Cancel</button>
		</form>
	</div>
</div>
