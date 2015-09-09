package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateGames
{
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private DBConnection db;
	private ResultSet rset;

	public CreateGames()
	{
		db = new DBConnection();
		conn = db.openDB();
	}

	public void createGameTable()
	{

		try {
			// dropping a sequence
			String dropseq = "drop sequence stockid";
			stmt = conn.prepareStatement(dropseq);
			stmt.executeUpdate();

			// Dropping a Table
			String drop = "DROP TABLE Game";
			stmt = conn.prepareStatement(drop);
			stmt.executeUpdate();

			String createseq = "create sequence stockid increment by 1 start with 1";
			stmt = conn.prepareStatement(createseq);
			stmt.executeUpdate(createseq);

			String create = "CREATE TABLE Game "
					+ "(stockid NUMBER PRIMARY KEY, title VARCHAR(40), publisher VARCHAR(40), console VARCHAR(40), price VARCHAR(10), stock VARCHAR(10))";
			stmt = conn.prepareStatement(create);
			stmt.executeUpdate(create);

			String insertString = "INSERT INTO Game (stockid, title, publisher, console, price, stock) values(stockid.nextVal,?,?,?,?,?)";
			stmt = conn.prepareStatement(insertString);

			stmt.setString(1, "Kingdom Hearts");
			stmt.setString(2, "SquareSoft");
			stmt.setString(3, "PlayStation 2");
			stmt.setString(4, "20");
			stmt.setString(5, "6");
			stmt.executeUpdate();

			stmt.setString(1, "Kingdom Hearts 2");
			stmt.setString(2, "SquareSoft");
			stmt.setString(3, "PlayStation 2");
			stmt.setString(4, "25");
			stmt.setString(5, "3");
			stmt.executeUpdate();

			stmt.setString(1, "Grand Theft Auto: San Andreas");
			stmt.setString(2, "Rockstar");
			stmt.setString(3, "PlayStation 2");
			stmt.setString(4, "15");
			stmt.setString(5, "1");
			stmt.executeUpdate();

			stmt.setString(1, "Grand Theft Auto: Triple Pack");
			stmt.setString(2, "Rockstar");
			stmt.setString(3, "PlayStation 2");
			stmt.setString(4, "30");
			stmt.setString(5, "3");
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.print("SQL Exception " + e);
			System.exit(1);
		}
	}
	
	public void queryGames()
	{	
		try
		{
			String queryString = "SELECT * FROM Game";			     
		    stmt = conn.prepareStatement(queryString); 
		    rset = stmt.executeQuery();
		    System.out.println("Game Table");   
		    while (rset.next())
		    {
		    	System.out.println(rset.getInt(1) +" " + rset.getString(2)+" "+rset.getString(3)+" "+rset.getString(4)+" "+rset.getString(5)+" "+rset.getString(6));		
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
		CreateGames ct = new CreateGames();
		ct.createGameTable();
		ct.queryGames();
	}
}
