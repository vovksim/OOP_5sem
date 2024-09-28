package com.musicsys.Database;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
  private static HikariDataSource dataSource;

  public static void init(String url, String user, String passwd) {
    if(dataSource!=null)
      throw new RuntimeException("Unable to reinit connection pool.");
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(url);
    config.setUsername(user);
    config.setPassword(passwd);
    dataSource = new HikariDataSource(config);
  }

  public static Connection getConnection() throws SQLException,RuntimeException {
    if(dataSource!=null && dataSource.isRunning())
      return dataSource.getConnection();
    else
      throw new RuntimeException("Init pool first!");
  }

  public static void closePool() {
    if(dataSource!=null && !dataSource.isClosed()) {
      dataSource.close();
    }
  }
}
