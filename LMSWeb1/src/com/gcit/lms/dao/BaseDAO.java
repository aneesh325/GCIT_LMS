package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 977789196208978647L;
	public static Connection conn = null;
	private Integer pagenum;
	private Integer pagenum1;
	private Integer pagesize = 5;

	public BaseDAO(Connection conn) 
	{
		super();
		this.conn = conn;
	}
	
	
	
	
	public void save(String query, Object vals[]) throws SQLException 
	{
		PreparedStatement pstmt = null;		
		try {
			pstmt = conn.prepareStatement(query);
			if(vals != null){	
				int count = 1;
				for(Object o : vals)
				{
					pstmt.setObject(count, o);
					count++;   
				}
				pstmt.executeUpdate();
			}    }//end of try
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}                    //<T>List<T> can be type casted to any of the List. We are saying not to cast to anything 
	//        because I will cast it to one of the List types
	public Integer saveWithID(String query, Object vals[]) throws SQLException 
	{
		PreparedStatement pstmt = null;		
		try {
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			if(vals != null){	
				int count = 1;
				for(Object O : vals)
				{
					pstmt.setObject(count, O);
					count++;   
				}
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					return rs.getInt(1);
				}
				else
					return -1;
			} }
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	} 
	public  <T>List<T> readAll(String query, Object[] vals) throws SQLException  // has a return type  of a //generic list <T>List<T> and is accepting parameters
	{ 			
		PreparedStatement pstmt = null;
		if(getPagenum()!= null && getPagenum() >0)
		{
			Integer limit = (getPagenum()-1) * getPagesize();
			query = query + " LIMIT " + limit + "," + getPagesize();
		}
		try {
			pstmt = conn.prepareStatement(query);
			if(vals != null) {
				int count = 1;
				for(Object O : vals)
				{
					pstmt.setObject(count, O);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			List<T> temp = (List<T>) extractData(rs);
			return temp;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public abstract <T>List<T> extractData(ResultSet rs);
	
	public <T>List<T> readAllFirstLevel(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			return (List<T>) extractDataFirstLevel(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public abstract <T>List<T> extractDataFirstLevel(ResultSet rs);
	
	
	public Integer getCount(String query, Object[] vals) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			if(vals!=null){
				int count=1;
				for(Object o: vals){
					pstmt.setObject(count, o);
					count++;
				}
			}
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public Integer getPagenum() {
		return pagenum;
	}


	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}




	public Integer getPagesize() {
		return pagesize;
	}


	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}


	public Integer getPagenum1() {
		return pagenum1;
	}




	public void setPagenum1(Integer pagenum1) {
		this.pagenum1 = pagenum1;
	}
	
	
	
}
