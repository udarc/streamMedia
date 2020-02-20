package com.streammedia.perisistence;

import com.streammedia.entity.Role;
import com.streammedia.entity.User;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role dao test.
 */

//@Disabled
class RoleDaoTest {

    GenericDao genericDao;
    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao(Role.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllRolesSuccess() {
        List<Role> roles = genericDao.getAll();
        assertEquals(5, roles.size());
    }

//    /**
//     * Verifies gets roles by last name successfully.
//     */
//    @Test
//    void getRolesByName() {
//        List<Role> roles = dao.getRoleName("a");
//        assertEquals(3, roles.size());
//    }

    /**
     * Verifies a role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = (Role)genericDao.getById(2);
        assertNotNull(retrievedRole);
        assertEquals("admin", retrievedRole.getName());
    }

    /**
     * Verify successful insert of a role
     */
//    @Disabled
    @Test
    void insertSuccess() {
        genericDao  =  new GenericDao(User.class);
        User user = (User)genericDao.getById(1);
        Role newRole = new Role();
        newRole.setName("media creator");
        newRole.setUser(user);

        int id = genericDao.insert(newRole);
        assertTrue(id > 0);
        Role insertedRole = (Role)genericDao.getById(id);
        assertEquals("media creator", insertedRole.getName());
        assertNotNull(insertedRole.getUser());
//        assertEquals("jcoyne",insertedRole.getUser().getUsername());
        assertTrue(user.equals(insertedRole.getUser()));
    }

    /**
     * Verify successful update of role
     */
    @Test
    void updateSuccess() {
        String newName = "manager";
        Role roleToUpdate = (Role)genericDao.getById(1);
        roleToUpdate.setName(newName);
        genericDao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = (Role)genericDao.getById(1);
//        assertEquals(newName, retrievedRole.getName());
        assertTrue(newName.equals(retrievedRole.getName()));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = genericDao.getByPropertyEqual("name", "admin");
        assertEquals(1, roles.size());
        assertEquals(2, roles.get(0).getRoleId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = genericDao.getByPropertyLike("name", "a");
        assertEquals(3, roles.size());
    }

    /**
     * Verify successful delete of role
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(5));
        assertNull(genericDao.getById(5));
    }
}
