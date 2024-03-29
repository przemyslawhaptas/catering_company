package com.dao;
 
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
 
public abstract class GenericDAO<T> {
    private final static String UNIT_NAME = "CrudPU";
 
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
 
    private Class<T> entityClass;
 
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    public void save(T entity) {
        em.persist(entity);
    }
 
    // Changed this method from the one from the tutorial
    public void delete(int entityId) {
    	T entityToBeRemoved = em.getReference(entityClass, entityId);
        em.remove(entityToBeRemoved);
    }
 
    public T update(T entity) {
        return em.merge(entity);
    }
 
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
    
    public T findReference(int entityID) {
        return em.getReference(entityClass, entityID);
    }
 
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected List<T> findManyResults(String namedQuery, Map<String, Object> parameters) {
        List<T> results = null; //TOO: check if not "List"
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            results = query.getResultList();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return results;
    }
    
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<String> pluckColumn(String columnName) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass).get(columnName)).distinct(true);
        return em.createQuery(cq).getResultList();
    }
    
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
