package com.streammedia.perisistence;

import com.streammedia.entity.User;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */

//@Disabled
class UserDaoTest {

    UserDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all users successfully.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(6, users.size());
    }

    /**
     * Verifies gets users by last name successfully.
     */
    @Test
    void getUsersByLastNameSuccess() {
        List<User> users = dao.getUserByLastName("c");
        assertEquals(3, users.size());
    }

    /**
     * Verifies a user is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getUserById(3);
        assertNotNull(retrievedUser);
        assertEquals("Barney", retrievedUser.getFirstName());
    }

    /**
     * Verify successful insert of a user
     */
//    @Disabled
    @Test
    void insertSuccess() {

        User newUser = new User("fflintstone", "fflintstone@streammedia.com", "fflintstone", LocalDate.now(),LocalDate.now());
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getUserById(id);
        assertEquals("fflintstone", insertedUser.getUsername());
        assertEquals("fflintstone@streammedia.com",insertedUser.getEmail());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TO DO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful update of user
     */
    @Test
    void updateSuccess() {
        String newLastName = "Davis";
        User userToUpdate = dao.getUserById(3);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getUserById(3);
        assertEquals(newLastName, retrievedUser.getLastName());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "Curry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "c");
        assertEquals(3, users.size());
    }

    /**
     * Verify successful delete of user
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        dao.delete(dao.getUserById(6));
        assertNull(dao.getUserById(6));
    }
}


//package com.streammedia.perisistence;
//
//import com.streammedia.entity.User;
//import com.streammedia.test.utility.Database;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * The type User dao test.
// */
//
//class UserDaoTest {
//    /**
//     * The User dao.
//     */
//    UserDao dao;
//
//    /**
//     * Sets up.
//     */
//    @BeforeEach
//    void setUp() {
//        dao =  new UserDao();
//        Database database = Database.getInstance();
//        database.runSQL("cleandb.sql");
//
//
//    }
//
//    /**
//     * Verify that  all users are retrieved successfully.
//     */
//    @Test
//    void getAllSuccess() {
//        List<User> users = dao.getAllUsers();
//        assertEquals(6, users.size());
//    }
//    @Test
//    void getAllUsersSuccess() {
//        List<User> users =  dao.getAllUsers();
//        assertEquals(6,users.size());
//    }
//
//    /**
//     * Vrify that search by last name returns  user by last name returns correct results.
//     */
//
//    @Test
//    void getUserByLastNameSuccess() {
//        List<User> users = dao.getUserByLastName("e");
//        assertEquals(6,users.size());
//    }
//
//    /**
//     * Verify that user is returned based successfully on id search.
//     */
//    @Test
//    void getByIdSuccess() {
//        User retrievedUser = dao.getUserById(3);
//        assertEquals("Barney", retrievedUser.getFirstName());
//        assertEquals("Curry", retrievedUser.getLastName());
//        assertEquals("bcurry", retrievedUser.getUsername());
//        assertEquals("bcurry@streammedia.com", retrievedUser.getEmail());
//        assertEquals("1947-11-11", retrievedUser.getBirthdate());
//    }
//
//    @Test
//    void getUserByIdSuccess(){
//        User retrievedUser = dao.getUserById(3);
//        assertNotNull(retrievedUser);
//        assertEquals("bcurry",retrievedUser.getUsername());
//
//    }
//    /**
//     * Verify successful get by property (equal match)
//     */
//
//
//    @Test
//    void getByPropertyEqualSuccess() {
//        List<User> users = dao.getByPropertyLike("username", "ujeanne");
//        assertEquals(1, users.size());
////        assertEquals(3, users.get(0).getUserId());
//    }
//
//    /**
//     * Verify successful insert of a user
//     */
//    @Disabled
//    @Test
//    void insertSuccess() {
//
//        User newUser = new User("ian", "iam@madisoncollege.edu", "1234",LocalDate.now(),LocalDate.now());
//        int id = dao.insert(newUser);
//        assertNotEquals(0,id);
//        User insertedUser = dao.getUserById(id);
//        assertEquals("ian", insertedUser.getUsername());
//        // Could continue comparing all values, but
//        // it may make sense to use .equals()
//        // TO DO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
//    }
//
//    /**
//     * Verify successful delete of user
//     */
//@Disabled
//    @Test
//    void deleteSuccess() {
//        dao.delete(dao.getUserById(1));
//        assertNull(dao.getUserById(2));
//    }
//
//}