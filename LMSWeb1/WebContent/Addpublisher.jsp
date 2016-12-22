    <%@include file="welcome.html" %>
		<em>Enter Publisher Details</em>
	</h2>
		<form class="form-group" action="addPublisher" method="post">

				<label for="labelPublisherName">Enter Publisher Name:</label> <input
					type="text" name="publisherName" class="form-control"
					placeholder="Enter Publlisher Name" />

			<div class="form-group">
				<label for="labelPublisherAddress">Enter Publisher Address:</label>
				<input type="text" name="publisherAddress" class="form-control"
					placeholder="Enter Publlisher Address" />
			</div>

			<div class="form-group">
				<label for="labelPublisherPhone">Enter Publisher Phone:</label> <input
					type="text" name="publisherPhone" class="form-control"
					placeholder="Enter Publlisher Phone" />
			</div>
			<button class="btn btn-success"
				value="Add Publisher" />Add Publisher</button>
			<button type="button" id="cancel" class="btn btn-danger"
				 onClick="cancel();">Cancel</button>
		</form>
	</div>
</div>
