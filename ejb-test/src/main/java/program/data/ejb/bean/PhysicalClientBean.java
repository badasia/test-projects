package program.data.ejb.bean;

import org.hibernate.Query;
import org.hibernate.Session;
import program.HibernateSessionFactory;
import program.data.ejb.entity.PhysicalClientEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PhysicalClientBean extends AbstractBean<PhysicalClientEntity> {

    public PhysicalClientBean() {
        super(PhysicalClientEntity.class);
    }

    public static PhysicalClientEntity findByFullName(String surname, String name, String patronymic) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.getNamedQuery("PhysicalClientEntity.findByFullName");
        query.setParameter("surname", surname);
        query.setParameter("name", name);
        query.setParameter("patronymic", patronymic);
        List<PhysicalClientEntity> resultList = query.list();
        session.close();
        if (resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
