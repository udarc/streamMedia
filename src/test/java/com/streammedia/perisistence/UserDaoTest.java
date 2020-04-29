package com.streammedia.perisistence;

import com.streammedia.entity.Role;
import com.streammedia.entity.User;
import com.streammedia.test.utility.Database;
import lombok.extern.log4j.Log4j2;
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
    @Log4j2
class UserDaoTest {

    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao =  new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all users successfully.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = genericDao.getAll();
        assertEquals(6, users.size());
    }

    /**
     * Verifies a user is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = (User)genericDao.getById(2);
        assertNotNull(retrievedUser);
        assertEquals("Fred", retrievedUser.getFirstName());
    }

    /**
     * Verify successful insert of a user
     */
//    @Disabled
    @Test
    void insertSuccess() {

        User newUser = new User("fflintstone", "fflintstone@streammedia.com", "fflintstone", LocalDate.now(),LocalDate.now());
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
//        assertEquals("fflintstone", insertedUser.getUsername());
//        assertEquals("fflintstone@streammedia.com",insertedUser.getEmail());
        assertEquals(newUser,insertedUser);
        assertTrue(newUser.equals(insertedUser));
    }

    /**
     * Insert with role success.
     */
    @Test
    void insertWithRoleSuccess() {

        User newUser = new User("ujeanne", "ujeanne@streammedia.com", "ujeanne", LocalDate.now(),LocalDate.now());
       String roleName = "Admin";
       Role role = new Role();
       role.setName("Admin");
       role.setUser(newUser);
       role.setCreatedAt(LocalDate.now());
       newUser.addRole(role);
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
//        assertEquals("ujeanne", insertedUser.getUsername());
//        assertEquals("ujeanne@streammedia.com",insertedUser.getEmail());
        assertTrue(newUser.equals(insertedUser));
        assertEquals(1,insertedUser.getRoles().size());
        }

    /**
     * Verify successful update of user
     */
    @Test
    void updateSuccess() {
        String newLastName = "Davis";
        User userToUpdate = (User)genericDao.getById(3);
        userToUpdate.setLastName(newLastName);
        genericDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User)genericDao.getById(3);
//        assertEquals(newLastName, retrievedUser.getLastName());
//        assertEquals(userToUpdate,retrievedUser);
        log.debug("User to Update: " + userToUpdate);
        log.debug("User to Retrieve: " + retrievedUser);
        assertTrue(userToUpdate.getEmail().equals(retrievedUser.getEmail()));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = genericDao.getByPropertyEqual("lastName", "Curry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getByPropertyLike("lastName", "c");
        assertEquals(3, users.size());
    }

    /**
     * Verify successful delete of user
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(5));
        assertNull(genericDao.getById(5));
    }

    /**
     * Delete user with r ole success.
     * TODO Check this test
     */

    @Disabled
    @Test
    void deleteUserWithROleSuccess() {
        GenericDao roleDao = new GenericDao(Role.class);
        User user = (User)genericDao.getById(1);
        Role role = (Role) roleDao.getById(1);
        user.removeRole(role);
        genericDao.delete(user);
        log.debug("User:   " + user);
        log.debug("Role:  " + role);
//        assertNull(user);
        assertNull(role);

    }
}
