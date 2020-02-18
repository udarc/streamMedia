package com.streammedia.perisistence;

import com.streammedia.entity.Role;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The type Role dao.
 */
@Log4j2
public class RoleDao {
    /**
     * The Session factory.
     */
//    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * update role
     *
     * @param role Role to be inserted or updated
     * @return the int
     */
    public int insert(Role role) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(role);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Gets all roles.
     *
     * @return the all roles
     */
    public List<Role> getAllRoles() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);

        Root<Role> root = query.from(Role.class);
        List<Role> roles = session.createQuery(query).getResultList();
        session.close();
        log.debug("The list of roles {}" , roles);
        return roles;
    }

    /**
     * Gets role name.
     *
     * @param name the name
     * @return the role name
     */
    public List<Role> getRoleName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        Expression<String> propertyPath = root.get("name");
        query.where(builder.like(propertyPath,"%" + name + "%"));
        List<Role> roles = session.createQuery(query).getResultList();
        session.close();
        return roles;
    }

    /**
     * Gets role by id.
     *
     * @param roleId the role id
     * @return the role by id
     */
    public Role getRoleById(int roleId) {
        log.debug("Getting role by Id {}",roleId);
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class,roleId);
        session.close();
        return role;
    }

    /**
     * update role
     *
     * @param role Role to be inserted or updated
     */
    public void saveOrUpdate(Role role) {
        log.info("Save Mathoth");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
        session.close();
    }


    /**
     * Delete a role
     *
     * @param role Role to be deleted
     */
    public void delete(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    /**
     * Get role by property (exact match)
     * sample usage: getByPropertyEqual("name", "admin")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<Role> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for role with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery( Role.class );
        Root<Role> root = query.from( Role.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Role> roles = session.createQuery( query ).getResultList();

        session.close();
        return roles;
    }

    /**
     * Get role by property (like)
     * sample usage: getByPropertyLike("name", "a")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<Role> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for role with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery( Role.class );
        Root<Role> root = query.from( Role.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Role> roles = session.createQuery( query ).getResultList();
        session.close();
        return roles;
    }

}
