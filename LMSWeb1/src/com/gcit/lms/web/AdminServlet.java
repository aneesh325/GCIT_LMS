package com.gcit.lms.web;

import java.util.List;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.services.AdminService;
import com.gcit.lms.services.BorrowerService;
@WebServlet({"/addPublisher" ,"/Delpublisher","/DelAuthor","/DelBranch","/DelBorrower","/DelBook","/pageAuthors","/searchAuthors","/EditPublisher","/EditBook", "/addBook","/addAuthor","/addBranch","/addBorrower"
	,"/EditAuthor","/EditBorrower","/EditBranch1","/BookLoans"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		Boolean isAjax = false;
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "Index.jsp";
		AdminService aservice = new AdminService();

		Publisher pub = new Publisher();
		Author author = new Author();
		LibraryBranch lib = new LibraryBranch();
		Borrower borr = new Borrower();
		Book book = new Book();

		switch(reqUrl)
		{
		case "/pageAuthors":
			Integer pageAuthors = Integer.parseInt(request.getParameter("pagenum"));
		    List<Author> authList1 = new ArrayList<Author>();
			try {
				authList1 = aservice.readAllAuthorsbyPagenum(pageAuthors);
				forwardPage = "AuthorList.jsp";
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			request.setAttribute("aList", authList1);
			break;
			
			
		case "/Delpublisher":
			System.out.println("here");
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			pub.setPublisherId(publisherId);
			try {
				aservice.deletePublisher(pub);
				forwardPage = "publisherList.jsp";
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		
		case "/DelAuthor":
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			author.setAuthorId(authorId);
			try {
				aservice.DeleteAuthor(author);
				forwardPage = "AuthorList.jsp";
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			break;
			
		case "/DelBranch":
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			lib.setBranchId(branchId);
			try { 
				aservice.DeleteLibraryBranch(lib); 
				forwardPage = "LibraryListb.jsp";
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "/DelBorrower":
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			borr.setCardNo(cardNo);
			try { 
				aservice.DeleteBorrower(borr); 
				forwardPage = "BorrowerList.jsp";
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "/DelBook":
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			book.setBookId(bookId);
			try { 
				aservice.deletebook(book);
				forwardPage = "BookList.jsp";
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "/searchAuthors":
			searchAuthorAJAX(request, response);
			System.out.println("trur");
            isAjax = true;	
		break;
		}
		if(!isAjax){
		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);}

	}

	private void searchAuthorAJAX(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	String searchString = request.getParameter("searchString");
		List<Author> aList = new ArrayList<Author>();
		try {
			AdminService aservice = new AdminService();	
			aList = aservice.readAllAuthors(1, searchString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("authors", aList);
		StringBuilder str = new StringBuilder();
		
		str.append("<thread><tr><th>#</th><th>AuthorName</th></tr></thread><tbody>");
		 for(Author a : aList){
			 str.append("<tr><td>"+ aList.indexOf(a) +"</td>");
			 str.append("<td>" + a.getAuthorName() +"</td>");
			 str.append("<td><button type='button' class='btn btn-warning' onClick='javascript:location.href='EditAuthor.jsp?authorId="
					 + a.getAuthorId() + "'>Update</button></td>");
		 
			 str.append("<td><button type='button' class='btn btn-danger'onClick='javascript:location.href='DelAuthor?authorId="
					 + a.getAuthorId() + "'>Delete</button></td>");
		 
		 }
		 response.getWriter().append(str);//print writer object
	}
	
//		str.append("<tr><th>#</th><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr>");
//		for (Author a : authors) {
//			str.append("<tr><td>" + authors.indexOf(a) + "</td>");
//			str.append("<td>" + a.getAuthorName() + "</td>");
//			str.append(
//					"<td><button class='btn btn-success' data-toggle='modal' data-target='#editauthormodal' href='editauthor.jsp?authorId="
//							+ a.getAuthorId() + "'>Edit</button></td>");
//			str.append("<td><button class='btn btn-danger' data-toggle='modal'  href='editauthor.jsp?authorId="
//					+ a.getAuthorId() + "'>Delete</button></td>");
//		}

		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPage = "Index.jsp";
		AdminService aservice = new AdminService();

		Publisher pub = new Publisher();
		Author author = new Author();
		LibraryBranch branch = new LibraryBranch();
		Borrower borrower = new Borrower();

		switch(reqUrl)
		{
	
		
		case "/addPublisher":

			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");

			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);

			try {

				aservice.addPublisher(pub);
				// int cardNo = reque.getAttribtes("cardNo")
				forwardPage = "AdminMenu.jsp";

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditPublisher":
			System.out.println("pub");
			publisherName = request.getParameter("publisherName");
			publisherAddress = request.getParameter("publisherAddress");
			publisherPhone = request.getParameter("publisherPhone");
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			pub = new Publisher();

			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);
			pub.setPublisherId(publisherId); 

			try {
				aservice.updatePublisher(pub); 
				forwardPage = "publisherList.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

			break;

		case "/EditAuthor":
			System.out.println("auth");
			String authorName = request.getParameter("authorName");

			Integer authorIdd = Integer.parseInt(request.getParameter("authorId"));
			author.setAuthorId(authorIdd);
			author.setAuthorName(authorName);
			try {
				aservice.updateAuthor(author);
				forwardPage = "AuthorList.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

			break;

		case "/addAuthor":
			authorName = request.getParameter("authorName");
			author.setAuthorName(authorName);
			try {
				aservice.addAuthor(author); //.addPublisher(pub);
				forwardPage = "AdminMenu.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "/addBorrower":

			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			borrower.setName(name);
			borrower.setAddress(address);
			borrower.setPhone(phone);
			try {
				aservice.addBorrower(borrower); 
				forwardPage = "AdminMenu.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditBorrower":
			System.out.println("pub");
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			name = request.getParameter("name");
			address = request.getParameter("address");
			phone = request.getParameter("phone");

			borrower.setCardNo(cardNo);
			borrower.setName(name);
			borrower.setAddress(address);
			borrower.setPhone(phone);
			try {
				aservice.updateBorrower(borrower); 
				forwardPage = "BorrowerList.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

			break;	
		case "/addBranch":
			String branchName = request.getParameter("branchName");
			String branchAddress = request.getParameter("branchAddress");
			System.out.println(branchAddress);
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			try {
				aservice.addLibraryBranch(branch);  //.addAuthor(author); //.addPublisher(pub);
				forwardPage = "LibraryListb.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case "/EditBranch1":
			branchName = request.getParameter("branchName");
			branchAddress = request.getParameter("branchAddress");
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			LibraryBranch lib = new LibraryBranch();

			lib.setBranchName(branchName);
			lib.setBranchAddress(branchAddress);
			lib.setBranchId(branchId);

			try {
				aservice.updateLibraryBranch(lib);
				forwardPage = "LibraryListb.jsp";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

			break;	
			
		case "/EditBook":
			Integer bookId = Integer.parseInt(request.getParameter("bookId")); 
			
			String title = request.getParameter("title");
			Book book = new Book();

			book.setTitle(title);
			book.setBookId(bookId); 

			try {
				aservice.updateBook(book); //updateLibraryBranch(lib);
				forwardPage = "BookList.jsp";
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			break;	
			
			
		case "/BookLoans":
			
			BookLoans bloans = new BookLoans();
			cardNo = Integer.parseInt(request.getParameter("cardNo"));
			branchId = Integer.parseInt(request.getParameter("branchId"));
			bookId = Integer.parseInt(request.getParameter("bookId"));
		    String newdate = request.getParameter("newDueDate");  
			
			book = new Book();
			    Borrower borr = new Borrower();

			   book.setBookId(bookId);
			   branch.setBranchId(branchId);
			   borr.setCardNo(cardNo); 
			
			    bloans.setBooks(book);
			    bloans.setBranch(branch);
			    bloans.setCards(borr); 
			   
			Date utilDate;
			
			try {
				utilDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(newdate);
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			    bloans.setDateOut(utilDate); //setDueDate(utilDate);
			    aservice.BookLoans(bloans);  
				forwardPage = "BorrowerList.jsp";
			   } 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		   // bloans.setDueDate(new Date(System.currentTimeMillis() + 7*24*60*60*1000));
			
			break;

		case "/addBook": 

			title = request.getParameter("bookTitle");
			
			String publisherIds = request.getParameter("selectPublisher");
			String[] authorIds = request.getParameterValues("selectAuthor");
			String[] genreIds = request.getParameterValues("selectGenre");

			pub = new Publisher();
			pub.setPublisherId(Integer.parseInt(publisherIds));
			System.out.println("pubId: " + pub.getPublisherId());
			Book b = new Book();
			b.setTitle(title);
			b.setPublisher(pub);
			
			ArrayList<Author> authors = new ArrayList<Author>();
			ArrayList<Genre> genres = new ArrayList<Genre>();

			AdminService aService = new AdminService();
			try {
				for(String authorId: authorIds){
					authors.add(aService.readOneAuthor(Integer.parseInt(authorId))); 
				}
				b.setAuthors(authors);
				for(String genreId : genreIds) {
					genres.add(aService.readOneGenre(Integer.parseInt(genreId)));
				}
				b.setGenres(genres);
				aService.createBook(b); 
				request.setAttribute("result", "Book Added Successfully.");

			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("result", "Book Add Failed." + e.getMessage());
			}
			forwardPage = "BookList.jsp";

			break;
		default :
			break;
		}
		
			javax.servlet.RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
	}
	

}