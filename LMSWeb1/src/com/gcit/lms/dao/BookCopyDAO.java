package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookCopies;

public class BookCopyDAO extends BaseDAO
{

	public BookCopyDAO(Connection conn) {
		super(conn);
	}

	public void addCopies(BookCopies copies) throws Exception 
	{
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values(?,?,?)",
				new Object[] { copies.getBookId(), copies.getBranchId(), copies.getNoOfCopies()});
	}
	
	
	public void updateCopies(BookCopies copies) throws Exception 
	{
		save("update tbl_book_copies set noOfCopies = ? where bookId=? and branchId=?",
				new Object[] { copies.getNoOfCopies(), copies.getBookId(), copies.getBranchId()});
	}

	public void deleteCopies(BookCopies copies) throws Exception
	{
		save("delete from tbl_book_copies where bookId=? and branchId=?",
				new Object[] { copies.getBookId(), copies.getBranchId()});
	}

	public List<BookCopies> readAll() throws Exception {
		return readAll("SELECT * FROM tbl_book_copies", null);
	}

	public BookCopies readOne(BookCopies copies)
			throws Exception {

		List<BookCopies> bookCopies = readAll(
				"SELECT * FROM tbl_book_copies", new Object[] { copies.getBookId(),
						copies.getBranchId() });
		if(bookCopies!=null && bookCopies.size()>0){
			return bookCopies.get(0);}
		return null;
	}
	
	
	@Override
	public  List<BookCopies> extractData(ResultSet rs) 
	{
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		BookDAO bookDAO = new BookDAO(conn);
		LibraryBranchDAO branchDAO = new LibraryBranchDAO(conn);

		try {
			while(rs.next()){

				BookCopies copy = new BookCopies();
				copy.setBookId(rs.getInt("bookId"));
				copy.setBranchId(rs.getInt("branchId"));
				copy.setNoOfCopies(rs.getInt("noOfCopies"));

				bookCopies.add(copy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopies;
		
		
	}

	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) 
	{
		List<BookCopies> bookCopies = new ArrayList<BookCopies>();
		BookDAO bookDAO = new BookDAO(conn);
		LibraryBranchDAO branchDAO = new LibraryBranchDAO(conn);

		try {
			while(rs.next()){

				BookCopies copy = new BookCopies();
				copy.setBookId(rs.getInt("bookId"));
				copy.setBranchId(rs.getInt("branchId"));
				copy.setNoOfCopies(rs.getInt("noOfCopies"));

				bookCopies.add(copy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCopies;
	}
	
	public int getNoOfCopies(Integer bookId,Integer branchId) throws SQLException
	{int NoOfCopies;
	
	List<Object>a = readAllFirstLevel("select tbl_book_copies.noOfCopies from tbl_book_copies where tbl_book_copies.bookId = ? and tbl_book_copies.branchId = ?",
			new Object[]{bookId,branchId});
	return NoOfCopies = (int) a.get(0);
	}
	
//	public BookCopies updateNoOfCopies(BookLoans bloans)//this is for borrower
//	{
//		BookCopies copies = new BookCopies();
//		copies.setBookId(bloans.getBooks().getBookId());
//		copies.setBranchId(bloans.getBranch().getBranchId());
//		return copies;
//	}
	public List<BookCopies> listOfCopies(int branchId) throws SQLException
	{
		return  readAllFirstLevel("select tbl_book.bookId,tbl_library_branch.branchId, branchName,title,noOfCopies from tbl_book,tbl_book_copies,tbl_library_branch where tbl_library_branch.branchId = tbl_book_copies.branchId and tbl_book_copies.bookId=tbl_book.bookId and tbl_library_branch.branchId = ? ",
				new Object[] {branchId});
	}
	
}
