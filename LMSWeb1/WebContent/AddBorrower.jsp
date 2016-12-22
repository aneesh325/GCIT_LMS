    <%@include file="welcome.html" %>
		<em>Enter Author Details</em>
		<form class="form-group" action="addBorrower" method="post">

				<label for="labelname">Enter Borrower Name:</label> <input
					type="text" name="name" class="form-control"
					placeholder="Enter Borrower Name" />

				<label for="labeladdress">Enter Borrower Address:</label> <input
					type="text" name="address" class="form-control"
					placeholder="Enter Borrower Address" />

				
				<label for="labelphone">Enter Borrower Phone:</label> <input
					type="text" name="phone" class="form-control"
					placeholder="Enter Borrower Phone" />
				
		
			<button type="submit" class="btn btn-success"
				value="Add Borrower" />Add Borrower</button>
			<button type="button" id="cancel" class="btn btn-danger"
				 onClick="cancel();">Cancel</button>
		</form>
	</div>
</div>
