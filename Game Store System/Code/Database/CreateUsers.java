package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CreateUsers
{
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private DBConnection db;
	private ResultSet rset;

	public CreateUsers() 
	{
		db = new DBConnection();
		conn = db.openDB();
	}

	public void CreateUsersTable() 
	{
		try 
		{
			// Dropping a Table
			String drop = "DROP TABLE Users";
			stmt = conn.prepareStatement(drop);
			stmt.executeUpdate();

			// Dropping a sequence
			String dropseq = "drop sequence id_seq";
			stmt = conn.prepareStatement(dropseq);
			stmt.executeUpdate();

			// Creating a sequence
			String createseq = "create sequence id_seq increment by 1 start with 1";
			stmt = conn.prepareStatement(createseq);
			stmt.executeUpdate(createseq);

			// Create a Table
			String create = "CREATE TABLE Users "
					+ "(id NUMBER PRIMARY KEY, name VARCHAR(40), xnum VARCHAR(40), password VARCHAR(40), "
					+ "address VARCHAR(30), pnumber VARCHAR(30), "
					+ "email VARCHAR(20), isAdmin INTEGER)";

			stmt = conn.prepareStatement(create);
			stmt.executeUpdate(create);

			// Insert data into table
			String insertString = "INSERT INTO Users (id ,name, xnum, password, address, pnumber,email, isAdmin) values(id_seq.nextVal,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(insertString);

			stmt.setString(1, "Stephen");
			stmt.setString(2, "x00088318");
			stmt.setString(3, "abcd");
			stmt.setString(4, "23 Lime Lane");
			stmt.setString(5, "018776543");
			stmt.setString(6, "x00088318@ittd.ie");
			stmt.setInt(7, 0);
			stmt.executeUpdate();
			
			stmt.setString(1, "Alan");
			stmt.setString(2, "x00080345");
			stmt.setString(3, "8025");
			stmt.setString(4, "23 Lime Road");
			stmt.setString(5, "000000");
			stmt.setString(6, "x00080345@ittd.ie");
			stmt.setInt(7, 1);
			stmt.executeUpdate();
			
			stmt.setString(1, "Andrew");
			stmt.setString(2, "x00093888");
			stmt.setString(3, "1234");
			stmt.setString(4, "23 Lime Avenue");
			stmt.setString(5, "222222");
			stmt.setString(6, "x00093888@ittd.ie");
			stmt.setInt(7, 1);
			stmt.executeUpdate();
			
			stmt.setString(1, "John");
			stmt.setString(2, "x00054321");
			stmt.setString(3, "j2013");
			stmt.setString(4, "15 Park Wood");
			stmt.setString(5, "333333");
			stmt.setString(6, "j.long@hotmail.com");
			stmt.setInt(7, 0);
			stmt.executeUpdate();
			
			stmt.setString(1, "Louise");
			stmt.setString(2, "x12345678");
			stmt.setString(3, "amy12");
			stmt.setString(4, "11 Lemon and Lime Road");
			stmt.setString(5, "485897");
			stmt.setString(6, "louise14@gmail.com");
			stmt.setInt(7, 0);
			stmt.executeUpdate();
		}

		catch (SQLException e) 
		{
			System.out.print("SQL Exception " + e);
			System.exit(1);
		}
	}
	
	public void queryUsers()
	{	
		try
		{
			String queryString = "SELECT * FROM Users";			     
		    stmt = conn.prepareStatement(queryString); 
		    rset = stmt.executeQuery();
		    System.out.println("Users Table");   
		    while (rset.next())
		    {
		    	System.out.println(rset.getInt(1) +" " + rset.getString(2)+" "+rset.getString(3)+" "+rset.getString(4)+" "+rset.getString(5)+" "+rset.getString(6)+" "+rset.getString(7)+" "+rset.getString(8));		
		    }	
		
		    conn.commit();
		    stmt.close();
		    db.closeDB();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void main(String args[])
	{
		CreateUsers ct = new CreateUsers();
		ct.CreateUsersTable();
		ct.queryUsers();
	}

}
