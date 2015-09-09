package ViewControl;

import java.awt.event.*;

import java.awt.*;

import javax.swing.*;

import Database.*;
import Model.Game;
import Model.User;

public class cusView extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 590;
	private static final int FRAME_HEIGHT = 295;
	private JButton s, logOut, addG, checkOut, database, delAcc, addCart,
			updateInfo, clear;
	private JTextField search, cart, cost;
	private JTextArea results;
	private GridBagLayout layout;
	private GridBagConstraints con;
	private JPanel p1;
	private Login l;
	private boolean inStock = false;
	private User us;
	private int t;
	private Game resultGame;
	String[] cartS = new String[100];
	int cartSi = 0;

	private GameDatabaseOp gd;

	public cusView(Login log, User x) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Customer View");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		gd = new GameDatabaseOp();
		layout = new GridBagLayout();
		con = new GridBagConstraints();
		this.us = x;
		this.l = log;
		Insets a = new Insets(5, 0, 160, 90); // welcome label
		Insets b = new Insets(5, 0, 0, 60); // incart label
		Insets c = new Insets(0, 80, 0, 0); // cart textfield
		Insets d = new Insets(21, 0, 0, 60); // total label
		Insets e = new Insets(21, 80, 0, 0); // cost textfield
		Insets f = new Insets(20, 0, 0, 0); // logout button
		Insets g = new Insets(0, 0, 55, 0); // search textfield
		Insets h = new Insets(0, 20, 55, 0); // s button
		Insets i = new Insets(15, 5, 0, 0); // results jtable
		Insets j = new Insets(43, 0, 0, 0); // delete account button
		Insets k = new Insets(0, 20, 40, 0); // add cart button
		Insets l = new Insets(43, 0, 0, 0); // checkout button
		Insets m = new Insets(0, 156, 0, 0); // updateinfo button
		Insets n = new Insets(0, 0, 40, 0); // clear button

		p1 = new JPanel();
		p1.setLayout(layout);
		JLabel welcome = new JLabel("Welcome, User: " + x.getXnum());
		JLabel incart = new JLabel("In Cart:");
		JLabel total = new JLabel("Total:");

		search = new JTextField(34);
		search.setText("Search");
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search.setText("");
			}
		});
		cart = new JTextField(4);
		cart.setText("0");
		cart.setEditable(false);
		cost = new JTextField(4);
		cost.setText("€ " + t);
		cost.setEditable(false);
		results = new JTextArea(5, 42);
		s = new JButton("Search");
		s.addActionListener(this);
		logOut = new JButton("Log Out");
		logOut.addActionListener(this);
		clear = new JButton("Clear Basket");
		clear.addActionListener(this);
		checkOut = new JButton("Checkout");
		if (inStock == true) {
			checkOut.setEnabled(true);
		} else if (inStock == false) {
			checkOut.setEnabled(false);
		}
		checkOut.addActionListener(this);
		addCart = new JButton("Add to Cart");
		addCart.addActionListener(this);
		delAcc = new JButton("Deactive Account");
		delAcc.addActionListener(this);
		updateInfo = new JButton("Update Account Info");
		updateInfo.addActionListener(this);

		results.setEditable(false);
		addCart.setEnabled(false);
		clear.setEnabled(false);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHWEST;
		con.insets = a;
		p1.add(welcome, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHEAST;
		con.insets = b;
		p1.add(incart, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHEAST;
		con.insets = c;
		p1.add(cart, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHEAST;
		con.insets = d;
		p1.add(total, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHEAST;
		con.insets = e;
		p1.add(cost, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.NORTHWEST;
		con.insets = f;
		p1.add(logOut, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.WEST;
		con.insets = g;
		p1.add(search, con);

		con.gridx = 1;
		con.gridy = 0;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.EAST;
		con.insets = h;
		p1.add(s, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = i;
		p1.add(results, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTHWEST;
		con.insets = j;
		p1.add(delAcc, con);

		con.gridx = 1;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.EAST;
		con.insets = k;
		p1.add(addCart, con);

		con.gridx = 1;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTHEAST;
		con.insets = l;
		p1.add(checkOut, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = m;
		p1.add(updateInfo, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTHWEST;
		con.insets = n;
		p1.add(clear, con);

		add(p1);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(s)) {
			resultGame = gd.findGame(search.getText());
			if (resultGame == null) {
				JOptionPane.showMessageDialog(null, "Game not found");
			} else if (resultGame != null
					&& Integer.parseInt(resultGame.getStock()) > 0) {
				results.setText("Found Game: " + resultGame.getTitle()
						+ " with " + resultGame.getStock()
						+ " copies in stock." + "\n Price: $"
						+ resultGame.getPrice());
				addCart.setEnabled(true);
				addCart.setText("Add " + resultGame.getTitle() + " to basket");
			} else if (resultGame != null
					&& Integer.parseInt(resultGame.getStock()) == 0) {
				JOptionPane.showMessageDialog(null, "This game appears to be out of stock. We're sorry about this!");
				results.setText("Found Game: " + resultGame.getTitle()
						+ " with " + resultGame.getStock()
						+ " copies in stock." + "\n Price: $"
						+ resultGame.getPrice());
			}
		} else if (ae.getSource().equals(logOut)) {
			this.setVisible(false);
			l.setVisible(true);
		} else if (ae.getSource().equals(addCart)) {
			if(resultGame != null){
			try {
				int i = Integer.parseInt(cart.getText());
				i++;
				cart.setText(Integer.toString(i));
				clear.setEnabled(true);
				String pprice = "" + t;
				String cprice = resultGame.getPrice();
				int price = Integer.parseInt(pprice) + Integer.parseInt(cprice);
				t = price;
				cost.setText("€ " + t);
				addCart.setEnabled(false);
				JOptionPane.showMessageDialog(null, resultGame.getTitle()
						+ " added to Cart");
				System.out.println("In add cart");
				if (Integer.parseInt(cart.getText()) > 0) {
					checkOut.setEnabled(true);
				}
				cartS[cartSi] = (resultGame.getTitle());
				cartSi++;
				gd.reduceStock(resultGame.getTitle());
				s.doClick();
			} 
			catch (IndexOutOfBoundsException e) {

			}
			}
			else{
				JOptionPane.showMessageDialog(null, "You have not selected a title");
			}
		} else if (ae.getSource().equals(checkOut)) {
			System.out.println("In checkout");
			JOptionPane.showMessageDialog(null, "Thank You For Shopping With Us");
			cart.setText("0");
			results.setText("Thank you for shopping at XYZ"
					+ "\nYou're bill comes to €" + t + " and you purchased"
					+ "\n");
			cost.setText("€ " + 0);
			for (String element : cartS) {
				if (element != null) {
					results.append(element + "\n");
				} else {
					System.out.print("");
				}
			}
			int stock = Integer.parseInt(resultGame.getStock());
			System.out.println(stock - 1);
			resultGame.setStock(stock - 1);
			resultGame.equals(null);
			checkOut.setEnabled(false);
		} else if (ae.getSource().equals(delAcc)) {
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete your Account?",
					"Delete Account?", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				this.dispose();
				UserDatabaseOp u = new UserDatabaseOp();
				u.deleteUser(us.getXnum());
				JOptionPane.showMessageDialog(null, "Sorry to see you go.");
			}
		}

		else if (ae.getSource().equals(clear)) {
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to clear your basket?",
					"Clear Cart", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				t = 0;
				cost.setText("€ " + t);
				cart.setText("0");
				for (int i = 0; i < cartS.length; i++) {
					if (cartS[i] != null) {
						int stock = Integer.parseInt(gd.findGame(cartS[i].toString()).getStock());
						int stockp = stock + 1;
						gd.setStock(resultGame.getTitle(), stockp);;
					} else {
						System.out.print("");
					}
				search.setText("");
				results.setText("");
				addCart.setText("Add to Cart");
				checkOut.setEnabled(false);
				resultGame.equals(null);
			}
			}
			else if(reply == JOptionPane.NO_OPTION)
			{
				clear.setEnabled(true);
			}
			clear.setEnabled(false);
		}

		else if (ae.getSource().equals(updateInfo)) 
		{
			UserDatabaseOp x = new UserDatabaseOp();
			updateUser up= new updateUser(l, x, this);
			up.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public User getUS()
	{
		return us;
	}
}
