package com.streammedia.perisistence;

import com.streammedia.entity.Role;
import com.streammedia.entity.User;
import com.streammedia.test.utility.Database;
import org.hibernate.criterion.Order;
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
    @Test
    void insertWithRoleSuccess() {

        User newUser = new User("ujeanne", "ujeanne@streammedia.com", "ujeanne", LocalDate.now(),LocalDate.now());
       String roleName = "Admin";
       Role role = new Role();
       role.setName("Admin");
       role.setUser(newUser);
       role.setCreatedAt(LocalDate.now());
       newUser.addRole(role);
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getUserById(id);
        assertEquals("ujeanne", insertedUser.getUsername());
        assertEquals("ujeanne@streammedia.com",insertedUser.getEmail());
        assertEquals(1,insertedUser.getRoles().size());
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
        List<User> users = dao.getByPropertyEqual("lastName", "Curry");
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
