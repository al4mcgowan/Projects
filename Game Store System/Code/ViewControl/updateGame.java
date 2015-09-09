package ViewControl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Database.GameDatabaseOp;
import Model.Game;

public class updateGame extends JFrame implements ActionListener {
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
	private GameDatabaseOp gd;
	private Login log;
	private PreparedStatement stmt = null;
	private Connection conn = null;
	private Game game;
	private adminView ad;

	public updateGame(Login l, GameDatabaseOp g, adminView av) 
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Update Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.gd = g;
		this.log = l;
		this.ad = av;
		layout = new GridBagLayout();
		con = new GridBagConstraints();
		Insets a = new Insets(0, 60, 60, 0); // box
		Insets b = new Insets(0, 0, 50, 140); // t1
		Insets c = new Insets(0, 0, 0, 140); // t2
		Insets d = new Insets(43, 50, 0, 50); // save button
		Insets e = new Insets(43, 140, 0, 0); // cancel button
		Insets f = new Insets(0,0,50,295); //price label
		Insets h = new Insets(0,0,0,295); //stock label

		p1 = new JPanel();
		p1.setLayout(layout);

		box = new JComboBox();
		box.removeAllItems();
		box.addItem("Please Select...");
		JLabel p = new JLabel("Price:");
		JLabel s = new JLabel("Stock:");
		
		try 
		{
			ResultSet rset = gd.returnRset();

			while (rset.next())
			{
				box.addItem(rset.getString("title"));
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
				Game g = gd.findGame(value);
				game = g;
				t1.setText(game.getPrice());
				t2.setText(game.getStock());
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
			game.setPrice(t1.getText());
			game.setStock(Integer.parseInt(t2.getText()));
			gd.setPrice(box.getSelectedItem().toString(),
					Integer.parseInt(t1.getText()));
			System.out.println(box.getSelectedItem().toString());
			gd.setStock(box.getSelectedItem().toString(),
					Integer.parseInt(t2.getText()));
			
			this.setVisible(false);
			ad.setVisible(true);
			JOptionPane.showMessageDialog(null, "Saved");
		}
		else 
		{
			this.dispose();
			ad.setVisible(true);
		}
	}

}