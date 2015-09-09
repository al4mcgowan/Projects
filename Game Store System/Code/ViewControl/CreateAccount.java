package ViewControl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Database.*;
import Model.*;

public class CreateAccount extends JDialog implements ActionListener {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 200;
	private JTextField nameF, addF, pnum, emailF, xnum;
	private JPasswordField pass;
	private JButton save, cancel;
	private JPanel p1, p2;
	UserDatabaseOp db;
	Login log;

	public CreateAccount(Login l, UserDatabaseOp u) 
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Create Account");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);

		this.db = u;
		this.log = l;
		
		p1 = new JPanel(new GridLayout(6, 2));
		add(p1);

		// Panel 1
		p1.add(new JLabel("Name: "));
		nameF = new JTextField(10);
		p1.add(nameF);

		p1.add(new JLabel("Address:"));
		addF = new JTextField(10);
		p1.add(addF);

		p1.add(new JLabel("Phone Number:"));
		pnum = new JTextField(10);
		p1.add(pnum);

		p1.add(new JLabel("E-mail:"));
		emailF = new JTextField(10);
		p1.add(emailF);

		p1.add(new JLabel("Xnumber:"));
		xnum = new JTextField(10);
		p1.add(xnum);

		p1.add(new JLabel("Password:"));
		pass = new JPasswordField(10);
		p1.add(pass);

		p1.setBorder(new TitledBorder("User Details"));
		this.add(p1, BorderLayout.CENTER);

		// Panel 2
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		add(p2, BorderLayout.SOUTH);

		save = new JButton("Save");
		save.addActionListener(this);
		p2.add(save);
	}

	public void addNewUser() {
		String name = nameF.getText().toString();
		String address = addF.getText().toString();
		String phone = pnum.getText().toString();
		String email = emailF.getText().toString();
		String Xnum = xnum.getText().toString();
		char[] ps = pass.getPassword();
		int isAdmin = 0;
		

		if (name.equals("") || address.equals("") || phone.equals("")
				|| email.equals("") || Xnum.equals("") || ps.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter all details");
			
		} 
		else 
		{
			User u = new User(name, Xnum, ps, address, phone, email, isAdmin);
			db.addUser(u);

			JOptionPane.showMessageDialog(null, "User Saved");
			this.setVisible(false);
			log.setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(save)) 
		{
			addNewUser();
		}
	}

}