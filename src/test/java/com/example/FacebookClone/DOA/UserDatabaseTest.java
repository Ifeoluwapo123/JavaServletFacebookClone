package com.example.FacebookClone.DOA;

import com.example.FacebookClone.dbConnectionProvider.DbConnection;
import com.example.FacebookClone.model.User;
import com.example.FacebookClone.utils.PasswordHashing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDatabaseTest {

    User user = null;

    @BeforeEach
    void setup(){
        String password = PasswordHashing.encryptPassword("password");
        user = new User("firstname","surname","numEmail",password,"sob","gender");
    }

    @Test
    void userCrud(){
        UserDatabase userDatabaseTest = new UserDatabase(DbConnection.getConnection());

        boolean success = userDatabaseTest.registerUser(user);
        assertTrue(success);

        User user = loginUser("numEmail", "password");
        assertNotNull(user);

        assertNotNull(user.getFirstname());
        assertNotNull(user.getSurname());
        assertNotNull(user.getNumEmail());
        assertNotNull(user.getPassword());
        assertNotNull(user.getDob());
        assertNotNull(user.getGender());

        assertEquals(user.getFirstname(), "firstname");
        assertEquals(user.getSurname(), "surname");
        assertEquals(user.getNumEmail(), "numEmail");
        assertEquals(PasswordHashing.decryptPassword(user.getPassword()), "password");
        assertEquals(user.getDob(), "sob");
        assertEquals(user.getGender(), "gender");

        //delete
        boolean result = deleteUser("numEmail");
        assertTrue(result);
    }

    User loginUser(String numEmail, String password){
        UserDatabase userDatabaseTest = new UserDatabase(DbConnection.getConnection());
        User success = userDatabaseTest.loginUser(numEmail, password);
        return success;
    }

    boolean deleteUser(String data){
        UserDatabase userDatabaseTest = new UserDatabase(DbConnection.getConnection());
        boolean success = userDatabaseTest.deleteUser(data);
        return success;
    }
}