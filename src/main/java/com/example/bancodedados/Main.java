/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bancodedados;

import com.sun.org.apache.xml.internal.utils.URI;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  //@Value("${spring.datasource.url}")
//  private String dbUrl = "postgres://lbqvjvwascbnvm:ebcf05adf0182981d555207b5330c1636f70c97431a9272961f2c510d050578f@ec2-54-225-115-177.compute-1.amazonaws.com:5432/de9ajdtkd0lce3";
//  @Autowired
//  private DataSource dataSource;

  public static void main(String[] args) throws Exception {

    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

//  @RequestMapping("/db")
//  String db(Map<String, Object> model) {
//    try (Connection connection = dataSource.getConnection()) {
//      Statement stmt = connection.createStatement();
//      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//      ArrayList<String> output = new ArrayList<String>();
//      while (rs.next()) {
//        output.add("Read from DB: " + rs.getTimestamp("tick"));
//      }
//
//      model.put("records", output);
//      return "db";
//    } catch (Exception e) {
//      model.put("message", e.getMessage());
//      return "error";
//    }
//  }
//
//  @Bean
//  public DataSource dataSource() throws SQLException {
//    if (dbUrl == null || dbUrl.isEmpty()) {
//      return new HikariDataSource();
//    } else {
//      HikariConfig config = new HikariConfig();
//      config.setJdbcUrl(dbUrl);
//      return new HikariDataSource(config);
//    }
//  }

//  @Bean
//  public static DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName("org.postgresql.Driver");
//    dataSource.setUrl(dbUrl);
//    dataSource.setUsername("lbqvjvwascbnvm");
//    dataSource.setPassword("ebcf05adf0182981d555207b5330c1636f70c97431a9272961f2c510d050578f");
//    return dataSource;
//  }
}
