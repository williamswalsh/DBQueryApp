package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import account.*;
import databaseIO.*;
import userIO.Framed_GUI;
import userIO.GetInput;
/*
Main class which creates an instance of the Framed_GUI class.
This class throws an SQLException.

*/
public class Main {

	public static void main(String[] args) throws SQLException {
		new Framed_GUI();
	}
}
