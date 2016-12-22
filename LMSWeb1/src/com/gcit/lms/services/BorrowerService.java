package com.gcit.lms.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.web.BaseServlet;
@SuppressWarnings("serial")
@WebServlet("/BorrowerService")
public class BorrowerService extends BaseServlet 
{
	public List<Borrower> listOfBorr() throws SQLException{
		List<Borrower> borrList = new ArrayList<Borrower>();
		{   	Connection conn = null;
		BorrowerDAO borrdao;
			try {
				conn = ConnectionUtil.openConnection();
				borrdao = new BorrowerDAO(conn);
				borrList = borrdao.readAllBorr();
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
		System.out.println(borrList.get(0).getName());
			return borrList; //return the redirect jsp page for the response
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
		System.out.println(branchList.size());
			return branchList; //return the redirect jsp page for the response
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
	
	
	public Borrower readBorrByPK(int cardNo) throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
			try {
				conn = conUtil.openConnection();
				BorrowerDAO bdao = new BorrowerDAO(conn);
				return bdao.readBorrowerByPK(cardNo); 
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
	
	public void checkoutBook(BookLoans loans) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionUtil.openConnection();
			BookLoansDAO loansDAO = new BookLoansDAO(conn);
			BookCopyDAO copiesDAO = new BookCopyDAO(conn);
			BookCopies copies = new BookCopies();
			loansDAO.addBookLoans(loans);
			copies.setBookId(loans.getBooks().getBookId());
			copies.setBranchId(loans.getBranch().getBranchId());
		    copies = copiesDAO.readOne(copies);
			System.out.println(copies.getNoOfCopies());

			copies.setNoOfCopies(copies.getNoOfCopies()-1);
			copiesDAO.updateCopies(copies);
			conn.commit();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
			finally
			{
				conn.close();
			}	
		}
	
	
	public void returnBook(BookLoans loans) throws Exception
	{    
		Connection conn = null;
		try
		{
			conn = ConnectionUtil.openConnection();
			BookLoansDAO loansDAO = new BookLoansDAO(conn);
			BookCopyDAO copiesDAO = new BookCopyDAO(conn);
			BookCopies copies = new BookCopies();
			loansDAO.delete(loans); //.update(loans);  //.delete(loans);
			copies.setBookId(loans.getBooks().getBookId());
			copies.setBranchId(loans.getBranch().getBranchId());
		    copies = copiesDAO.readOne(copies);
			System.out.println(copies.getNoOfCopies());

			copies.setNoOfCopies(copies.getNoOfCopies()+1);
			copiesDAO.updateCopies(copies);
			conn.commit();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			conn.close();
		}	
		
	}

	@Override
	public String execute() 
	{
		switch(getUrl())
		{
		case "/LibraryList":
	//	return listOfBranches();
		break;
		}	
		return null;
	}
}

//public static void main(String args[]) throws Exception
//{
//System.out.println("\nvalid card\n");
//	System.out.println("1)Check out a book"
//			+"\n2)Return a book"
//			+"\n3)Quit to Previous");
//	Scanner sc = new Scanner(System.in);
//	int opt = sc.nextInt();
//	if(opt == 1)
//	{
//    BorrowerService borr = new BorrowerService();
//    BorrowerService b2 = new BorrowerService();
//    
//    List<LibraryBranch>b = borr.listOfBranches();
//    List<Book>b1 = borr.getBooks(b.get(0).getBranchId());
//    BookLoans bloans = new BookLoans();
//   
//    bloans.setBooks((b1.get(0)));
//    bloans.setBranch(b.get(0)); 
//    Date date = new Date(opt);
//    bloans.setDateIn(date);
//    bloans.setDateOut(date);
//    bloans.setDueDate(date);
//    Borrower cards = new Borrower();
//    cards.setCardNo(1);
//    bloans.setCards(cards); 
////borr.returnBook(bloans);
//	borr.checkoutBook(bloans);
//
//	} 
//	}