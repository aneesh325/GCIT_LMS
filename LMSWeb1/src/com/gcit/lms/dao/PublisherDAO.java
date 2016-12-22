package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO 
{

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void addPublisher(Publisher pub) throws SQLException
	{
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)", new Object []{pub.getPublisherName(),pub.getPublisherAddress(),pub.getPublisherPhone()});
	}

	public void updatePublisher(Publisher pub) throws SQLException
	{
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone =? where publisherId = ? ", new Object[]{pub.getPublisherName(),pub.getPublisherAddress(),pub.getPublisherPhone(),pub.getPublisherId()});	
	}	

	public void deletePublisher(Publisher pub) throws SQLException
	{
		save("delete from tbl_publisher where publisherId = ? ", new Object[]{pub.getPublisherId()});	
	}	

	public List<Publisher> readAllPublishers() throws SQLException{
		return readAllFirstLevel("select * from tbl_publisher", null);
	}
	
	public Publisher readPublisherByPK(int publisherId) throws SQLException{
		List<Publisher> pubList =  readAll("select * from tbl_publisher where publisherId = ?", new Object[]{publisherId});
		if(pubList!=null){
			return pubList.get(0);
		}
		return null;} 

	@Override
	public  List<Publisher> extractData(ResultSet rs) 
	{
		ArrayList<Publisher> publist = new ArrayList<>();
		Publisher pub = new Publisher();
		BookDAO bdao = new BookDAO(conn);
		try {
			while(rs.next())
			{	
				pub.setPublisherId(rs.getInt("publisherId"));  
				pub.setPublisherName(rs.getString("publisherName"));
				pub.setPublisherAddress(rs.getString("publisherAddress"));
				pub.setPublisherPhone(rs.getString("publisherPhone"));
				pub.setBooks(bdao.readAllFirstLevel("select * from tbl_book where publisherId = ?", new Object[]{pub.getPublisherId()}));
				publist.add(pub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publist;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) {
		ArrayList<Publisher> publist = new ArrayList<>();
		Publisher pub;
		BookDAO bdao = new BookDAO(conn);
		try {
			while(rs.next())
			{	
				pub = new Publisher();
				pub.setPublisherId(rs.getInt("publisherId"));  
				pub.setPublisherName(rs.getString("publisherName"));
				pub.setPublisherAddress(rs.getString("publisherAddress"));
				pub.setPublisherPhone(rs.getString("publisherPhone"));
				publist.add(pub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publist;
	}


}
