package ViewControl;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Model.*;
import Database.*;

public class Login extends JFrame implements ActionListener 
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 200;
	private JLabel user, pass1;
	private JTextField xnum;
	private JPasswordField pass;
	private JPanel top, bottom;
	private JButton login, quit, reg;
	private UserDatabaseOp db;
	ResultSet rset = null;
	Container cPane;

	public Login(UserDatabaseOp x) 
	{
		// Initialise the frame and content pane
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setTitle("Welcome to XYZ store!");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		setLocationRelativeTo(null);
		cPane = this.getContentPane();
		Container C = getContentPane();
		setLayout(new GridLayout(2, 1));
		this.db = x;

		top = new JPanel();
		add(top);
		top.setVisible(true);
		user = new JLabel("Please enter your username:");
		top.add(user);
		xnum = new JTextField(10);
		top.add(xnum);
		pass1 = new JLabel("Please enter your password:");
		top.add(pass1);
		pass = new JPasswordField(10);
		top.add(pass);

		bottom = new JPanel();
		add(bottom);
		bottom.setVisible(true);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		reg = new JButton("Sign Up");
		bottom.add(reg);
		reg.addActionListener(this);
		login = new JButton("Login");
		bottom.add(login);
		login.addActionListener(this);
		quit = new JButton("Exit");
		bottom.add(quit);
		quit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(reg))
		{
			jOptionPane j = new jOptionPane(this, db);
			this.setVisible(false);
			j.setVisible(true);
		}

		else if (ae.getSource().equals(login)) 
		{
			try 
			{
				String u = xnum.getText();
				User x = db.findUser(u);
				char[] p = pass.getPassword();
				boolean blnResult = Arrays.equals(p, x.getPassword());
				if (blnResult == true)
				{
					if (x.getAdmin() == 0)
					{
						this.setVisible(false);
						cusView ca = new cusView(this, x);
						ca.setVisible(true);
					} 
					else if (x.getAdmin() == 1)
					{
						this.setVisible(false);
						adminView ca = new adminView(this, x);
						ca.setVisible(true);
					}
				}
				if (xnum.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,
							"Username cannot be left blank!");
				}
			}
			catch (NullPointerException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid Log-In Details");
			}

		}
		else if (ae.getSource().equals(quit))
		{
			System.exit(0);
		}
	}

	public static void main(String[] args) 
	{
		UserDatabaseOp uo = new UserDatabaseOp();
		Login l = new Login(uo);
		l.setVisible(true);
	}
}