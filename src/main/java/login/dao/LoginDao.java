package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.connection.DBConnection;
import login.model.LoginBean;

public class LoginDao {
	
	public static String id;
	public static String mode;
	public String validate(LoginBean loginBean) {
		
		String status ="";
		Connection con=DBConnection.getConnection();
		String sql = "select user_type,id from user_data where email = ? and password =?";
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, loginBean.getUsername());
		ps.setString(2, loginBean.getPassword());
		System.out.println(loginBean.getPassword());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			status=rs.getString("user_type");
			mode=status;
			id=rs.getString("id");
		}
		//System.out.println(id);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
}
