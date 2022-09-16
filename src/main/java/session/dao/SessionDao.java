package session.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import database.connection.DBConnection;
import login.dao.LoginDao;
import session.model.Session;

public class SessionDao {

	Connection connection=DBConnection.getConnection();
	String id1=LoginDao.id;
	
	
	public void addUserSession(Session sessionObj)  {
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("INSERT INTO session_data VALUES(?,?,?,?);");
			statement.setString(1,sessionObj.getId());
			statement.setString(2, sessionObj.getSession_id());
			statement.setString(3, sessionObj.getCreation_time());
			statement.setString(4, sessionObj.getBrowser_details());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	
	}
	
	public void removeUserSession(String session_id) throws SQLException {

		PreparedStatement statement = connection.prepareStatement("DELETE FROM session_data WHERE session_id=?;");
		statement.setString(1, session_id);
		statement.executeUpdate();
		expireSessionWithId(session_id);
	}
	
	public List<Session> userSession()throws SQLException{
		
		List<Session> userSessionList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM session_data WHERE id=? ORDER BY creation_time DESC");
		statement.setString(1,id1);
		System.out.println(id1);
		ResultSet result=statement.executeQuery();
		while(result.next()) {
			String id=result.getString("id");
			String session_id=result.getString("session_id");
			String creation_time=result.getString("creation_time");
			String browser_details=result.getString("browser_details");
			System.out.println(id+"  "+session_id+" "+creation_time+" "+browser_details);
			userSessionList.add(new Session(id,session_id,creation_time,browser_details));
			System.out.println("hi"+userSessionList);
		}
		return userSessionList;
		
	}
	public void expireSessionWithId(String sessionID)
	{  
	     try { 
	        MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();

	        ObjectName objectName=new ObjectName("jboss.web:type=Manager,path=/test,host=default-host");

	        // declare signature of the parameter
	        String[] sig = { "java.lang.String"};
	        // declare parameter
	        Object[] opArgs1 = { sessionID };
	        // call the method
	        String value = (String) server.invoke(objectName, "expireSession",
	                opArgs1, sig);

	        System.out.println(value);
	    } catch (MalformedObjectNameException e) {
	        //handle the exception
	    } catch (InstanceNotFoundException e) {
	        //handle the exception
	    } catch (ReflectionException e) {
	        //handle the exception
	    } catch (MBeanException e) {
	        //handle the exception
	    }

	}
}
