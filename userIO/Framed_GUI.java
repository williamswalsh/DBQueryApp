package userIO;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseIO.DBConnection;

public class Framed_GUI extends JFrame{
	
	JTable table;
	JScrollPane scrollPane;
	JPanel tablePanel;
	JPanel inputPanel;
	DefaultTableModel tableVal = null;
	
	private static final long serialVersionUID = 1L;
	// Should I initialize Objects to null ? Peter Q
	public static Statement s = null;
	public static ResultSet rs = null;
	public Framed_GUI()
	{
		this.setTitle("Database IO GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel firstName = new JLabel("First Name:");
		JLabel lastName = new JLabel("Last Name:");
		JTextField firstNameField = new JTextField(20);
		JTextField lastNameField = new JTextField(20);
		JButton btn = new JButton();
		btn.setText("Search");
		btn.addActionListener(new ActionListener()
									{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					s = DBConnection.getStatement(DBConnection.getConnection());
					rs = DBConnection.getUserResultSet(s, firstNameField.getText(), lastNameField.getText());
					//DBConnection.printResultSetConsole(rs);
					printResultSet_V2();
					//repaint(); ?? >> Calls paint method of all JComponent in this. frame
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		inputPanel = new JPanel();
		inputPanel.add(firstName);
		inputPanel.add(firstNameField);
		inputPanel.add(lastName);
		inputPanel.add(lastNameField);
		inputPanel.add(btn);
		this.add(inputPanel, BorderLayout.NORTH);
		
		this.pack();
		this.setVisible(true);
	}
	public void printResultSet()
	{
		try 
		{
			JPanel p = null;
			while(rs.next())
			{
				JLabel a = new JLabel();
				JLabel b = new JLabel();
				JLabel c = new JLabel();
				p = new JPanel();
				
				a.setText("userID: "		+ rs.getString("userID"));
				b.setText("firstName: "		+ rs.getString("firstName"));
				c.setText("lastName: "		+ rs.getString("lastName"));
				
				p.add(a);
				p.add(b);
				p.add(c);
				this.add(p,BorderLayout.SOUTH);
			}
			this.pack();
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	public void printResultSet_V2() throws SQLException 
	{	
		try 
		{
			tableVal = DBConnection.buildTableModel(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		table = new JTable(tableVal);
		scrollPane = new JScrollPane(table);
		tablePanel = new JPanel();
		tablePanel.add(scrollPane);
		this.add(tablePanel,BorderLayout.SOUTH);
		this.pack();
	}
}
