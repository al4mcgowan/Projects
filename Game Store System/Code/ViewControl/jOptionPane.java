package ViewControl;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import Database.*;

public class jOptionPane extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 200;
	private static final int FRAME_X_ORIGIN = 150;
	private static final int FRAME_Y_ORIGIN = 250;
	JLabel question;
	JButton admin, cus, exit;
	JPanel p1, p2;
	Login log;
	UserDatabaseOp d;

	public jOptionPane(Login l, UserDatabaseOp db) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Type of User");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		Container cPane = this.getContentPane();
		setLayout(new GridLayout(2, 1));
		this.log = l;
		this.d = db;
		
		
		question = new JLabel(
				"Would you like to be made 'Admin' or 'Customer'?");
		admin = new JButton("Admin");
		admin.addActionListener(this);
		cus = new JButton("Customer");
		cus.addActionListener(this);
		exit = new JButton("Back");
		exit.addActionListener(this);

		p1 = new JPanel();
		p2 = new JPanel();

		p1.add(question);
		p2.add(admin);
		p2.add(cus);
		p2.add(exit);

		cPane.add(p1);
		cPane.add(p2);
		
	}

	public void actionPerformed(ActionEvent ae)
	{	
		if (ae.getSource().equals(admin)) 
		{
			String password = JOptionPane.showInputDialog ( 
					   null, "To register as an administrator you must enter the registration code!" ); 
			try{
			if(password.equals("javadev"))
			{
				CreateAdmin a = new CreateAdmin(log, d);
				a.setVisible(true);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog (  null, "Sorry! Wrong password!" );
			}}
			catch(Exception e)
			{
				System.out.println("");
			}
		}
		
		else if(ae.getSource().equals(cus))
		{
			CreateAccount ac = new CreateAccount(log, d);
			ac.setVisible(true);
			this.dispose();
		}
		else if(ae.getSource().equals(exit))
		{
			this.setVisible(false);
			log.setVisible(true);
		}
		
	}


}