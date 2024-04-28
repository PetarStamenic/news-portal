package com.example.projekat_backend.repository;

import java.sql.*;
import java.util.Optional;

abstract public class MySqlAbstractRepository {
    public MySqlAbstractRepository(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    protected Connection newConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/news_table", "root", "");
    }

    protected void closeResultSet(ResultSet resultSet){
        try {
            Optional.of(resultSet).get().close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    protected void closeStatement(Statement statement){
        try {
            Optional.of(statement).get().close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection){
        try {
            Optional.of(connection).get().close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
