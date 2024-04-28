package com.example.projekat_backend.repository.comment;

import com.example.projekat_backend.model.Comment;
import com.example.projekat_backend.model.News;
import com.example.projekat_backend.repository.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLCommentRepoository extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public List<Comment> allComments() {
        List<Comment> commentList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetNews = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM comments ORDER BY creation_date DESC");
            while (resultSet.next()) {
                Comment comment = new Comment(resultSet.getInt("id"), resultSet.getString("author"),
                        resultSet.getString("content"), resultSet.getDate("creation_date"),null);
                preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ?");
                preparedStatement.setInt(1, resultSet.getInt("news"));
                resultSetNews = preparedStatement.executeQuery();
                while (resultSetNews.next()) {
                    News news = new News(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("creation_date"),null,resultSet.getInt("visits"),null,null);
                    synchronized (this) {
                        comment.setNews(news);
                    }
                    commentList.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return commentList;
    }

    @Override
    public Comment findComment(Integer id) {
        Comment comment = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSet resultSetNews = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("creation_date");
                comment = new Comment(id, author, content, date,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return comment;
    }

    @Override
    public Comment addComment(Comment comment) {        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO comment (author, content, id_news) values (?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setInt(3, comment.getNews().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return comment;
    }

    @Override
    public void deleteComment(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
