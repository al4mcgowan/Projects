package ViewControl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import Database.GameDatabaseOp;
import Database.GameTable;
import Database.UserDatabaseOp;
import Database.UsersTable;
import Model.*;

public class adminView extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 490;
	private static final int FRAME_HEIGHT = 290;
	private Connection conn = null;
	public ResultSet rset = null;
	private PreparedStatement stmt = null;
	private JButton s, back, logOut, addG, database, delAcc, delG, updateInfo, stock;
	private JTextField search;
	private JTextArea results;
	private GridBagLayout layout;
	private GridBagConstraints con;
	private JPanel p1;
	private User us;
	private Login log;
	private Game resultGame;
	private GameDatabaseOp gd;
	private int viewStock = 0;
	GameTable gt = new GameTable();

	public adminView(Login l, User u) 
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Admin View");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.log = l;
		us = u;
		layout = new GridBagLayout();
		con = new GridBagConstraints();
		Insets a = new Insets(5, 0, 160, 90); // welcome message
		Insets b = new Insets(20, 0, 0, 0); // logout button
		Insets c = new Insets(0, 0, 55, 0); // search box
		Insets d = new Insets(0, 20, 55, 0); // search button
		Insets e = new Insets(23, 5, 0, 0); // results table
		Insets f = new Insets(43, 0, 0, 0); // delAcc button
		Insets g = new Insets(0, 0, 35, 72); // delete Game button
		Insets h = new Insets(0, 72, 35, 0); // add Game button
		Insets i = new Insets(0, 156, 35, 0); // update game info button
		Insets j = new Insets(0, 156, 0, 0); // check stock button
		Insets k = new Insets(0, 72, 0, 0); //Print report button

		p1 = new JPanel();
		p1.setLayout(layout);
		JLabel welcome = new JLabel("Welcome, User: " + u.getXnum());

		search = new JTextField(34);
		search.setText("Search");
		search.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				search.setText("");
			}
		});
		results = new JTextArea(5, 42);
		
		s = new JButton("Search");
		s.addActionListener(this);
		logOut = new JButton("Log Out");
		logOut.addActionListener(this);
		addG = new JButton("Add Game");
		addG.addActionListener(this);
		delAcc = new JButton("Deactivate Account");
		delAcc.addActionListener(this);
		delG = new JButton("Delete Game");
		delG.addActionListener(this);
		updateInfo = new JButton("Update Game Info");
		updateInfo.addActionListener(this);
		stock = new JButton("Check Stock");
		stock.addActionListener(this);
		delG.setEnabled(false);
		gt.setVisible(false);
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHWEST;
		con.insets = a;
		p1.add(welcome, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHWEST;
		con.insets = b;
		p1.add(logOut, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.WEST;
		con.insets = c;
		p1.add(search, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.EAST;
		con.insets = d;
		p1.add(s, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = e;
		p1.add(results, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTHWEST;
		con.insets = f;
		p1.add(delAcc, con);
		
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTHWEST;
		con.insets = g;
		p1.add(delG, con);
		
		con.gridx = 1;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = h;
		p1.add(addG, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = i;
		p1.add(updateInfo, con);
		
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = j;
		p1.add(stock, con);
		

		add(p1);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(s)) 
		{
			System.out.println(search.getText());
			resultGame = gd.findGame(search.getText());
			if (resultGame == null) 
			{
				JOptionPane.showMessageDialog(null, "Game not found");
			}
			else
			{
				results.setText("Found Game: " + resultGame.getTitle()
						+ " with " + resultGame.getStock()
						+ " copies in stock." + "\n Price: $"
						+ resultGame.getPrice());
				delG.setEnabled(true);
			}

		} 
		else if (ae.getSource().equals(logOut))
		{
			this.setVisible(false);
			log.setVisible(true);
		}
		else if (ae.getSource().equals(addG)) 
		{
			GameDatabaseOp g = new GameDatabaseOp();
			CreateGame c = new CreateGame(g);
			c.setVisible(true);

		} 
		else if (ae.getSource().equals(delAcc))
		{
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete your Account?",
					"Delete Account", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION)
			{
				this.dispose();
				UserDatabaseOp u = new UserDatabaseOp();
				u.deleteUser(us.getXnum());
				JOptionPane.showMessageDialog(null, "Sorry to see you go.");
			}
		} 
		else if (ae.getSource().equals(delG))
		{
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete your Game?",
					"Delete Game?", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION)
			{
				gd.deleteGame(resultGame.getTitle());
				search.setText("");
				results.setText("");
				JOptionPane
						.showMessageDialog(
								null,
								"DELETED, the game: "
										+ resultGame.getTitle()
										+ " will no longer appear in searches and is unavailable");
				resultGame.equals(null);
				delG.setEnabled(false);
			}
		} 
		else if (ae.getSource().equals(updateInfo))
		{
			GameDatabaseOp g = new GameDatabaseOp();
			updateGame u = new updateGame(log, g, this);
			this.setVisible(false);
			u.setVisible(true);

		}
		else if(ae.getSource().equals(stock))
		{
			if(viewStock == 0)
			{
				viewStock = 1;
			gt.setVisible(true);
			gt.setLocationRelativeTo(null);}
			else if(viewStock == 1){
			gt.setVisible(false);
			viewStock = 0;}
		}
	}

}