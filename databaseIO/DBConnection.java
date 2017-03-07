package databaseIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection()
	{
		final String url, user, password;
		Connection con = null;
		url =  "jdbc:mysql://localhost/users";
		user = "root";
		password = "password";	
		 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try 
		{
           con = DriverManager.getConnection(url, user, password);
           System.out.println("Connection Established");
        } 
		catch (Exception e)
		{
            e.printStackTrace();
        }
		return con;
	}
	public static Statement getStatement(Connection connection) throws SQLException{
		return connection.createStatement( ResultSet.TYPE_FORWARD_ONLY, 
										   ResultSet.CONCUR_UPDATABLE);
	}
	public static boolean isValid(String s){
		boolean val = true;
		if( s.equals("") )
		{
			val = false;
		}
		if( s.equals(null) )
		{
			val = false;
		}
		return val;
	}
	/**
	 * 
	 * @param String s
	 * @return boolean value true if the String is "" or null
	 */
	public static boolean isInValid(String s)
	{
		return !isValid(s);
	}
	/**
	 * 
	 * @param Result set rs 
	 * @return	DefaultTableModel which can be passed to a JTable constructor
	 * @throws SQLException
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException{
		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		
		// Column Names
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for(int column = 1;column <= columnCount;column++)
		{
			columnNames.add(metaData.getColumnName(column));
		}
		
		// data of the columns
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next())
	    {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    return new DefaultTableModel(data, columnNames);
	}
	// Possible function expansion -> Did you mean >> similar search results
	public static ResultSet getUserResultSet(Statement s, String firstName, String lastName){
		String query="";
		
		// Neither name valid
		if( isInValid(firstName) && isInValid(lastName))
		{
			System.out.println("Both strings invalid please enter a string");
			// Prompt to user to re-enter data
		}
		if( isValid(firstName) && isInValid(lastName))
		{
			query = "select * from users where firstName=\""+firstName+"\"";
		}
		if( isInValid(firstName) && isValid(lastName))
		{
			query = "select * from users where lastName=\""+lastName+"\"";
		}
		if( isValid(firstName) && isValid(lastName))
		{
			query = "select * from users where firstName=\""+firstName+"\" AND lastName=\""+lastName+"\"";	
		}
		try
		{
			return s.executeQuery(query);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;		// What is good practice here? Return null or create local var set to null
	}
	public static void getUser(Statement s, String firstName, String lastName)
	{	
		String query="";
		
		// Neither name valid
		if( isInValid(firstName) && isInValid(lastName))
		{
			System.out.println("Both strings invalid please enter a string");
			// Prompt to user to re-enter data
		}
		if( isValid(firstName) && isInValid(lastName))
		{
			System.out.println("b");
			query = "select * from users where firstName=\""+firstName+"\"";
		}
		if( isInValid(firstName) && isValid(lastName))
		{
			System.out.println("c");
			query = "select * from users where lastName=\""+lastName+"\"";
		}
		if( isValid(firstName) && isValid(lastName))
		{
			System.out.println("d");
			query = "select * from users where firstName=\""+firstName+"\" AND lastName=\""+lastName+"\"";	
		}

		ResultSet rows;
		try {
			rows = s.executeQuery(query);
			System.out.println("Query Executed");
			
			while(rows.next())
			{
				System.out.println("Scanning resultSet Loop");
				System.out.print("userID: "			+ rows.getLong("userID") 		+ "  ");
				System.out.print("firstName: "		+ rows.getString("firstName")	+ "  ");
				System.out.println("lastName: "		+ rows.getString("lastName"));
			}
			System.out.println("Scanning resultSet - complete");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void printResultSetConsole(ResultSet rs)
	{
		ResultSet rs_copy;
		rs_copy = rs;
		try 
		{
			while(rs.next())
			{
				System.out.println("Scanning resultSet Loop");
				System.out.print("userID: "			+ rs.getLong("userID") 		+ "  ");
				System.out.print("firstName: "		+ rs.getString("firstName")	+ "  ");
				System.out.println("lastName: "		+ rs.getString("lastName"));
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	public static void printResultSetFrame_incomplete(	ResultSet rs, JFrame frame )
	{
		try 
		{
			JPanel p = null;
			while(rs.next())
			{
				JLabel a = new JLabel();
				JLabel b = new JLabel();
				JLabel c = new JLabel();
				p = new JPanel();
				
				a.setText("userID: "		+ rs.getString("userID"));
				b.setText("firstName: "		+ rs.getString("firstName"));
				c.setText("lastName: "		+ rs.getString("lastName"));
				
				p.add(a);
				p.add(b);
				p.add(c);
				//frame.add(p);
			}
			frame.add(p);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	public static void createUserAccountDB(){}
	public static void addUser(){}
	public static void delUser(){}
}
