package program.data.ejb.bean;

import org.hibernate.Session;
import program.HibernateSessionFactory;

public abstract class AbstractBean<E> {

    private Class<E> entityClass;

    public AbstractBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(E entity) throws Exception {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void edit(E entity) throws Exception {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void remove(E entity) throws Exception {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new Exception();
        }
    }

//
//    public E find(long id) throws Exception {
//        try {
//            return entityManager.find(entityClass, id);
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }
//
//    public List<E> findAll() throws Exception {
//        try {
//            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(entityClass));
//            return entityManager.createQuery(cq).getResultList();
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }
//
//    public List<E> findRange(int[] range) throws Exception {
//        try {
//            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(entityClass));
//            javax.persistence.Query q = entityManager.createQuery(cq);
//            q.setMaxResults(range[1] - range[0] + 1);
//            q.setFirstResult(range[0]);
//            return q.getResultList();
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }
//
//    public int count() throws Exception {
//        try {
//            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
//            javax.persistence.criteria.Root<E> rt = cq.from(entityClass);
//            cq.select(entityManager.getCriteriaBuilder().count(rt));
//            javax.persistence.Query q = entityManager.createQuery(cq);
//            return ((Long) q.getSingleResult()).intValue();
//        } catch (Exception e) {
//            throw new Exception();
//        }
//    }
}
