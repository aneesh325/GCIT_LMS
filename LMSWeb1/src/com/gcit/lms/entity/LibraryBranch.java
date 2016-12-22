package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class LibraryBranch implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6714631713880074275L;

	private Integer branchId;
	private String branchName;
	private String branchAddress;
	private List<BookLoans> bookloans;
	private List<BookCopies> noOfCopies;



	public List<BookCopies> getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(List<BookCopies> noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		return result;
	}@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBranch other = (LibraryBranch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		return true;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public List<BookLoans> getBookloans() {
		return bookloans;
	}
	public void setBookloans(List<BookLoans> bookloans) {
		this.bookloans = bookloans;
	}
	
}
