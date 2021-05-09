package com.example.FacebookClone.DOA;

import com.example.FacebookClone.dbConnectionProvider.DbConnection;
import com.example.FacebookClone.model.Post;
import com.example.FacebookClone.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDatabaseTest {
    Post post = null;

    @BeforeEach
    @Test
    void setup() throws SQLException {
        post = new Post("test title","test body", "test image");
    }

    @Test
    void postCrud() throws SQLException {
        PostDatabase postDatabase = new PostDatabase(DbConnection.getConnection());
        User user = new User();
        user.setId(1);

        //create post
        boolean success = postDatabase.createPost(user.getId(), post);
        assertTrue(success);

        //get post
        List<Post> ls = postDatabase.getPosts(user);
        assertNotNull(ls);

        //delete post
        System.out.println(post.getImageName());
        String query = "select id from posts where image_name=?";
        PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
        statement.setString(1, post.getImageName());
        ResultSet resultSet = statement.executeQuery();

        int postId = 0;

        while(resultSet.next())
        {
           postId = resultSet.getInt("id");
        }

        boolean data = postDatabase.deletePost(user.getId(), postId);
        assertTrue(data);
    }
}