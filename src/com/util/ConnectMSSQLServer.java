package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQLServer
{
	Connection con =null;
	String db_connect_string = "jdbc:sqlserver://10.10.11.239;databasename=HosptalRegistration";
	String db_userid = "sa";
	String db_password = "mumsql";
   public Connection dbConnect()
   {
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con = DriverManager.getConnection(db_connect_string,
                  db_userid, db_password);
         System.out.println("connected");
        /* Statement statement = conn.createStatement();
         String queryString = "select * from mydb.dbo.login";
         ResultSet rs = statement.executeQuery(queryString);
         while (rs.next()) {
            System.out.println("Type -> "+rs.getString(1)+": Username ->"+rs.getString(2)+": Password ->"+rs.getString(3)+": Status -> "+rs.getString(4));
         }*/
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return con;
   }

  /* public static void main(String[] args)
   {
      ConnectMSSQLServer connServer = new ConnectMSSQLServer();
    //  connServer.dbConnect("jdbc:sqlserver://<hostname>", "<user>","<password>");
      connServer.dbConnect("jdbc:sqlserver://10.10.11.166", "sa","mumsql");
   }*/
}