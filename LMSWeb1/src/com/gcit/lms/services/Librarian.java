package com.gcit.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class Librarian 
{
	public static void main(String args[]) throws SQLException
	{
		   Librarian lib = new Librarian();
		   BorrowerService b2 = new BorrowerService();
		    
		    List<LibraryBranch>l = lib.listOfBranches();
		   System.out.println(l);
		   System.out.println(l.get(0).getBranchId());
//		    List<Book>b1 = lib.listOfBooks(l.get(0).getBranchId()); 
		// System.out.println(b1);
		    //  BookLoans bloans = new BookLoans();
		   	
	}
		public void updateLibraryBranch(LibraryBranch branch) throws SQLException
		{  
			Connection conn = null;
	  try {
		    conn = ConnectionUtil.openConnection();
			LibraryBranchDAO branchdao = new LibraryBranchDAO(conn);
			branchdao.updateLibrarybranch(branch);
		}
		 catch (Exception e)
		{
			e.printStackTrace();
		} 
	  finally {
			conn.close();
		}
		}

		public List<LibraryBranch> listOfBranches() throws SQLException{
			List<LibraryBranch> branchList = new ArrayList<LibraryBranch>();
			{   	Connection conn = null;
				LibraryBranchDAO libdao;
				try {
					conn = ConnectionUtil.openConnection();
					libdao = new LibraryBranchDAO(conn);
					branchList = libdao.readAll();
	             //   branchList = libdao.readLibraryBranchByPK(lib)			
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				finally
				{
					conn.close();
				}
			System.out.println(branchList.get(0).getBranchId());
				return branchList;
			}
		}
		
		public LibraryBranch readBranchByPK(int branchId) throws SQLException{
			Connection conn = null;
			ConnectionUtil conUtil = new ConnectionUtil();
				try {
					conn = conUtil.openConnection();
					LibraryBranchDAO libdao = new LibraryBranchDAO(conn);
					return libdao.readLibraryBranchByPK(branchId);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			finally
			{
				conn.close();
			}
			return null;	
		}	
		
		
		public List<Book> getBooks(int branchId) throws SQLException  {
			List<Book> bookList = new ArrayList<Book>();
		 	Connection conn = null;
		// 	branchId = 1;
			try {
				conn = ConnectionUtil.openConnection();
				BookDAO bDAO = new BookDAO(conn);
			bookList = bDAO.listOfBorrwBooks(branchId);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		finally{
			conn.close();
		}
			return bookList;	
		}			
		
		public Book readBookByPK(Book bk) throws SQLException{
			Connection conn = null;
			ConnectionUtil conUtil = new ConnectionUtil();
				try {
					conn = conUtil.openConnection();
					BookDAO bdao = new BookDAO(conn);
					return bdao.readBookByPK(bk); 
					} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			finally
			{
				conn.close();
			}
			return null;	
		}	

	public List<BookCopies> getCopies(int branchId) throws SQLException
	{
		List<BookCopies> listOfCopies = new ArrayList<BookCopies>();
		Connection conn=null;
		try {
			conn = ConnectionUtil.openConnection();
		
		BookCopyDAO copiesdao = new BookCopyDAO(conn);
		listOfCopies = copiesdao.listOfCopies(branchId);
			}
			catch(Exception e){
			e.printStackTrace();
		}
		 finally{
	          conn.close();	
		}
		return listOfCopies;
	}
	 
	public void updateBookCopy(int branchId, int bookId, int numOfCopies) throws Exception {
		Connection conn = ConnectionUtil.openConnection();
		try {
			BookCopyDAO copiesdao = new BookCopyDAO(conn);

			BookCopies copies = new BookCopies();
			
			System.out.println(branchId + " " + bookId + " " + numOfCopies);
			copies.setBookId(bookId);
			copies.setBranchId(branchId);
			copies.setNoOfCopies(numOfCopies);
			copiesdao.updateCopies(copies);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}
}
