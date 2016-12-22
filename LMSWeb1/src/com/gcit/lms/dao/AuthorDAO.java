package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO
{

	public AuthorDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub 
	}

public void AuthorAdd(Author author) throws SQLException
{
save("insert into tbl_author (authorName) values (?)",new Object[]{author.getAuthorName()} );
	
}
	
public void addBookAuthor(Book book, Author author) throws SQLException
{
save("insert into tbl_book_authors values (?,?)", new Object[]{book.getBookId(), author.getAuthorId()});
}

public Integer addAuthorWithID(Author author) throws SQLException
{
return saveWithID("insert into tbl_author (authorName) values (?)", new Object[]{author.getAuthorName()});	
}

public void AuthorUpdate(Author author) throws SQLException
{
save("Update tbl_author set authorName = ? where authorId = ?",new Object[]{author.getAuthorName(),author.getAuthorId()} );	
}	


public void AuthorDelete(Author author) throws SQLException
{
save("delete from tbl_author where authorId = ?",new Object[]{author.getAuthorId()} );	
}


public Author readOne(int authorId) throws Exception {
	List<Author> authors =  readAll("select * from tbl_author where authorId=?", new Object[] {authorId});
	if(authors!=null && authors.size()>0){
		return authors.get(0);
	}
	return null;
}

public Author readAuthorByPK(Author author) throws SQLException{
	List<Author> authors =  readAll("select * from tbl_author where authorId = ?", new Object[]{author.getAuthorId()});
	if(authors!=null){
		return authors.get(0);
	}
	return null;
}

public List<Author> readAllAuthors(Integer pagenum, String searchString) throws SQLException{
	setPagenum(pagenum);
	if(searchString!=null && !searchString.isEmpty()){
		searchString = "%"+searchString+"%";
		System.out.println(searchString);
		return readAll("select * from tbl_author where authorName like ?", new Object[]{searchString});
	}else
	{return readAll("select * from tbl_author", null);
	}
}

public List<Author> readAllAuthorsbySearch(String searchString) throws SQLException  // has return type List<Author>
{	setPagenum(null);                                                        //test for only search
	if(searchString!=null && !searchString.isEmpty()){
		searchString = "%'"+searchString+"'%";
		System.out.println(searchString);
		return readAll("select * from tbl_author where authorName like ?", new Object[]{searchString});
	}else
	{return readAll("select * from tbl_author", null);
	}
}


public Integer getAuthorsCount() throws SQLException{
	return getCount("select count(*) AS COUNT from tbl_author", null);
}

public Integer getAuthorCountWithSearch(String searchString) throws SQLException {
	setPagenum(getPagenum());
	if(searchString!=null && !searchString.isEmpty()){
		searchString = "%"+searchString+"%";
		return getCount("select count(*) AS COUNT from tbl_author where authorName like ?", new Object[]{searchString});//keyword like
	}else{
		return getCount("select count(*) AS COUNT from tbl_author", null);
	}
}

@Override
public List<?> extractData(ResultSet rs) {
	// TODO Auto-generated method stub
	ArrayList<Author> author =  new ArrayList<Author>();
//Connection conn = null;
//PreparedStatement pstmt = null;
Author authorobj; 
BookDAO bdao = new BookDAO(conn);
try {

    while(rs.next())
    {authorobj = new Author();
    authorobj.setAuthorId(rs.getInt("authorId"));
    authorobj.setAuthorName(rs.getString("authorName"));
    authorobj.setBooks(bdao.readAllFirstLevel("select * from tbl_book where bookId IN (Select bookId from tbl_book_authors where authorId = ?)", new Object[] {authorobj.getAuthorId()}));
    author.add(authorobj);
    
    }
} 
catch (SQLException e) {
	e.printStackTrace();
}
return author;
}

@Override
public  List<Author> extractDataFirstLevel(ResultSet rs)
{
	ArrayList<Author> author =  new ArrayList<Author>();
	Author authorobj;
	BookDAO bdao = new BookDAO(conn);
	try {
	    while(rs.next())
	    {
	    authorobj  = new Author();	
	    authorobj.setAuthorId(rs.getInt("authorId"));
	    authorobj.setAuthorName(rs.getString("authorName"));
	    author.add(authorobj);
	    System.out.println(authorobj.getAuthorName());
	    }	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return author;
}

public List<Author> readAllBookAuthors(Integer pagenum) throws SQLException{
	setPagenum(pagenum);
	
	return readAll("select * from tbl_author", null);
	
}
}
