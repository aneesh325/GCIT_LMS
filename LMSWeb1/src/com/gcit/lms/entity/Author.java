package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176911705730175685L;

	private Integer authorId;
	private String authorName;
	private List<Book> books;



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		return result;
	}// assigns a different code for same authors but different books


	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", books=" + books + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}


	public Integer getAuthorId() 
	{
		return authorId;
	}


	public void setAuthorId(Integer authorId) 
	{
		this.authorId = authorId;
	}


	public String getAuthorName() 
	{
		return authorName;
	}

	public void setAuthorName(String authorName) 
	{
		this.authorName = authorName;
	}

	public List<Book> getBooks() 
	{
		return books;
	}

	public void setBooks(List<Book> books) 
	{
		this.books = books;
	}
		
	
	
	
}
