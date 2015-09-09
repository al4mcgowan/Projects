package ViewControl;

import javax.swing.*;
import javax.swing.border.*;

import Database.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import Model.*;

public class CreateGame extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 200;
	private JLabel title, publisher, console, price, stock;
	private JTextField tit, pub, con, pri, sto;
	private JButton create, exit;
	private JPanel p1, p2;
	private GameDatabaseOp db;

	public CreateGame(GameDatabaseOp x) 
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Create Game");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(true);

		this.db = x;
		
		p1 = new JPanel();
		p2 = new JPanel();

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);

		// panel
		p1.setLayout(new GridLayout(5, 2));
		p1.setBorder(new TitledBorder("Enter the data to create a new game"));

		title = new JLabel("Title");
		p1.add(title);
		tit = new JTextField(20);
		p1.add(tit);

		publisher = new JLabel("Publisher");
		p1.add(publisher);
		pub = new JTextField(20);
		p1.add(pub);

		console = new JLabel("Console");
		p1.add(console);
		con = new JTextField(20);
		p1.add(con);

		price = new JLabel("Price");
		p1.add(price);
		pri = new JTextField(20);
		p1.add(pri);

		stock = new JLabel("Stock");
		p1.add(stock);
		sto = new JTextField(20);
		p1.add(sto);

		// Panel 2
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		create = new JButton("Create Game");
		create.addActionListener(this);
		p2.add(create);
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setVisible(true);
		p2.add(exit);
	}

	public void addNewGame() {
		String title = tit.getText();
		String publisher = pub.getText();
		String console = con.getText();
		String price = pri.getText();
		String stock = sto.getText();

		if (title.equals("") || publisher.equals("") || console.equals("")
				|| price.equals("") || stock.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter all details");
		} else {
			Game g = new Game(title, publisher, console, price, stock);
			db.addGame(g);

			JOptionPane.showMessageDialog(null, "Game Saved");
			this.setVisible(false);

		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(create)) {
			addNewGame();
		}
		else if (ae.getSource().equals(exit)){
			this.dispose();
		}

	}

}