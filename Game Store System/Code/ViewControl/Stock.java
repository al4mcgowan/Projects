package ViewControl;

import javax.swing.*;
import javax.swing.border.*;

import Database.*;

import java.awt.*;
import java.awt.event.*;

import Model.*;

public class Stock extends JFrame implements ActionListener {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 250;
	private static final int FRAME_X_ORIGIN = 150;
	private static final int FRAME_Y_ORIGIN = 250;
	private JLabel stock;
	private JTextArea stockArea;
	private JButton showStock;
	private JPanel p1, p2, p3;
	private GameDatabaseOp dbo;
	
	public Stock() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("View Stock");
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		// Pane 1
		add(p1, BorderLayout.NORTH);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		stock = new JLabel("Stock");
		p1.add(stock);

		// Panel 2
		add(p2, BorderLayout.CENTER);
		p2.setBorder(new TitledBorder("Stock Display"));
		stockArea = new JTextArea(7, 30);
		p2.add(stockArea);

		// Panel 3
		add(p3, BorderLayout.SOUTH);
		showStock = new JButton("Generate Stock");
		showStock.addActionListener(this);
		p3.add(showStock);

	}

	public void actionPerformed(ActionEvent ae) {

	}

}