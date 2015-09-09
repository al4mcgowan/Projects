package ViewControl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


import Database.UserDatabaseOp;
import Model.User;

public class updateUser extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 220;
	private JButton save, cancel;
	private JTextField t1, t2;
	private JTable results;
	private JComboBox box;
	private JTextArea ar;
	private GridBagLayout layout;
	private GridBagConstraints con;
	private JPanel p1;
	private UserDatabaseOp ud;
	private Login log;
	private PreparedStatement stmt = null;
	private Connection conn = null;
	private User user;
	private cusView cv;

	public updateUser(Login l, UserDatabaseOp x, cusView cv) 
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Update Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.ud = x;
		this.log = l;
		this.cv = cv;
		layout = new GridBagLayout();
		con = new GridBagConstraints();
		Insets a = new Insets(0, 60, 60, 0); // box
		Insets b = new Insets(0, 0, 50, 140); // t1
		Insets c = new Insets(0, 0, 0, 140); // t2
		Insets d = new Insets(43, 50, 0, 50); // save button
		Insets e = new Insets(43, 140, 0, 0); // cancel button
		Insets f = new Insets(0,0,50,295); //Email label
		Insets h = new Insets(0,0,0,295); //PhoneNumber label

		p1 = new JPanel();
		p1.setLayout(layout);

		box = new JComboBox();
		box.removeAllItems();
		box.addItem("Please Select...");
		JLabel p = new JLabel("Email:");
		JLabel s = new JLabel("Phone:");
		
		try 
		{
			ResultSet rset = ud.returnRset();

			while (rset.next())
			{
				if(Integer.parseInt(rset.getString("isAdmin")) == 0 && (rset.getString("xnum").equals(cv.getUS().getXnum())) == true)
				box.addItem(rset.getString("xnum"));
			}

		} 
		catch (SQLException sqle) 
		{
			System.out.println(sqle);
		}

		box.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String value = box.getSelectedItem().toString();
				User us = ud.findUser(value);
				user = us;
				t1.setText(user.getEmail());
				t2.setText(user.getPhoneNumber());
			}
		});

		ar = new JTextArea(6, 10);
		t1 = new JTextField(10);
		t2 = new JTextField(10);
		save = new JButton("Save");
		save.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.WEST;
		con.insets = a;
		p1.add(box, con);

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = b;
		p1.add(t1, con);
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = c;
		p1.add(t2, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = d;
		p1.add(save, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		con.gridheight = 1;
		con.anchor = GridBagConstraints.SOUTH;
		con.insets = e;
		p1.add(cancel, con);
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = f;
		p1.add(p, con);
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		con.gridheight = 3;
		con.anchor = GridBagConstraints.CENTER;
		con.insets = h;
		p1.add(s, con);
		
		add(p1);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(save))
		{
			user.setEmail(t1.getText());
			user.setPhoneNumber(t2.getText());
			ud.setEmail(user.getXnum(), t1.getText());
			ud.setPhoneNumber(user.getXnum(), t2.getText());
			
			
			this.setVisible(false);
			cv.setVisible(true);
			JOptionPane.showMessageDialog(null, "Saved");
		}
		else 
		{
			this.dispose();
			cv.setVisible(true);
		}
	}

}