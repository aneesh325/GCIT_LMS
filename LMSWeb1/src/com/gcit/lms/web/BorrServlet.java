package com.gcit.lms.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.services.BorrowerService;
@WebServlet({"/BorrServlet","/Borr2","/Borr2_1"})
public class BorrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BorrowerService bservice = new BorrowerService();
    public BorrServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
				String forwardPage = "Index.jsp";
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "Index.jsp";
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		//Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));

		switch(reqUrl)
		{
		case "/borrower":
		Borrower b = new Borrower();
		BorrowerService bservice = new BorrowerService();
		System.out.println("here");
			try {
				if(bservice.readBorrByPK(1)!= null);
				{
					Borrower borr = bservice.readBorrByPK(1);
					request.setAttribute("cardNo", borr);
					forwardPage = "BorrowerList.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		case "/Borr2":
		    BookLoans bloans = new BookLoans();
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
					
		       Book book = new Book();
			   LibraryBranch lib = new LibraryBranch();
			   Borrower borr = new Borrower();

			   book.setBookId(bookId);
			   lib.setBranchId(branchId);
			   borr.setCardNo(cardNo); 
			   
			bloans.setBooks(book);
		    bloans.setBranch(lib);
		    bloans.setCards(borr); 
		  
		    Date date = new Date(System.currentTimeMillis());
		    bloans.setDateOut(date);
		    bloans.setDueDate(new Date(System.currentTimeMillis() + 7*24*60*60*1000));
			
		    BorrowerService bser = new BorrowerService();
			try {
			bser.checkoutBook(bloans); //	  bservice.checkoutBook(bloans);  
			forwardPage = "Borr2.jsp";
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			break;
			
		case "/Borr2_1":
		    bloans = new BookLoans();
			cardNo = Integer.parseInt(request.getParameter("cardNo"));

		       book = new Book();
			    lib = new LibraryBranch();
			    borr = new Borrower();

			   book.setBookId(bookId);
			   lib.setBranchId(branchId);
			   borr.setCardNo(cardNo); 
			   
			bloans.setBooks(book);
		    bloans.setBranch(lib);
		    bloans.setCards(borr); 
		  
		     date = new Date(System.currentTimeMillis());
		    bloans.setDateIn(date);
		   // bloans.setDueDate(new Date(System.currentTimeMillis() + 7*24*60*60*1000));
			
		     bser = new BorrowerService();
			try {
			bser.returnBook(bloans); //	  bservice.checkoutBook(bloans);  
			forwardPage = "Borr2_1.jsp";
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			break;
		default :
			break;
		}
		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}
}
