package com.streammedia.perisistence;

import com.streammedia.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
class UserDaoTest {
    /**
     * The User dao.
     */
    UserDao userDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userDao =  new UserDao();

    }

    /**
     * Gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users =  userDao.getAllUsers();
        assertEquals(1,users.size());
    }

    /**
     * Gets user by last name success.
     */
    @Test
    void getUserByLastNameSuccess() {
        List<User> users = userDao.getUserByLastName("u");
        assertEquals(1,users.size());

    }
}