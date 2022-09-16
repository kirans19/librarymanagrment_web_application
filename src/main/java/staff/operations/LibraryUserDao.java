package staff.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.DBConnection;
import login.dao.LoginDao;



public class LibraryUserDao {

	Connection connection=DBConnection.getConnection();
	String id=LoginDao.id;
	
	public List<LibraryUsers> readersToBeBanned() throws SQLException{
		
		  String readerList="SELECT * FROM user_data WHERE user_type='1'";
		  PreparedStatement statement_1=connection.prepareStatement(readerList);
		  ResultSet result_1 = statement_1.executeQuery();
		  List<LibraryUsers> bannedReaderList=new ArrayList<>();
		  
		  while(result_1.next()) {
			 String id=result_1.getString("id");
			 String first_name=result_1.getString("first_name")+" "+result_1.getString("last_name");
			
			  String dueAmountQuery="SELECT SUM(due_amount)FROM book_borrowlog WHERE id=?";
			  PreparedStatement statement_2 = connection.prepareStatement(dueAmountQuery);
			  statement_2.setString(1,id);
			  ResultSet result_2 = statement_2.executeQuery();
				 while(result_2.next()) {
				 if(result_2.getInt(1)>=10000) {
					 bannedReaderList.add(new LibraryUsers(id,first_name));
				  }
				 }
				
		  }
		  return bannedReaderList;
	}
	
	public List<LibraryUsers> readers() throws SQLException{
		
		List<LibraryUsers> readersList=new ArrayList<>();
		String readerList="SELECT * FROM user_data WHERE user_type='1'";
		PreparedStatement statement_1=connection.prepareStatement(readerList);
		ResultSet result_1=statement_1.executeQuery();
		while(result_1.next()) {
			String id=result_1.getString("id");
			String email=result_1.getString("email");
			String first_name=result_1.getString("first_name");
			String last_name=result_1.getString("last_name");
			String mobile_no=result_1.getString("mobile_no");
			String address=result_1.getString("address");
			readersList.add(new LibraryUsers(id,email,first_name,last_name,mobile_no,address));
		}
		return readersList;
	}
	
	public List<LibraryUsers> publishers() throws SQLException{
			
			List<LibraryUsers> publishersList=new ArrayList<>();
			String publisherList="SELECT * FROM user_data WHERE user_type='2'";
			PreparedStatement statement_1=connection.prepareStatement(publisherList);
			ResultSet result_1=statement_1.executeQuery();
			while(result_1.next()) {
				String id=result_1.getString("id");
				String email=result_1.getString("email");
				String first_name=result_1.getString("first_name");
				String last_name=result_1.getString("last_name");
				String mobile_no=result_1.getString("mobile_no");
				String address=result_1.getString("address");
				publishersList.add(new LibraryUsers(id,email,first_name,last_name,mobile_no,address));
			}
			return publishersList;
		}
	
	public boolean banReader(String id) throws SQLException {
		boolean flag=false;
		List<LibraryUsers> bannedReaderList=readersToBeBanned();
		String id2 = id;
		
		for(LibraryUsers obj:bannedReaderList) {
			if(obj.getId().equals(id2)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			
			String addBanReaderToBannedList="update user_data set user_type='0' where id = ?";
			String updateBorrowLog="update book_borrowlog set due_amount='0' where id=?";
			  PreparedStatement statement_3=connection.prepareStatement(addBanReaderToBannedList);
			  PreparedStatement statement_4=connection.prepareStatement(updateBorrowLog);
			  statement_3.setString(1, id2);
			  statement_4.setString(1, id2);
			  int status=statement_3.executeUpdate();
			  statement_4.executeUpdate();
			  if(status>0) {
				  flag=true;
			  }
			  else {
				  flag=false;
			  }
		}
		else {
		
		flag=false;
		}
		return flag;
	}
	
	public boolean deletePublishers(String id) throws SQLException{
		
		boolean check=false;
		List<LibraryUsers> deletePublisherList=publishersToBeDeleted();
		String id2 = id;
		
		for(LibraryUsers obj:deletePublisherList) {
			if(obj.getId().equals(id2)) {
				check=true;
				break;
			}
		}
		if(check==true) {
		PreparedStatement statement=connection.prepareStatement("UPDATE user_data SET user_type='-1' WHERE id=? AND user_type='2'");
		statement.setString(1, id);
		int flag=statement.executeUpdate();
		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
		}
		else {
			return false;
		}
		
	}
	public List<LibraryUsers> publishersToBeDeleted() throws SQLException{
		List<LibraryUsers> noBorrowPublisherList=new ArrayList<>();

		PreparedStatement statement=connection.prepareStatement("SELECT * FROM user_data WHERE user_type='2'");
		ResultSet result=statement.executeQuery();
		while(result.next()) {
		PreparedStatement statement_1=connection.prepareStatement("select count(book_borrowlog.ISBN) AS transaction_count,publisherId FROM book_borrowlog right join book_catalogue ON book_borrowlog.ISBN=book_catalogue.ISBN where publisherId=?");
		statement_1.setString(1,result.getString("id"));
		ResultSet result_1=statement_1.executeQuery();
		while(result_1.next()) {
			if(result_1.getInt("transaction_count")==0) {
			String id=result.getString("id");
			String first_name=result.getString("first_name");
			String last_name=result.getString("last_name");
			noBorrowPublisherList.add(new LibraryUsers(id,first_name,last_name));
			}
		}
		statement_1.close();
		}
		return noBorrowPublisherList;
	}
	
	public List<LibraryUsers> readersDueAmount(String id) throws SQLException{
		List<LibraryUsers> readersDueSlip=new ArrayList<>();
		PreparedStatement statement_1=connection.prepareStatement("select user_data.id,user_data.first_name,user_data.last_name,user_data.mobile_no,user_data.address,sum(book_borrowlog.due_amount)as due_amount from book_borrowlog inner join user_data on book_borrowlog.id=user_data.id where book_borrowlog.id=?;");
		statement_1.setString(1,id);
		ResultSet result_1=statement_1.executeQuery();
		while(result_1.next()) {
			String id1=result_1.getString("id");
			String first_name=result_1.getString("first_name");
			String last_name=result_1.getString("last_name");
			String mobile_no=result_1.getString("mobile_no");
			String address=result_1.getString("address");
			int due_amount=result_1.getInt("due_amount");
			System.out.println(id1+" "+first_name);
			if(id1!=null) {
			readersDueSlip.add(new LibraryUsers(id1,first_name,last_name,mobile_no,address,due_amount));
			}
			
			
		}
		
		return readersDueSlip;
		
	}
	public List<LibraryUsers> profile()throws SQLException{
		
		List<LibraryUsers> user_information=new ArrayList<>();
		PreparedStatement statement=connection.prepareStatement("select * from user_data where id=?");
		statement.setString(1,id);
		ResultSet result=statement.executeQuery();
		while(result.next()) {
			String id1=result.getString("id");
			String email=result.getString("email");
			System.out.println(email);
			String first_name=result.getString("first_name");
			String last_name=result.getString("last_name");
			String mobile_no=result.getString("mobile_no");
			String address=result.getString("address");
			user_information.add(new LibraryUsers(id1, email, first_name, last_name,mobile_no, address));
			
		}
		
		return user_information;
		
	}
	
	
	
}
