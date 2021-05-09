package com.example.FacebookClone.DOA;

import com.example.FacebookClone.model.User;
import com.example.FacebookClone.utils.PasswordHashing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Create and Delete operations on the User table
 * */
public class UserDatabase {
    private Connection dbConnection;

    public UserDatabase(Connection connection) {
        this.dbConnection = connection;
    }

    /**
     * CREATE operation on User
     * @param user
     * @return boolean(true for successful creation and false on failure to create)
     * */
    public boolean registerUser(User user){
        boolean set = false;
        try{

            String query = "insert into users(firstname,surname,password,numEmail,dob,gender) " +
                    "values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getNumEmail());
            preparedStatement.setString(5, user.getDob());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
            set = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return set;
    }

    /**
     * Get operation on User
     * @param numEmail
     * @param password
     * @return User object
     * */
    public User loginUser(String numEmail, String password){
        User user = null;
        String query = "";

        try {
            query = "select * from users where numEmail=?";

            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);

            preparedStatement.setString(1, numEmail);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                user = new User();

                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setSurname(result.getString("surname"));
                user.setPassword(result.getString("password"));
                user.setNumEmail(result.getString("numEmail"));
                user.setGender(result.getString("gender"));
                user.setDob(result.getString("dob"));

                String decryptPass = PasswordHashing.decryptPassword(result.getString("password"));

                if(!decryptPass.equals(password)){
                    return null;
                }
            }
        }catch(Exception e){
        }

        return user;
    }

    /**
     * DELETE operation on User
     * @param email
     * @return boolean(true for successful deletion and false on failure to delete)
     * */
    public boolean deleteUser(String email){
        boolean success =  false;
        try {
            String query = "delete from users where numEmail= ?";
            PreparedStatement prepared = this.dbConnection.prepareStatement(query);
            prepared.setString(1, email);

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
