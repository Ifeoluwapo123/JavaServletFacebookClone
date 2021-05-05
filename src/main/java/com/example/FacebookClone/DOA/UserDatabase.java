package com.example.FacebookClone.DOA;

import com.example.FacebookClone.model.User;
import com.example.FacebookClone.utils.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private Connection dbConnection;

    public UserDatabase(Connection connection) {
        this.dbConnection = connection;
    }

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

                //decrypt password
                String decryptPass = PasswordHashing.decryptPassword(result.getString("password"));

                if(!decryptPass.equals(password)){
                    return null;
                }
            }
        }catch(Exception e){
        }

        return user;
    }

    public List<User> getUsers(){
        User user = null;
        List users = new ArrayList();

        try {
            String query = "select * from users";
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                user = new User();

                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setSurname(result.getString("surname"));
                user.setPassword(result.getString("password"));
                user.setNumEmail(result.getString("numEmail"));

                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int id){
        User user = null;

        try {
            String query = "select * from users where id="+id;
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                user = new User();

                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setSurname(result.getString("surname"));
                user.setPassword(result.getString("password"));
                user.setNumEmail(result.getString("numEmail"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
