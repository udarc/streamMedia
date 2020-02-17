package com.streammedia.perisistence;

import com.streammedia.entity.User;
import org.apache.logging.log4j.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;


public class GenericDao <T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets all entities.
     *
     * @return the list of all entities
     */
    public List<T> getAllUsers() {

        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of users " + list);
        return list;
    }


    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type, for example Role
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }
    private Session getSession(){
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Gets an entity by EntityId.
     *
     * @param EntityId the entity id to search by
     * @return the entity
     */
    public <T>T getUserById(int EntityId) {
        logger.debug("Getting Entity by Id {}",EntityId);
        Session session = getSession();
        T entity = (T)session.get(type,EntityId);
        session.close();
        return entity;
    }
    /**
     * update Entity
     * @param entity Entity to be inserted or updated
     */
    public void saveOrUpdate(T entity) {
        logger.debug("Updating");
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * update Entity
     *
     * @param entity Entity to be inserted or updated
     * @return the int
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete an Entity
     * @param entity Entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for entity with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createQuery( query ).getResultList();
        session.close();
        return list;
    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for entity with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createQuery( query ).getResultList();
        session.close();
        return list;
    }

}
