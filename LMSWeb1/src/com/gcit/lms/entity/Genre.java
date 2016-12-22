package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1066847902991243572L;

	
	private Integer genreid;
	private String genre_name;
	private List<Book> books;


	public Integer getGenreid() {
		return genreid;
	}

	public void setGenreid(Integer genreid) {
		this.genreid = genreid;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre_name == null) ? 0 : genre_name.hashCode());
		result = prime * result + genreid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genre_name == null) {
			if (other.genre_name != null)
				return false;
		} else if (!genre_name.equals(other.genre_name))
			return false;
		if (genreid != other.genreid)
			return false;
		return true;
	}

	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
