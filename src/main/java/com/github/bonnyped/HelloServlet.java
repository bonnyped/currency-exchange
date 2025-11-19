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

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/new.db");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM currencies")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getInt(2) + " " + resultSet.getInt(3) + " "
                        + resultSet.getInt(4));
            }
        } catch (SQLException e) {
            log("Error with connection to data base!");
        }

    }

}
