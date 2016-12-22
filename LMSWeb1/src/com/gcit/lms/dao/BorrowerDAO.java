package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class BorrowerDAO extends BaseDAO
{

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBorrower(Borrower borr) throws SQLException
	{
	save("insert into tbl_borrower (name,address,phone) values (?,?,?) ", new Object[]{borr.getName(),borr.getAddress(),borr.getPhone()});	
	}	
	public void updateBorrower(Borrower borr) throws SQLException
	{
	save("update  tbl_borrower set name = ? , address = ? , phone =? where cardNo = ? ", new Object[]{borr.getName(),borr.getAddress(),borr.getPhone(),borr.getCardNo()});	
	}	

	public void deleteBorrower(Borrower borr) throws SQLException
	{
	save("delete from tbl_borrower where cardNo = ? ", new Object[]{borr.getCardNo()});	
	}	

	public List<Book> readAllBooks() throws SQLException{
		return readAll("select * from tbl_book", null);
	}
	public List<Borrower> readAllBorr() throws SQLException{
		return readAllFirstLevel("select * from tbl_borrower", null);
	}

	public Borrower readBorrowerByPK(int cardNo) throws SQLException{
		List<Borrower> borrList =  readAll("select * from tbl_borrower where cardNo = ?", new Object[]{cardNo});
		if(borrList!=null){
			return borrList.get(0);
		}
		return null;} 
	
	
		
		@Override
		public  List<Borrower> extractData(ResultSet rs)
		{
			ArrayList<Borrower> borr = new ArrayList<Borrower>();
			Borrower b = new Borrower();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			try {		
				while(rs.next())
				{	b.setCardNo(rs.getInt("cardNo")); 
					b.setName(rs.getString("name"));
					b.setAddress(rs.getString("address"));
					b.setPhone(rs.getString("phone"));
					b.setBookloans((bldao.readAllFirstLevel("select * from tbl_book_loans where cardNo IN (Select cardNo from tbl_borrower where cardNo = ? )", new Object[]{b.getCardNo()})));
					borr.add(b);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
			return  borr;
		}

		@Override
		public  List<Borrower> extractDataFirstLevel(ResultSet rs) 
		{
			Borrower obj; 
			ArrayList<Borrower> borr = new ArrayList<Borrower>();
			try {
				while(rs.next())
				{	
					obj =  new Borrower();
					obj.setCardNo(rs.getInt("cardNo")); 
					obj.setName(rs.getString("name"));
					obj.setAddress(rs.getString("address"));
					obj.setPhone(rs.getString("phone"));
					borr.add(obj);
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return  borr;
		}

	
	
}
