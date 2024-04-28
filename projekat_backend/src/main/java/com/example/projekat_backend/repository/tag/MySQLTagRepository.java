package com.example.projekat_backend.repository.tag;

import com.example.projekat_backend.model.Tag;
import com.example.projekat_backend.repository.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTagRepository extends MySqlAbstractRepository implements TagRepository{
    @Override
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet.next()){
                System.out.println(resultSet.getString("tag_name"));
                Tag tag = new Tag(resultSet.getInt("id"),resultSet.getString("tag_name"));
                tags.add(tag);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tags;
    }

    @Override
    public Tag findTag(String id) {
        Tag tag = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.prepareStatement("select * from tag where  tag.tag_name = ?");
            statement.setString(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                tag = new Tag(resultSet.getInt("id"),id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public Tag findTagById(Integer id) {
        Tag tag = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.prepareStatement("select * from tag where id = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                tag = new Tag(id,resultSet.getString("tag_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public Tag addTag(String tag) {
        Tag tg = new Tag();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet initialResultSet = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("select * from tag where tag_name = ?");
            preparedStatement.setString(1,tag);
            initialResultSet = preparedStatement.executeQuery();
            if(!initialResultSet.next()){
                String[] generatedCollumns = {"id"};
                preparedStatement = connection.prepareStatement("INSERT into tag (tag_name) values(?)",generatedCollumns);
                preparedStatement.setString(1,tag);
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    tg.setId(resultSet.getInt(1));
                    tg.setTagName(tag);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            if (resultSet != null)
                this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tg;
    }

    @Override
    public void deleteTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM tag where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
