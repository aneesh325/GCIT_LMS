package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.services.BorrowerService;
import com.gcit.lms.services.Librarian;

@WebServlet({"/editBranch","/listofBooks","/borrower"})
public class Lib extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Lib() {
        super();
    }
    Librarian libService = new Librarian();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
				String forwardPage = "Index.jsp";
		
		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("editBranch.jsp");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		
		LibraryBranch lib1;
		try {
			lib1 = libService.readBranchByPK(branchId);
			System.out.println("library branch: " + lib1);
			request.setAttribute("branch", lib1);
			rd.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("branchId", branchId);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "Index.jsp";


		switch(reqUrl)
		{
		case "/editBranch":
			String branchName = request.getParameter("branchName");
			String branchAddress = request.getParameter("branchAddress");
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			LibraryBranch lib = new LibraryBranch();
			
			lib.setBranchName(branchName);
			lib.setBranchAddress(branchAddress);
			lib.setBranchId(branchId);
			
			try {
				libService.updateLibraryBranch(lib);
				forwardPage = "LibraryList.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
			break;

		case "/listofBooks":
			Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
			BookCopies bc = new BookCopies();
			bc.setNoOfCopies(noOfCopies);
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			branchId = Integer.parseInt(request.getParameter("branchId"));

			try {
				libService.updateBookCopy(branchId, bookId, noOfCopies);
				forwardPage = "LibraryList.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;	
		case "/borrower":
		int	cardNo = Integer.parseInt(request.getParameter("cardNo"));
		Borrower b = new Borrower();
		BorrowerService bservice = new BorrowerService();
		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("borrower.jsp");
			try {
				if(bservice.readBorrByPK(cardNo)!= null);
				{
					forwardPage = "BorrowerList.jsp";
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "/Borr2":
			branchId = Integer.parseInt(request.getParameter("branchId"));
			bookId = Integer.parseInt(request.getParameter("bookId"));

			
		default :
			break;
		}
		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
		
	}
}
