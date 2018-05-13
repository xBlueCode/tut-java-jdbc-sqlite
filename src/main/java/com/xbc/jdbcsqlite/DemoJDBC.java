package com.xbc.jdbcsqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DemoJDBC {

  public static void main(String[] args) throws ClassNotFoundException{
    // TODO: Load the JDBC driver (JDBC class implements java.sql,Driver)
    Class.forName("org.sqlite.JDBC");

    // TODO: Create a DB Connection
    String url = "jdbc:sqlite:shop-manager.db";
    try(Connection connection = DriverManager.getConnection(url)) {

      System.out.println("Connected to database: shop-manager.db.");

      // TODO: Create a SQL statement
      Statement statement = connection.createStatement();
      System.out.println("Initializing the database ...");

      // TODO: Create  a DB table
      statement.executeUpdate("DROP TABLE IF EXISTS products");
      statement.executeUpdate(
          "CREATE TABLE products"
              + "(id INTEGER PRIMARY KEY, "
              + "code STRING(12), "
              + "price INTEGER(6), "
              + "Quantity INTEGER(6))");

      System.out.println("products table created.");

      // TODO: Insert some members
      System.out.print("How many product you want to add ?:  ");
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      for (int i = 0; i < n; i++) {

        System.out.println("Product N "+i+" (code price quantity):  ");
         statement.executeUpdate(
             "INSERT INTO products (code, price, quantity) "
             + "VALUES(" //  +"'abc', 1200, 12)");
                 + "'"+scanner.next()+"', "
                 + scanner.nextInt()+", "
                 + scanner.nextInt()+")");
      }

      // TODO: Fetch all the records from the table
      ResultSet rs = statement.executeQuery("SELECT * FROM products");

      // TODO: Iterate over the ResultSet and Display contact info
      while (rs.next()) {
        System.out.println(
            "Product: ID: " + rs.getInt("id") +" | "
                + " Code: " + rs.getString("code") +" | "
                + " Price: " + rs.getInt("price") +" | "
                + " Quantity: " + rs.getInt("quantity")
        );
      }

    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }
}
