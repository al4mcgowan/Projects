package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;
import ViewControl.*;

public class UserDatabaseOp {
	private Connection conn = null;
	public ResultSet rset = null;
	private PreparedStatement stmt = null;
	private DBConnection db;

	public UserDatabaseOp() {
		db = new DBConnection();
		conn = db.openDB();
	}

	public int deleteUser(String n) {
		int no = 0;
		String cmd = "DELETE FROM Users WHERE xnum = ?";
		try {
		stmt = conn.prepareStatement(cmd);
		stmt.setString(1, n);
		no = stmt.executeUpdate();
		} catch (Exception ex) {

		}
		return no;
	}

	public User findUser(String n) 
	{
		User u = null;
		try 
		{
			String query = "SELECT * FROM Users WHERE xnum like'%" + n + "'";
			stmt = conn.prepareStatement(query);
			rset = stmt.executeQuery();  
			//setRset(stmt.executeQuery());
			String name, xnum, password, address, pnumber, email = null;
			int isAdmin = 0;
			while (rset.next()) {
				name = getRset().getString("name");
				xnum = getRset().getString("xnum");
				password = getRset().getString("password");
				address = getRset().getString("address");
				pnumber = getRset().getString("pnumber");
				email = getRset().getString("email");
				isAdmin = getRset().getInt("isAdmin");
				u = new User(name, xnum, password.toCharArray(), address, pnumber, email, isAdmin);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("");
		}
		return u;
	}
	


	public void addUser(User u) 
	{
		
		try 
		{
			String test = String.valueOf(u.getPassword());;
			String sql = "INSERT INTO Users(id ,name, xnum, password, address, pnumber, email, isAdmin) VALUES(id_seq.nextVal,?,?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, u.getName());
			stmt.setString(2, u.getXnum());
			stmt.setString(3, test);
			stmt.setString(4, u.getAddress());
			stmt.setString(5, u.getPhoneNumber());
			stmt.setString(6, u.getEmail());
			stmt.setInt(7, u.getAdmin());
			stmt.executeUpdate();
		}

		catch (Exception exe) {
			System.out.println("Exception: " + exe);
		}

	}


	public void setRset(ResultSet rset) {
		this.rset = rset;
	}

	public ResultSet returnRset() {
		{
			try
			{
			String queryString = "Select * From Users";
			stmt = conn.prepareStatement(queryString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 
			rset = stmt.executeQuery();
			}
			catch(Exception e)
			{
			System.out.println(e);
			}
			return rset;
			}
	}

	public ResultSet getRset() {
		return rset;
	}
	
	
	
	public void setPhoneNumber(String g, String p) {
		try {
			String sql = "UPDATE Users SET pnumber = ? WHERE xnum = ?";
			stmt = conn.prepareStatement(sql);;
			stmt.setString(1, p);
			stmt.setString(2, g);
			stmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}
	
	public void setEmail(String g, String e) {
		try {
			String sql = "UPDATE Users SET email = ? WHERE xnum = ?";
			stmt = conn.prepareStatement(sql);;
			stmt.setString(1, e);
			stmt.setString(2, g);
			stmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}

}
