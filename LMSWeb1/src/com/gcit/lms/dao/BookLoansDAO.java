package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

public class BookLoansDAO extends BaseDAO
{

	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addBookLoans(BookLoans bloans) throws SQLException
	{
		System.out.println(bloans.getBooks().getBookId() + " " + bloans.getBranch().getBranchId() + " "  + bloans.getCards().getCardNo());
	 save("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn) values (?,?,?,?,?,?) ",
	new Object[]{bloans.getBooks().getBookId(),bloans.getBranch().getBranchId(),bloans.getCards().getCardNo(),bloans.getDateOut(),bloans.getDueDate(),bloans.getDateIn()});	
	}	
		
	public void update(BookLoans bloans) throws Exception {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and cardNo = ? and branchId = ?",
				new Object[] {bloans.getDateOut(), bloans.getDueDate(), bloans.getDateIn(), bloans.getBooks().getBookId(),
						bloans.getCards().getCardNo(), bloans.getBranch().getBranchId()});
	}
	public void update2(BookLoans bloans) throws Exception {
		save("update tbl_book_loans set dueDate = ? where bookId = ? and cardNo = ? and branchId = ?",
				new Object[] {bloans.getDueDate(), bloans.getBooks().getBookId(),
						bloans.getCards().getCardNo(), bloans.getBranch().getBranchId()});
	}

	public void delete(BookLoans bloans) throws Exception {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bloans.getBooks().getBookId(), bloans.getBranch().getBranchId(), bloans.getCards().getCardNo() });
	}

	public List<BookLoans> readAllBookLoans() throws Exception{
		return readAllFirstLevel("select * from tbl_book_loans", null);
		
	}

//	public BookLoans readOne(int bookId, int cardNo, int branchId) throws Exception {
//		List<BookLoan> bookLoans = (List<BookLoan>) readAll("select * from tbl_book_loans where bookId = ? and branchId = ?"
//				+ " and cardNo = ?", new Object[] {bookId, branchId, cardNo});
//		if(bookLoans!=null && bookLoans.size()>0){
//			return bookLoans.get(0);
//		}
//		return null;
//	}
	@Override
	public List<BookLoans> extractData(ResultSet rs) {
		

		List<BookLoans> bookLoans = new ArrayList<BookLoans>();

		BookDAO bookDAO = new BookDAO(conn);
		LibraryBranchDAO branchDAO = new LibraryBranchDAO(conn);
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
		try {
			while (rs.next()) 
			{
				BookLoans bloans = new BookLoans();
				bloans.setDateOut((rs.getDate("dateOut")));
				bloans.setDueDate((rs.getDate("dueDate")));
				bloans.setDateIn((rs.getDate("dateIn")));
				Integer bookId = rs.getInt("bookId");
			    Integer branchId = rs.getInt("branchId");
			    Integer cardNo = rs.getInt("cardNo");
			    
			   List<Book> temp = bookDAO.readAll("select * from tbl_book where bookId = ?",new Object[]{bookId});
			   List<LibraryBranch> temp1 = branchDAO.readAll("select * from tbl_library_branch where branchId = ?",new Object[]{branchId});
			   List<Borrower> temp2 = borrowerDAO.readAll("select * from tbl_borrower where cardNo = ?",new Object[]{cardNo});
			   
			   if((temp != null)&&(temp1 != null)&&(temp2 != null))
			   {
				   bloans.setBooks(temp.get(0));
				   bloans.setBranch(temp1.get(1));
				   bloans.setCards(temp2.get(2));
				  
				   bookLoans.add(bloans);
			   }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
       return bookLoans; 
	}

	@Override
	public  List<BookLoans> extractDataFirstLevel(ResultSet rs) 
	{

		List<BookLoans> bookLoans = new ArrayList<BookLoans>();

		BookDAO bookDAO = new BookDAO(conn);
		LibraryBranchDAO branchDAO = new LibraryBranchDAO(conn);
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
		try {
			while (rs.next()) 
			{
				BookLoans bloans = new BookLoans();
				bloans.setDateOut((rs.getDate("dateOut")));
				bloans.setDueDate((rs.getDate("dueDate")));
				bloans.setDateIn((rs.getDate("dateIn")));
				Integer bookId = rs.getInt("bookId");
			    Integer branchId = rs.getInt("branchId");
			    Integer cardNo = rs.getInt("cardNo");
			    
			   List<Book> temp = bookDAO.readAllFirstLevel("select * from tbl_book where bookId = ?",new Object[]{bookId});
			   List<LibraryBranch> temp1 = branchDAO.readAllFirstLevel("select * from tbl_library_branch where branchId = ?",new Object[]{branchId});
			   List<Borrower> temp2 = borrowerDAO.readAllFirstLevel("select * from tbl_borrower where cardNo = ?",new Object[]{cardNo});
			   
			   if((temp != null)&&(temp1 != null)&&(temp2 != null))
			   {
				   bloans.setBooks(temp.get(0));
				   bloans.setBranch(temp1.get(0));
				   bloans.setCards(temp2.get(0));
				  
				   bookLoans.add(bloans);
			   }
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
       return bookLoans; 
	}
	
	
}
