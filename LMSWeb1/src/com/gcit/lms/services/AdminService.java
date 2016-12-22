package com.gcit.lms.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService
{
	
	
	
	
	
	
	public void addAuthor(Author author) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.AuthorAdd(author);
			BookDAO bdao = new BookDAO(conn);
			//insert bookAuthors table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			conn.close();
		}
	}

	public Author readOneAuthor(int authorId) throws Exception {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			Author author = adao.readOne(authorId);
			return author;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	public Genre readOneGenre(int genreid) throws Exception {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			GenreDAO gdao = new GenreDAO(conn);
			Genre genre = gdao.readOne(genreid);
			return genre;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	public void addAuthorBook(Book book ,Author author) throws SQLException //dont need
	{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			BookDAO bdao = new BookDAO(conn);

			Integer authorId = adao.addAuthorWithID(author);
			author.setAuthorId(authorId); 

			for(Book b: author.getBooks())
			{
				adao.addBookAuthor(b, author);
			} 
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally
		{
			conn.close();
		}
	}
	public void DeleteAuthor(Author author) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.AuthorDelete(author); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}

	public void updateAuthor(Author author) throws SQLException {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.AuthorUpdate(author); // .updateAuthor(author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			conn.close();
		}

	}


	public List<Genre> readAllGenre() {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			GenreDAO gdao = new GenreDAO(conn);
			List<Genre> gList =  gdao.readAllFirstLevel(); // adao.readAllAuthors();  //pdao.readAllPublishers();
			return gList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Author readAuthorByPK(Author author) throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPK(author);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
		return null;
	}


	public Integer getAuthorsCount() throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
		return null;
	}
	
	
	public Integer getAuthorsCountWithSearch(String searchString) throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorCountWithSearch(searchString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
		return null;
	}

	public List<Author> readAllAuthorsbyPagenum(Integer pagenum) {
	Connection conn = null;
	ConnectionUtil conUtil = new ConnectionUtil();
	try {
		conn = conUtil.openConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		List<Author> aList =  adao.readAllBookAuthors(pagenum);  //pdao.readAllPublishers();
		return aList;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	
	public List<Author> readAllAuthorsbySearch(String searchString) { //only for search 
	Connection conn = null;
	ConnectionUtil conUtil = new ConnectionUtil();
	try {
		conn = conUtil.openConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		List<Author> aList1 =  adao.readAllAuthorsbySearch(searchString);  //pdao.readAllPublishers();
		return aList1;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
	
	public List<Author> readAllAuthors(Integer pagenum, String searchString) { //only for search 
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			List<Author> aList1 =  adao.readAllAuthors(pagenum,searchString);  //pdao.readAllPublishers();
			return aList1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<Book> readAllBooks() throws Exception {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BookDAO bdao = new BookDAO(conn);
			List<Book> listOfBooks = bdao.readAllBooks();
			return listOfBooks;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	public Book readOneBook(int bookId) throws Exception {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BookDAO bdao = new BookDAO(conn);
			Book book = bdao.readOne(bookId);
			return book;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}

	public void createBook(Book book) throws Exception {
		Connection conn = ConnectionUtil.openConnection();
		BookDAO bDAO = new BookDAO(conn);

		try {
			System.out.println("title on create: " + book.getTitle());
			bDAO.create(book);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}


	public void updateBook(Book book) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();

		try {
			conn = conUtil.openConnection();
			BookDAO bDAO = new BookDAO(conn);

			bDAO.updateBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}

	public void deletebook(Book book) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();

		try {conn = conUtil.openConnection();
		BookDAO bDAO = new BookDAO(conn);

		bDAO.deleteBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}}

	public List<Publisher> readAllPublishers() {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			List<Publisher> pubList = pdao.readAllPublishers();
			return pubList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Publisher readPublisherByPK(int publisherId) throws SQLException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherByPK(publisherId);
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

	public void addPublisher(Publisher  pub) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.addPublisher(pub);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			conn.close();
		}
	}
	public void deletePublisher(Publisher pub) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(pub);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
	public void updatePublisher(Publisher pub) throws SQLException
	{  
		Connection conn = null;
		try {
			conn = ConnectionUtil.openConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.updatePublisher(pub);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally {
			conn.close();
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

	public void addLibraryBranch(LibraryBranch  lib) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			LibraryBranchDAO libdao = new LibraryBranchDAO(conn);
			libdao.addLibraryBranch(lib); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			conn.close();
		}
	}

	public void DeleteLibraryBranch(LibraryBranch lib) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			LibraryBranchDAO libdao = new LibraryBranchDAO(conn);
			libdao.deleteLibraryBranch(lib);  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
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

	public Borrower readBorrowerByPK(int cardNo) throws SQLException{
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
	public void addBorrower(Borrower  borr) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.addBorrower(borr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			conn.close();
		}
	}
	public void DeleteBorrower(Borrower borr) throws Exception{
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borr);  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
	public List<Borrower> readAllBorrower() throws Exception {
		Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			List<Borrower> listOfBorr = bdao.readAllBorr();// .readAllBooks();
			return listOfBorr;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}
	public void updateBorrower(Borrower borr) throws SQLException
	{  
		Connection conn = null;
		try {
			conn = ConnectionUtil.openConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(borr);	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally {
			conn.close();
		}
	}
	
	public List<BookLoans> ReadAllBookLoans() 
	{   Connection conn = null;
		ConnectionUtil conUtil = new ConnectionUtil();
		try {
			conn = conUtil.openConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			List<BookLoans> blList =  bldao.readAllBookLoans();
			return blList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public void BookLoans(BookLoans loans) throws SQLException 
	{
			Connection conn = null;
			try
			{
				conn = ConnectionUtil.openConnection();
				BookLoansDAO loansDAO = new BookLoansDAO(conn);
				loansDAO.update2(loans); //delete(loans); //.update(loans);  //.delete(loans);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				conn.close();
			}	
		
	}
	



}



	