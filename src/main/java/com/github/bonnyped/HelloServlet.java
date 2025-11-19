package com.github.bonnyped;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/hello", "/" }, name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // ServletContext servletContext = getServletContext();
        String path = "/home/bonnyped/projects_to_do/currency-exchange/src/main/resources/new.db";
        // servletContext.getRealPath("webapps/currency-exchange-1.0-SNAPSHOT/WEB-INF/classes/new.db");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM currencies";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out
                        .println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " "
                                + resultSet.getString(4));
            }
        } catch (SQLException e) {
            log("Error with connection to data base!");
        }

    }

}
