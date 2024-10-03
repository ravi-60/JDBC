package com.movies;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoviesTask {
	final static String driverName = "com.mysql.jdbc.Driver";
	final static String url = "jdbc:mysql://localhost:3306/insta";
	final static String userName = "root";
	final static String password = "42825123";
	final static String createTable = "create table MOVIE(" + "id int NOT NULL AUTO_INCREMENT , "
			+ "title VARCHAR (255), " + "genre VARCHAR (255)," + "yearOfRelease int," + "PRIMARY KEY (id))";
	final static String createRow = "insert into movie values(?,?,?,?)";
	final static String deleteRow = "DELETE FROM movie WHERE (id = ?)";
	final static String update = "UPDATE movie SET `id` = ?, `title` = ?, `genre` = ?,`yearOfRelease`=? WHERE (`id` = ?)";
	final static String delectTable = "drop table movie";
	final static String showDB = "select * from movie";
	final static String getById = "SELECT * FROM movie WHERE id IN (?)";

	public static Connection getConnection(Connection c) throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		c = DriverManager.getConnection(url, userName, password);
		if (c != null) {
			System.out.println("connected");
		}
		return c;
	}

	public static void createTable(Connection c) throws SQLException {
		Statement s = c.createStatement();
		s.execute(createTable);
		System.out.println("table created");
	}

	public static void createRecords(Connection c, int num, String Mname, String g, int year) throws SQLException {
		PreparedStatement s = c.prepareStatement(createRow);
		s.setInt(1, num);
		s.setString(2, Mname);
		s.setString(3, g);
		s.setInt(4, year);
		s.executeUpdate();
		System.out.println("row is inserted");
	}

	public static void update(Connection c, int id, String title, String genre, int year, int cid) throws SQLException {
		PreparedStatement s = c.prepareStatement(update);
		s.setInt(1, id);
		s.setString(2, title);
		s.setString(3, genre);
		s.setInt(4, year);
		s.setInt(5, cid);
		s.executeUpdate();
		System.out.println("row updated");
	}

	public static void delete(Connection c, int id) throws SQLException {
		PreparedStatement s = c.prepareStatement(deleteRow);
		s.setInt(1, id);
		s.executeUpdate();
		System.out.println("Row is delected");
	}

	public static void dropTable(Connection c) throws SQLException {
		PreparedStatement s = c.prepareStatement(delectTable);
		s.execute();
		System.out.println("Database delected");
	}

	public static void getById(Connection c, int id) throws SQLException {
		PreparedStatement s = c.prepareStatement(getById);
		s.setInt(1, id);
		ResultSet r = s.executeQuery();
		r.next();
		System.out.println("Id: " + r.getInt("id"));
		System.out.println("Title: " + r.getString("title"));
		System.out.println("Genre: " + r.getString("genre"));
		System.out.println("YearOfRelease: " + r.getInt("yearOfRelease"));
	}

	public static void getResult(Connection c) throws SQLException {
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(showDB);
		while (r.next()) {
			System.out.println("Id: " + r.getInt("id"));
			System.out.println("Title: " + r.getString("title"));
			System.out.println("Genre: " + r.getString("genre"));
			System.out.println("YearOfRelease: " + r.getInt("yearOfRelease"));
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection c = null;
		Connection cn = getConnection(c);
		createTable(cn);
//		createRecords(cn, 1, "One Piece", "Action", 1999);
//		createRecords(cn, 2, "Solo Leveling", "Action", 2015);
//		createRecords(cn, 3, "Naruto", "Action", 1999);
//		update(cn, 2, "Solo Leveling", "Action", 2016, 2);
//		delete(cn, 3);
//		getResult(cn);
//		getById(cn, 2);
	}

}
