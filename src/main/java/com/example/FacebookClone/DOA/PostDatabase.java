package com.example.FacebookClone.DOA;

import com.example.FacebookClone.model.Post;
import com.example.FacebookClone.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDatabase {
    private Connection dbConnection;

    public PostDatabase(Connection connection) {
        this.dbConnection = connection;
    }

    /**
     * CREATE operation on Post
     * @param userId
     * @param post
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean createPost(int userId, Post post){
        boolean result = false;
        try{
            String query = "insert into posts(title,body,image_name,user_id) " +
                    "values (?,?,?,?)";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getBody());
            preparedStatement.setString(3, post.getImageName());
            preparedStatement.setInt(4, userId);

            preparedStatement.executeUpdate();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * GET by id operation on Post
     * @params postId
     * @return post object
     * */
    public Post getPostById(int postId){
        Post post = null;

        try{
            String query = "select p.id, p.title, p.body, u.numEmail from posts p"
                    +"  join users u on p.user_id=u.id where p.id=?";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setInt(1,postId);
            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                post = new Post();

                post.setId(result.getInt("id"));
                post.setTitle(result.getString("title"));
                post.setBody(result.getString("body"));
                post.setNumEmail(result.getString("numEmail"));

                return post;
            }
        }catch (Exception e){
        }

        return post;
    }

    /**
     * GET operation on Post
     * @param currentUser
     * @return list of posts
     * */
    public List<Post> getPosts(User currentUser){

        List<Post> posts = new ArrayList<>();

        try{
            String query = "select p.id, p.title, p.body, p.image_name, u.surname from posts p"
                    +"  join users u on p.user_id=u.id order by p.id DESC";
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            Post post = null;

            while(result.next()){
                post = new Post();
                post.setId(result.getInt("id"));
                post.setTitle(result.getString("title"));
                post.setBody(result.getString("body"));
                post.setImageName(result.getString("image_name"));
                post.setName(result.getString("surname"));

                //the total number of likes on this particular post
                String que = "select * from likes where post_id="+post.getId();
                PreparedStatement prepared = this.dbConnection.prepareStatement(que);
                ResultSet res = prepared.executeQuery();
                res.last();
                int noOfLikes = res.getRow();
                post.setNoLikes(noOfLikes);

                //no of comments made on that particular posts
                String que1 = "select * from comment where post_id="+post.getId();
                PreparedStatement prepared1 = this.dbConnection.prepareStatement(que1);
                ResultSet res1 = prepared1.executeQuery();
                res1.last();
                int noOfComments = res1.getRow();
                post.setNoComments(noOfComments);

                //return true if current user liked this post, else false
                String que2 = "select * from likes where post_id="+post.getId()+" and user_id="+currentUser.getId();
                PreparedStatement prepared2 = this.dbConnection.prepareStatement(que2);
                ResultSet res2 = prepared2.executeQuery();
                if(res2.next()) {
                    post.setLikedPost(true);
                }

                //add post to arraylist
                posts.add(post);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

       return posts;
    }

    /**
     * UPDATE operation on Post
     * @param postId
     * @param newPost
     * @return boolean(true for successful update and false on failure to update)
     * */
    public boolean updatePost(int postId, Post newPost){
       boolean success = false;

        try {
            //String query = "update posts set title=newPost.getTitle(), body=newPost.getBody() where id=posjjtId";
            String query = "update posts set title=?, body=? where id=?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);

            prepared.setString(1, newPost.getTitle());
            prepared.setString(2, newPost.getBody());
            prepared.setInt(3, postId);

            int result = prepared.executeUpdate();

            if(result > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * DELETE operation on Post
     * @param postId
     * @param userId
     * @return boolean(true for successful deletion and false on failure to delete)
     * */
    public boolean deletePost(int userId, int postId){
        boolean success =  false;
        try {
            String query = "delete from posts where id= ? and user_id=?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            prepared.setInt(1, postId);
            prepared.setInt(2, userId);
            int result = prepared.executeUpdate();

            if(result > 0) {
                success = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

}
