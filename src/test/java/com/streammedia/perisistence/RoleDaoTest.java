package com.streammedia.perisistence;

import com.streammedia.entity.Role;
import com.streammedia.entity.Role;
import com.streammedia.entity.User;
import com.streammedia.test.utility.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role dao test.
 */

//@Disabled
class RoleDaoTest {

    RoleDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new RoleDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllRolesSuccess() {
        List<Role> roles = dao.getAllRoles();
        assertEquals(5, roles.size());
    }

    /**
     * Verifies gets roles by last name successfully.
     */
    @Test
    void getRolesByName() {
        List<Role> roles = dao.getRoleName("a");
        assertEquals(3, roles.size());
    }

    /**
     * Verifies a role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = dao.getRoleById(2);
        assertNotNull(retrievedRole);
        assertEquals("admin", retrievedRole.getName());
    }

    /**
     * Verify successful insert of a role
     */
//    @Disabled
    @Test
    void insertSuccess() {
        UserDao userDao =  new UserDao();
        User user = userDao.getUserById(1);
        Role newRole = new Role();
        newRole.setName("media creator");
        newRole.setUser(user);

        int id = dao.insert(newRole);
        assertTrue(id > 0);
        Role insertedRole = dao.getRoleById(id);
        assertEquals("media creator", insertedRole.getName());
        assertNotNull(insertedRole.getUser());
        assertEquals("jcoyne",insertedRole.getUser().getUsername());
    }

    /**
     * Verify successful update of role
     */
    @Test
    void updateSuccess() {
        String newName = "manager";
        Role roleToUpdate = dao.getRoleById(1);
        roleToUpdate.setName(newName);
        dao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = dao.getRoleById(1);
        assertEquals(newName, retrievedRole.getName());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = dao.getByPropertyEqual("name", "admin");
        assertEquals(1, roles.size());
        assertEquals(2, roles.get(0).getRoleId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = dao.getByPropertyLike("name", "a");
        assertEquals(3, roles.size());
    }

    /**
     * Verify successful delete of role
     */
//    @Disabled
    @Test
    void deleteSuccess() {
        dao.delete(dao.getRoleById(5));
        assertNull(dao.getRoleById(5));
    }
}
