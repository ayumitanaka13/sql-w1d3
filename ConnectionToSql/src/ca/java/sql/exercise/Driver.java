package ca.java.sql.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Date;

import ca.java.sql.exercise.connection.DbConstants;

public class Driver {
	
	private static final String ALL_DEP_QUERY = "SELECT * FROM Department";
	private static final String ALL_EMP_QUERY = "SELECT * FROM Employee";
	private static final String ADD_EMP_QUERY = 
			"INSERT INTO Employee(emp_fname, emp_lname, start_date, dep_id) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_EMP_QUERY = "UPDATE Employee SET emp_lname = ? WHERE emp_id = ?";
	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.
				getConnection(DbConstants.CONN_STRING, DbConstants.USER, DbConstants.PASSWORD);
		return conn;
	}
	
	public static ResultSet getDate(Connection conn, String query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	public static void addEmployee(Connection conn) throws SQLException {
		PreparedStatement prestmt = null;
		prestmt = conn.prepareStatement(ADD_EMP_QUERY);
		prestmt.setString(1, "Ayumi");
		prestmt.setString(2, "Tanaka");
		prestmt.setDate(3, Date.valueOf("2020-01-01"));
		prestmt.setInt(4, 1);
		prestmt.executeUpdate();
	}
	
	public static void updateEmployee(Connection conn) throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the last name you want to update : ");
		String lname = input.nextLine();
		System.out.println("Enter the id you want to update : ");
		int empId = input.nextInt();
	
		PreparedStatement prestmt = null;
		prestmt = conn.prepareStatement(UPDATE_EMP_QUERY);
		prestmt.setString(1,lname);
		prestmt.setInt(2, empId);
		prestmt.executeUpdate();
		
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prestmt = null;
		
		try {
			
//			conn = getConnection();
//			ResultSet rsDep = getDate(conn, ALL_DEP_QUERY );
//			while(rsDep.next()) {
//				System.out.println(
//						rsDep.getInt(1) + " : " + rsDep.getString(2));
//			}
//			
//			System.out.println("Adding Employee to DB");
//			stmt = conn.createStatement();
//			stmt.executeUpdate("INSERT INTO EMPLOYEE(emp_fname, emp_lname, start_date, dep_id) VALUES('John', 'Doe', '2020-01-01', 1)");
//			ResultSet rsEmp = getDate(conn, ALL_EMP_QUERY);
//			while(rsEmp.next()) {
//				System.out.println(rsEmp.getString("emp_fname") + " " + rsEmp.getString("emp_lname"));
//			}
			
			//Add Employee
//			conn = getConnection();
//			addEmployee(conn);
			
			//Update Employee
			conn = getConnection();
			updateEmployee(conn);
			
			//Print Employees
			System.out.println("List of Employees");
			ResultSet rs = getDate(conn, ALL_EMP_QUERY );
			
			while(rs.next()) {
				//System.out.println(rs.getString("emp_fname") + " " + rs.getString("emp_lname"));
				System.out.println(rs.getString(2) + " " + rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

}
