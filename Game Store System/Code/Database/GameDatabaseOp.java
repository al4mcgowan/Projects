package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.*;
import ViewControl.*;

public class GameDatabaseOp {
	private Connection conn = null;
	public ResultSet rset = null;
	private PreparedStatement stmt = null;
	private DBConnection db;

	public GameDatabaseOp() {
		db = new DBConnection();
		conn = db.openDB();
	}

	public void deleteGame(String n) {
		String cmd = "DELETE FROM Game WHERE title = ?";
		try {
			stmt = conn.prepareStatement(cmd);
			stmt.setString(1, n);
			stmt.executeUpdate();
			
		} catch (Exception ex) 
		{

		}
	}

	public Game findGame(String n) {
		Game g = null;
		try {
			String query = "SELECT * FROM Game WHERE title like '%" + n + "%'";
			stmt = conn.prepareStatement(query);
			rset = stmt.executeQuery();
			String title, publisher, console, price, stock = null;
			if(rset.next()) {
				title = getRset().getString("title");
				publisher = getRset().getString("publisher");
				console = getRset().getString("console");
				price = getRset().getString("price");
				stock = getRset().getString("stock");
				g = new Game(title, publisher, console, price, stock);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, n + " not found");
		}
		return g;
	}

	public void addGame(Game g) {
		try {
			String sql = "INSERT INTO Game(stockid, title, publisher, console, price, stock) VALUES(stockid.nextVal,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, g.getTitle());
			stmt.setString(2, g.getPublisher());
			stmt.setString(3, g.getConsole());
			stmt.setString(4, g.getPrice());
			stmt.setString(5, g.getStock());

			stmt.executeUpdate();
		}

		catch (Exception exe) {
			System.out.println("Exception: " + exe);
		}

	}

	public void setStock(String g, int i) {
		try {
			String sql = "UPDATE Game SET stock = ? WHERE title = ?";
			stmt = conn.prepareStatement(sql);
			String stock = "" + i;
			stmt.setString(1, stock);
			stmt.setString(2, g);
			stmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}

	public void setPrice(String g, int i) {
		try {
			String sql = "UPDATE Game SET price = ? WHERE title = ?";
			stmt = conn.prepareStatement(sql);
			String price = "" + i;
			stmt.setString(1, price);
			stmt.setString(2, g);
			if(stmt.executeUpdate() == 1) {
				System.out.println("Update Success");
			} else {
				System.out.println("Update Failed");
			}
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
	}
	
	public void reduceStock(String g) {
		try {
			String sql = "UPDATE Game SET stock = ? WHERE title = ?";
			Game gm = findGame(g);
			String s = gm.getStock();
			int stock = Integer.parseInt(s);
			int stockR = stock - 1;
			String stockx = String.valueOf(stockR);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stockx);
			stmt.setString(2, g);
			
			if(stmt.executeUpdate() == 1) {
				System.out.println("Update Success");
				
			} else {
				System.out.println("Update Failed");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//System.out.println("Exception: " + );
		}
	}

	public void setGRset(ResultSet rset) {
		this.rset = rset;
	}

	public ResultSet returnRset() {
		try {
			String queryString = "Select * From Game";
			stmt = conn
					.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			rset = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}

	public ResultSet getRset() {
		return rset;
	}
}