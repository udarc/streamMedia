package com.streammedia.perisistence;

import com.streammedia.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
@Disabled
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
     * Verify that  all users are retrieved successfully.
     */
    @Disabled
    @Test
    void getAllUsersSuccess() {
        List<User> users =  userDao.getAllUsers();
        assertEquals(2,users.size());
    }

    /**
     * Vrify that search by last name returns  user by last name returns correct results.
     */
    @Disabled
    @Test
    void getUserByLastNameSuccess() {
        List<User> users = userDao.getUserByLastName("e");
        assertEquals(2,users.size());
    }

    /**
     * Verify that user is returned based successfully on id search.
     */
    @Test
    void getUserByIdSuccess(){
        User retrievedUser = userDao.getUserById(3);
        assertNotNull(retrievedUser);
        assertEquals("ujeanne",retrievedUser.getUsername());

    }
    /**
     * Verify successful get by property (equal match)
     */


    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = userDao.getByPropertyLike("username", "ujeanne");
        assertEquals(1, users.size());
//        assertEquals(3, users.get(0).getUserId());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("ian", "iam@madisoncollege.edu", "1234",LocalDate.now(),LocalDate.now());
        int id = userDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = userDao.getUserById(id);
        assertEquals("ian", insertedUser.getUsername());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TO DO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful delete of user
     */
    @Disabled
    @Test
    void deleteSuccess() {
        userDao.delete(userDao.getUserById(1));
        assertNull(userDao.getUserById(2));
    }

}