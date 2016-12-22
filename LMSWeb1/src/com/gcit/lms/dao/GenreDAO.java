package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO 
{

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addGenre(String query,Genre genre) throws SQLException
	{
	save("insert into tbl_genre (genre_name) VALUES (?)",new Object[]{genre.getGenre_name()});	
	}	
		
	public Integer addGenreWithID(String query,Genre genre) throws SQLException
	{
	return saveWithID("insert into tbl_genre (genre_name) VALUES (?)",new Object[]{genre.getGenre_name()});	
	}


	public void updateGenre(String query, Genre genre) throws SQLException
	{
	save("update from tbl_genre genre_name = ? where genre_id = ?", new Object[]{genre.getGenre_name(),genre.getGenreid()});	 
	}

	public void deleteGenre(String query, Genre genre) throws SQLException
	{
	save("delete from tbl_genre where genre_name = ? ", new Object[]{genre.getGenre_name()});	
	}

	public List<Genre> readAllGenre() throws SQLException  // has return type List<Author>
	{
	return readAllFirstLevel("select * from tbl_genre", null);
	}
	
	@Override
		public List<Genre> readAll(String query, Object[] vals) throws SQLException 
	{

		List<Genre> g =	readAll("select * from tbl_genre", null);  //g is of type List<Genre> and we need to return                                                             	
	return g;                                                   // return type List<Genre> to readAll 

	}
	public List<Genre> readAllFirstLevel() throws Exception {

		List<Genre> genre = readAllFirstLevel("select * from tbl_genre", null);

		return genre;
	}

	public Genre readOne(int genreid) throws Exception {
		List<Genre> Genre =  readAllFirstLevel("select * from tbl_genre where genre_id=?", new Object[] {genreid});
		if(Genre!=null && Genre.size()>0){
			return Genre.get(0);
		}
		return null;
	}
	@Override
	public List<Genre> extractData(ResultSet rs) 
	{
		ArrayList<Genre> gen = new ArrayList<Genre>();
		BookDAO bdao = new BookDAO(conn);
	  Genre ge = new Genre();
		try {
			while(rs.next())
	{
			ge.setGenre_name(rs.getString("genre_name"));
			ge.setGenreid(rs.getInt("genre_id"));
			ge.setBooks((bdao.readAllFirstLevel("select * from tbl_book where bookId IN (Select bookId from tbl_book_genres where genre_id = ?)", new Object[] {ge.getGenreid()})));
			gen.add(ge);
	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return gen;
		
	}


	@Override
	public  List<Genre> extractDataFirstLevel(ResultSet rs) {
		// TODO Auto-generated method stub
		ArrayList<Genre> gen = new ArrayList<Genre>();

		Genre ge;
		try {
			while(rs.next())
			{	ge =  new Genre();
				ge.setGenre_name(rs.getString("genre_name"));
				ge.setGenreid(rs.getInt("genre_id"));
				gen.add(ge);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return gen;


		//return null;
	}

	
	
}
