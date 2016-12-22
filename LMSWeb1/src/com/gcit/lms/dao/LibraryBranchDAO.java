package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO 
{

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public void addLibraryBranch(LibraryBranch lib) throws SQLException
	{
	save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[]{lib.getBranchName(),lib.getBranchAddress()});	
	}
	
	public void updateLibrarybranch(LibraryBranch lib) throws SQLException
	{
	save("update tbl_library_branch set branchName = ?,branchAddress = ? where branchId = ? ", new Object[]{lib.getBranchName(),lib.getBranchAddress(), lib.getBranchId()});	
	}	

	public void deleteLibraryBranch(LibraryBranch lib) throws Exception 
	{
		save("DELETE FROM tbl_library_branch WHERE branchId=?",new Object[] { lib.getBranchId() });
	}
	
	public List<LibraryBranch> readAll() throws Exception{
		return readAll("SELECT * FROM tbl_library_branch", null); 
		
	}

	public LibraryBranch readLibraryBranchByPK(int branchId) throws SQLException{
		List<LibraryBranch> branchList =  readAll("select * from tbl_library_branch where branchId = ?", new Object[]{branchId});
		if(branchList!=null){
			return branchList.get(0);
		}
		return null;	}
	
	@Override
	public List<LibraryBranch> extractData(ResultSet rs) 
	{
		List<LibraryBranch> branch =  new ArrayList<LibraryBranch>();
		
		BookLoansDAO bookLoanDAO = new BookLoansDAO(conn);
		BookCopyDAO bookCopyDAO = new BookCopyDAO(conn);
		try {
			while (rs.next()){ 
				LibraryBranch lib = new LibraryBranch();
				lib.setBranchId(rs.getInt("branchId"));
				lib.setBranchName(rs.getString("branchName"));
				lib.setBranchAddress(rs.getString("branchAddress"));

				 lib.setBookloans(bookLoanDAO.readAllFirstLevel("select * from tbl_book_loans where branchId=?", new Object[] {rs.getInt("branchId")}));
				
			     lib.setNoOfCopies(bookCopyDAO.readAllFirstLevel("select * from tbl_book_copies where branchId=?", new Object[] {rs.getInt("branchId")}));
			     branch.add(lib);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branch;
	}

	@Override
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) 
	{
		ArrayList<LibraryBranch> branch =  new ArrayList<LibraryBranch>();
		try {
			while (rs.next()){
				LibraryBranch lib = new LibraryBranch();
				lib.setBranchId(rs.getInt("branchId")); 
				lib.setBranchName(rs.getString("branchName"));
				lib.setBranchAddress(rs.getString("branchAddress"));
				branch.add(lib);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branch;
	}
	
	
}
