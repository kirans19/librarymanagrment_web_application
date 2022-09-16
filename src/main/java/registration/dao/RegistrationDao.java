package registration.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.DBConnection;
import password.hash.PasswordHashing;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import registration.model.Register;


public class RegistrationDao {
	
	
	Connection connection=DBConnection.getConnection();
	public int registerEmployee(Register register) throws ClassNotFoundException{
			
			
	        String INSERT_USERS_SQL = "INSERT INTO user_data(email,password,first_name,last_name,mobile_no,address,user_type)  VALUES(?, ?, ?, ?, ?, ?, ?)";

	        int result = 0;
	        
	        try {
	        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
	        preparedStatement.setString(1,register.getEmail());
	        preparedStatement.setString(2,PasswordHashing.doHashing(register.getPassword()));
	        preparedStatement.setString(3,register.getFirst_name());
	        preparedStatement.setString(4,register.getLast_name());
	        preparedStatement.setString(5,register.getMobile_no());
	        preparedStatement.setString(6,register.getAddress());
	        preparedStatement.setString(7,register.getUser_type());
	           
	        result = preparedStatement.executeUpdate();
	            
	       
	        }
	        catch(SQLException e) {
	        	//printSQLException(e);
	        	result=0;
	        	
	        }
	        return result;
	}
/**public List<String> emailValidator() throws SQLException{
		
		List<String> emailList=new ArrayList<>();
		String getEmailQuery="SELECT email FROM user_data";
		
		PreparedStatement statement_1=DBConnection.getConnection().prepareStatement(getEmailQuery);
		ResultSet result_1=statement_1.executeQuery();
		
		while(result_1.next()) {
			emailList.add(result_1.getString("email"));
			
		}
		
		return emailList;

		
	}
public List<String> mobileNoValidator() throws SQLException{
	
	List<String> mobileNoList=new ArrayList<>();
	String getEmailQuery="SELECT mobile_no FROM user_data";
	
	PreparedStatement statement_1=DBConnection.getConnection().prepareStatement(getEmailQuery);
	ResultSet result_1=statement_1.executeQuery();
	
	while(result_1.next()) {
		mobileNoList.add(result_1.getString("mobile_no"));
	}
	
	return mobileNoList;

	
}*/
	
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	}


