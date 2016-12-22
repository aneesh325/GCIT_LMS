package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO
{

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBook(Book book) throws SQLException
	{
		save("insert into tbl_book (title) values (?)", new Object[]{book.getTitle()});
	}
	public void addBookAuthor(Book book, Author author) throws SQLException
	{
	save("insert into tbl_book_authors values (?,?)", new Object[]{book.getBookId(), author.getAuthorId()});
    }

	public void addBookGenre(Book book, Genre genre) throws SQLException
	{
	save("insert into tbl_book_genres values (?,?)", new Object[]{book.getBookId(), genre.getGenreid()});
    }
	
	public Integer addBookWithID(Book book) throws SQLException{
		return saveWithID("insert into tbl_book (title) values (?)", new Object[]{book.getTitle()});
	}
	
	public void create(Book book) throws Exception {
		System.out.println("title on: " + book.getTitle());
		int bookId = saveWithID("insert into tbl_book (title) values(?)",new Object[] {book.getTitle()});
		
		//insert tbl_book_authors
		for (Author a: book.getAuthors()){
			save("insert into tbl_book_authors (bookId, authorId) values(?,?)", new Object[] {bookId, a.getAuthorId()});
		}
		//insert tbl_book_genres
		for(Genre g: book.getGenres()){
			save("insert into tbl_book_genres(bookId, genre_id) values(?,?)", new Object[]{bookId, g.getGenreid()});
		}
	}
	
	
	public void updateBook(Book book) throws SQLException{
		save("update tbl_book set title = ? where bookId = ?", new Object[]{book.getTitle(), book.getBookId()});
	}
	
	
	public void deleteBook(Book book) throws SQLException{
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws SQLException{
		return readAll("select * from tbl_book", null);
	}

	public Book readOne(int bookId) throws Exception {

		List<Book> book = readAllFirstLevel("select * from tbl_book where bookId=?", new Object[] {bookId});
		if(book!=null && book.size()>0){
			return book.get(0);
		}
		return null;
	}

	public Book readBookByPK(Book book) throws SQLException{
		List<Book> booksel =  readAll("select * from tbl_book where bookId = ?", new Object[]{book.getBookId()});
		if(booksel!=null){
			return booksel.get(0);
		}
		return null;	}
	
	
	@Override
	public  List<Book> extractData(ResultSet rs) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Publisher pub = new Publisher();
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		try {
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				pub.setPublisherId(rs.getInt("pubId"));
				System.out.println(rs.getInt("pubId"));
				b.setPublisher(pub);
				b.setAuthors(adao.readAllFirstLevel("select * from tbl_author where authorId IN (Select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()}));
				
				b.setGenres(gdao.readAllFirstLevel("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId=?)", new Object[]{b.getBookId()}));
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return books;
		
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Publisher pub = new Publisher();
		
		try {
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				pub.setPublisherId(rs.getInt("pubId"));
				b.setPublisher(pub);
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return books;
	
	}

	public List<Book> listOfBooksbyBranch(int branchId) throws SQLException 
	{
		return readAll("select * from tbl_book as book JOIN tbl_book_copies as copies ON copies.bookId = book.bookId JOIN tbl_library_branch as lib ON lib.branchId = copies.branchId where lib.branchId = ?",
		new Object []{branchId}); 
//		String libAuthorName4Query = "select auth.authorName from tbl_author as auth JOIN tbl_book_authors as bauth ON bauth.authorId = auth.authorId JOIN tbl_book as book ON book.bookId = bauth.bookId JOIN tbl_book_copies as copies ON copies.bookId = book.bookId JOIN tbl_library_branch as lib ON lib.branchId = copies.branchId where lib.branchId = ?";
		
	
	}

	public List<Book> listOfBorrwBooks(int branchId) throws SQLException 
	{
	return readAllFirstLevel("SELECT * FROM tbl_book JOIN tbl_book_copies as copies ON tbl_book.bookId = copies.bookId WHERE noOfCopies > 0 and branchId = ?", 
			new Object []{branchId});
	}
	
}
