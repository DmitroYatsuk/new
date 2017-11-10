package de.re.easymodbus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class JavaToMySQL {

  // JDBC URL, username and password of MySQL server
  private static final String url = "jdbc:mysql://localhost:3306/modbus_schema";
  private static final String user = "root";
  private static final String password = "root";

  // JDBC variables for opening and managing connection
  private static Connection con;
  private static Statement stmt;
  private static ResultSet rs;

  public void getData(){

    String query = "select Register1, Register2, Register3 from modbus_table";

    try {
      // opening database connection to MySQL server
      con = DriverManager.getConnection(url, user, password);

      // getting Statement object to execute query
      stmt = con.createStatement();

      // executing SELECT query
      rs = stmt.executeQuery(query);

      while (rs.next()) {
        int count1 = rs.getInt(1);
        System.out.println("Register1 : " + count1);

        int count2 = rs.getInt(2);
        System.out.println("Register2 : " + count2);

        int count3 = rs.getInt(3);
        System.out.println("Register3 : " + count3);
      }

    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      //close connection ,stmt and resultset here
      try { con.close(); } catch(SQLException se) { /*can't do anything */ }
      try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
      try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }
  }

  public void setData(int[] reg_data){

    String query = "UPDATE modbus_schema.modbus_table SET Register1=" + reg_data[0]+ ", Register2=" + reg_data[1]+ ", Register3=" + reg_data[2]+
            " WHERE idnew_table=1";

    try {
      // opening database connection to MySQL server
      con = DriverManager.getConnection(url, user, password);

      // getting Statement object to execute query
      stmt = con.createStatement();

      // executing SELECT query
      stmt.executeUpdate(query);

    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      //close connection ,stmt and resultset here
      try { con.close(); } catch(SQLException se) { /*can't do anything */ }
      try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
      }
  }
}